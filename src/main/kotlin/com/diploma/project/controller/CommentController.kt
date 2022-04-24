package com.diploma.project.controller

import com.diploma.project.model.dto.get.CommentDto
import com.diploma.project.model.dto.create.LeaveCommentDto
import com.diploma.project.service.CommentService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("comment")
class CommentController(
    var commentService: CommentService
) {
    @PostMapping("leave")
    fun leaveComment(@RequestBody comment: LeaveCommentDto): CommentDto {
        return commentService.leaveComment(comment)
    }

    @PostMapping("like")
    fun likeComment(@RequestParam userId: Long, @RequestParam commentId: Long): Boolean {
        return commentService.like(userId, commentId)
    }

    @PostMapping("unlike")
    fun unlikeComment(@RequestParam userId: Long, @RequestParam commentId: Long): Boolean {
        return commentService.unlike(userId, commentId)
    }

    @GetMapping("by-path")
    fun getCommentsByPath(@RequestParam pathId: Long, @RequestParam userId: Long, page: Pageable): Page<CommentDto> {
        return commentService.getCommentsByPath(pathId, userId, page)
    }
}