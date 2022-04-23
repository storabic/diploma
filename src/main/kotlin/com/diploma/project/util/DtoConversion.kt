package com.diploma.project.util

import com.diploma.project.model.Account
import com.diploma.project.model.Comment
import com.diploma.project.model.Path
import com.diploma.project.model.PathPoint
import com.diploma.project.model.dto.get.UserDto
import com.diploma.project.model.dto.get.CommentDto
import com.diploma.project.model.dto.get.PathDto
import com.diploma.project.model.dto.get.PathPointDto

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
        this.id,
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

fun Account.toDto(): UserDto {
    return UserDto(
        this.id,
        this.name
    )
}
