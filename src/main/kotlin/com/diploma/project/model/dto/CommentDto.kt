package com.diploma.project.model.dto

import java.util.*

class CommentDto(
    var id: Long?,
    var text: String?,
    var pathId: Long?,
    var author: AccountDto?,
    var dateCreated: Date?,
    val thumbsUp: Long?
)
