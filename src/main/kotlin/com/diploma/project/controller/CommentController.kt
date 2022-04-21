package com.diploma.project.controller

import com.diploma.project.model.dto.CommentDto
import com.diploma.project.model.dto.LeaveCommentDto
import com.diploma.project.service.CommentService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/comment")
class CommentController(
    var commentService: CommentService
) {
    @PostMapping("leave")
    fun leaveComment(@RequestBody comment: LeaveCommentDto): CommentDto {
        return commentService.leaveComment(comment)
    }
}