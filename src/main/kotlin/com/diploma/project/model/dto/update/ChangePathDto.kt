package com.diploma.project.model.dto.update

import com.diploma.project.model.dto.create.CreatePathPointDto

class ChangePathDto (
    val pathId: Long,
    val authorUserId: Long,
    val description: String,
    val name: String,
    val points: List<CreatePathPointDto>
)
