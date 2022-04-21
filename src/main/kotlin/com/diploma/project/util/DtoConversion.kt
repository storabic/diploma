package com.diploma.project.util

import com.diploma.project.model.Account
import com.diploma.project.model.Comment
import com.diploma.project.model.Path
import com.diploma.project.model.dto.AccountDto
import com.diploma.project.model.dto.CommentDto
import com.diploma.project.model.dto.PathDto

fun Comment.toDto(): CommentDto {
    return CommentDto(
        this.id,
        this.text,
        this.path?.id,
        this.author?.toDto(),
        this.dateCreated,
        this.thumbsUp
    )
}

fun Path.toDto(): PathDto {
    return PathDto(

    )
}

fun Account.toDto(): AccountDto {
    return AccountDto(
        this.id,
        this.name
    )
}
