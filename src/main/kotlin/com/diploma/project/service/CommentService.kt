package com.diploma.project.service

import com.diploma.project.model.dto.CommentDto
import com.diploma.project.model.dto.LeaveCommentDto
import io.swagger.v3.oas.annotations.parameters.RequestBody

interface CommentService {
    fun leaveComment(@RequestBody commentDto: LeaveCommentDto): CommentDto
}
