package com.diploma.project.service

import com.diploma.project.model.Path
import com.diploma.project.model.dto.update.ChangePathDto
import com.diploma.project.model.dto.create.CreatePathDto
import com.diploma.project.model.dto.get.PathDto
import com.diploma.project.model.dto.get.PathPointDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PathService {
    fun getPath(pathId: Long) : Path
    fun getNearPaths(latitude: Double, longitude: Double, pageable: Pageable): Page<PathDto>
    fun getPathPoints(pathId: Long): List<PathPointDto>
    fun createPath(createPathDto: CreatePathDto): PathDto
    fun changePath(changePathDto: ChangePathDto)
    fun getPathsByUser(userId: Long, pageable: Pageable): Page<PathDto>
    fun ratePath(pathId: Long, userId: Long, rate: Double)
}