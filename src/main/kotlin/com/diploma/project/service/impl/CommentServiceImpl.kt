package com.diploma.project.service.impl

import com.diploma.project.model.Comment
import com.diploma.project.model.dto.CommentDto
import com.diploma.project.model.dto.LeaveCommentDto
import com.diploma.project.persistence.CommentDao
import com.diploma.project.service.CommentService
import com.diploma.project.service.PathService
import com.diploma.project.service.AccountService
import com.diploma.project.util.toDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    val commentDao: CommentDao
) : CommentService {
    @Autowired
    lateinit var accountService: AccountService
    @Autowired
    lateinit var pathService: PathService

    override fun leaveComment(commentDto: LeaveCommentDto): CommentDto {
        val author = accountService.getUser(commentDto.userId)
        val path = pathService.getPath(commentDto.pathId)
        val comment = Comment(
            text = commentDto.text,
            path = path,
            author = author
        )
        commentDao.save(comment)
        return comment.toDto()
    }
}
