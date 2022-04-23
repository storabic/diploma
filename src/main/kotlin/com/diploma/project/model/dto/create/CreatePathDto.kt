package com.diploma.project.model.dto.create

class CreatePathDto (
    val authorUserId: Long,
    val description: String,
    val name: String,
    val points: List<CreatePathPointDto>
)
