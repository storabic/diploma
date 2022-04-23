package com.diploma.project.controller

import com.diploma.project.model.dto.get.PathDto
import com.diploma.project.model.dto.get.PathPointDto
import com.diploma.project.model.dto.create.CreatePathDto
import com.diploma.project.model.dto.update.ChangePathDto
import com.diploma.project.service.PathService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("path")
class PathController(
    var pathService: PathService
) {
    @GetMapping("near")
    fun getNearPaths(@RequestParam latitude: Double, @RequestParam longitude: Double, pageable: Pageable): Page<PathDto> {
        return pathService.getNearPaths(latitude, longitude, pageable)
    }

    @GetMapping("by-user")
    fun getPathsByUser(@RequestParam userId: Long, pageable: Pageable): Page<PathDto> {
        return pathService.getPathsByUser(userId, pageable)
    }

    @GetMapping("points")
    fun getPathPoints(@RequestParam pathId: Long) : List<PathPointDto> {
        return pathService.getPathPoints(pathId)
    }

    @PostMapping("create")
    fun createPath(@RequestBody createPathDto: CreatePathDto): PathDto {
        return pathService.createPath(createPathDto)
    }

    @PutMapping("change")
    fun changePath(@RequestBody changePathDto: ChangePathDto) {
        pathService.changePath(changePathDto)
    }

    @PutMapping("rate")
    fun ratePath(@RequestParam pathId: Long, @RequestParam userId: Long, @RequestParam rate: Double) {
        pathService.ratePath(pathId, userId, rate)
    }
}
