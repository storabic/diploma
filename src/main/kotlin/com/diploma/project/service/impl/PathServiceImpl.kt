package com.diploma.project.service.impl

import com.diploma.project.exception.ForbiddenActionException
import com.diploma.project.exception.NotEnoughPathPointsException
import com.diploma.project.exception.PathNotFoundException
import com.diploma.project.exception.WrongCoordinatesException
import com.diploma.project.model.Path
import com.diploma.project.model.PathPoint
import com.diploma.project.model.dto.get.PathDto
import com.diploma.project.model.dto.get.PathPointDto
import com.diploma.project.model.dto.create.CreatePathDto
import com.diploma.project.model.dto.create.CreatePathPointDto
import com.diploma.project.model.dto.update.ChangePathDto
import com.diploma.project.persistence.PathDao
import com.diploma.project.persistence.PathPointDao
import com.diploma.project.service.AccountService
import com.diploma.project.service.PathService
import com.diploma.project.service.RateService
import com.diploma.project.util.toDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PathServiceImpl(
    val pathDao: PathDao,
    val pathPointDao: PathPointDao,
    val accountService: AccountService,
    val rateService: RateService
) : PathService {
    override fun getPath(pathId: Long): Path {
        return pathDao.findById(pathId).orElseThrow { PathNotFoundException(pathId) }
    }

    override fun getNearPaths(userId: Long, latitude: Double, longitude: Double, pageable: Pageable): Page<PathDto> {
        val user = accountService.getUser(userId)
        return pathDao.getNearPaths(latitude, longitude, pageable)
            .map { it.toDto(rateService.getByPathAndAccount(it, user)?.rate) }
    }

    override fun getPathPoints(pathId: Long): List<PathPointDto> {
        val path = getPath(pathId)
        return pathPointDao.getAllByPathSorted(path.id!!).map { it.toDto() }
    }

    @Transactional
    override fun createPath(createPathDto: CreatePathDto): PathDto {
        if (createPathDto.points.size <= 1) {
            throw NotEnoughPathPointsException(createPathDto.name)
        }
        val author = accountService.getUser(createPathDto.authorUserId)
        val pathToCreate = Path(
            author = author,
            name = createPathDto.name,
            description = createPathDto.description,
            beginLatitude = createPathDto.points[0].latitude,
            beginLongitude = createPathDto.points[0].longitude
        )
        val pathFromDb = pathDao.save(pathToCreate)
        createPathPoints(createPathDto.points, pathFromDb)
        return pathFromDb.toDto(null)
    }

    override fun changePath(changePathDto: ChangePathDto) {
        if (changePathDto.points.size <= 1) {
            throw NotEnoughPathPointsException(changePathDto.name)
        }
        val path = getPath(changePathDto.pathId)
        val user = accountService.getUser(changePathDto.authorUserId)
        if (user != path.author) {
            throw ForbiddenActionException("User with id ${user.id} cannot change path with id = ${path.id}")
        }
        path.description = changePathDto.description
        path.name = changePathDto.name
        path.beginLatitude = changePathDto.points[0].latitude
        path.beginLongitude = changePathDto.points[0].longitude
        pathDao.save(path)
        pathPointDao.deleteAll(pathPointDao.getAllByPath(path))
        createPathPoints(changePathDto.points, path)
    }

    override fun getPathsByUser(userId: Long, pageable: Pageable): Page<PathDto> {
        val user = accountService.getUser(userId)
        return pathDao.getAllByAuthor(user, pageable).map { it.toDto(rateService.getByPathAndAccount(it, user)?.rate) }
    }

    @Transactional
    override fun ratePath(pathId: Long, userId: Long, rate: Double) {
        val user = accountService.getUser(userId)
        val path = getPath(pathId)
        var pathNewRateSum = path.currentRating * path.ratedCount
        val existedRate = rateService.getByPathAndAccount(path, user)
        if (existedRate != null) {
            pathNewRateSum -= existedRate.rate!!
            --path.ratedCount
            existedRate.rate = rate
            rateService.update(existedRate)
        } else {
            rateService.create(user, null, path, rate)
        }
        pathNewRateSum += rate
        ++path.ratedCount
        path.currentRating = pathNewRateSum / path.ratedCount
        pathDao.save(path)
    }

    override fun search(userId: Long, searchString: String, page: Pageable): Page<PathDto> {
        val user = accountService.getUser(userId)
        return pathDao.search(searchString, page).map { it.toDto(rateService.getByPathAndAccount(it, user)?.rate) }
    }

    @Transactional
    fun createPathPoints(points: List<CreatePathPointDto>, path: Path) {
        val pathPoints = points.map {
            if (it.latitude > 90 || it.latitude < 0 || it.longitude > 90 || it.longitude < 0) {
                throw WrongCoordinatesException()
            }
            PathPoint(
                name = it.name,
                path = path,
                latitude = it.latitude,
                longitude = it.longitude,
                address = it.address
            )
        }
        pathPoints[0].first = true
        var nextPathPoint: PathPoint? = null
        for (i in pathPoints.size - 1 downTo 0) {
            pathPoints[i].nextPathPoint = nextPathPoint
            nextPathPoint = pathPointDao.save(pathPoints[i])
        }
    }
}
