package com.diploma.project.service.impl

import com.diploma.project.exception.CommentAlreadyLikedException
import com.diploma.project.exception.CommentIsNotLikedYetException
import com.diploma.project.model.Comment
import com.diploma.project.model.dto.get.CommentDto
import com.diploma.project.model.dto.create.LeaveCommentDto
import com.diploma.project.persistence.CommentDao
import com.diploma.project.service.CommentService
import com.diploma.project.service.PathService
import com.diploma.project.service.AccountService
import com.diploma.project.service.RateService
import com.diploma.project.util.toDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    val commentDao: CommentDao
) : CommentService {
    @Autowired
    lateinit var accountService: AccountService

    @Autowired
    lateinit var pathService: PathService

    @Autowired
    lateinit var rateService: RateService

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

    @Transactional
    override fun like(userId: Long, commentId: Long): Boolean {
        val account = accountService.getUser(userId)
        val comment = commentDao.getById(commentId)
        val rate = rateService.getByCommentAndAccount(comment, account)
        if (rate != null) {
            throw CommentAlreadyLikedException(account.id, account.name!!, comment.id)
        }
        rateService.create(account, comment, null)
        ++comment.thumbsUp
        commentDao.save(comment)
        return true
    }

    @Transactional
    override fun unlike(userId: Long, commentId: Long): Boolean {
        val account = accountService.getUser(userId)
        val comment = commentDao.getById(commentId)
        val rate = rateService.getByCommentAndAccount(comment, account)
            ?: throw CommentIsNotLikedYetException(userId, account.name!!, commentId)
        rateService.delete(rate)
        --comment.thumbsUp
        commentDao.save(comment)
        return true
    }

    override fun getCommentsByPath(pathId: Long, page: Pageable): Page<CommentDto> {
        val path = pathService.getPath(pathId)
        return commentDao.findByPath(path, page).map { it.toDto() }
    }
}
