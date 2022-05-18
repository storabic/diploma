package com.diploma.project.util

import com.diploma.project.model.Account
import com.diploma.project.model.Comment
import com.diploma.project.model.Path
import com.diploma.project.model.PathPoint
import com.diploma.project.model.dto.get.*

fun Comment.toDto(alreadyLiked: Boolean): CommentDto {
    return CommentDto(
        this.id,
        this.text,
        this.path?.id,
        this.author?.toUserDto(),
        this.dateCreated,
        this.thumbsUp,
        alreadyLiked
    )
}

fun Path.toDto(userRate: Double?): PathDto {
    return PathDto(
        this.id,
        this.author?.id,
        userRate,
        this.description,
        this.name,
        this.currentRating
    )
}

fun PathPoint.toDto(): PathPointDto {
    return PathPointDto(
        this.id,
        this.name,
        this.path?.id,
        this.latitude,
        this.longitude,
        this.address
    )
}

fun Account.toUserDto(): UserDto {
    return UserDto(
        this.id,
        this.name
    )
}

fun Account.toAccountDto(): AccountDto {
    return AccountDto(
        this.id,
        this.name,
        this.phone
    )
}
