package com.diploma.project.service.impl

import com.diploma.project.model.Path
import com.diploma.project.persistence.PathDao
import com.diploma.project.service.PathService
import org.springframework.stereotype.Service

@Service
class PathServiceImpl(
    val pathDao: PathDao
) : PathService {
    override fun getPath(pathId: Long): Path {
        return pathDao.getById(pathId)
    }
}
