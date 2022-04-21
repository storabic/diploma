package com.diploma.project.persistence

import com.diploma.project.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentDao: JpaRepository<Comment, Long> {
}
