package com.diploma.project.model.dto.create

data class LeaveCommentDto(
    var userId: Long,
    var pathId: Long,
    var text: String
)
