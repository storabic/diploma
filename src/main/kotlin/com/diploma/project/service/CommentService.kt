package com.diploma.project.service

import com.diploma.project.model.dto.get.CommentDto
import com.diploma.project.model.dto.create.LeaveCommentDto
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CommentService {
    fun leaveComment(@RequestBody commentDto: LeaveCommentDto): CommentDto
    fun like(userId: Long, commentId: Long): Boolean
    fun unlike(userId: Long, commentId: Long): Boolean
    fun getCommentsByPath(pathId: Long, userId: Long, page: Pageable): Page<CommentDto>
}
