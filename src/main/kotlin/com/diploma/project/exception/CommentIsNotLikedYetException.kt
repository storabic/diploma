package com.diploma.project.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class CommentIsNotLikedYetException(userId: Long, userName: String, commentId: Long) :
    Exception("User with id $userId and name $userName didn't like comment with id $commentId yet")