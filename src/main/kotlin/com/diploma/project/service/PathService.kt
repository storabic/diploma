package com.diploma.project.service

import com.diploma.project.model.Path

interface PathService {
    fun getPath(pathId: Long) : Path
}