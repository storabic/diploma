package com.diploma.project.persistence

import com.diploma.project.model.Comment
import com.diploma.project.model.Path
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentDao: JpaRepository<Comment, Long> {
    fun findByPath(path: Path, pageable: Pageable): Page<Comment>
}
