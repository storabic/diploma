package com.diploma.project.model.dto.get

import java.io.Serializable
import java.util.*

class CommentDto(
    var id: Long?,
    var text: String?,
    var pathId: Long?,
    var author: UserDto?,
    var dateCreated: Date?,
    val thumbsUp: Long?
) : Serializable
