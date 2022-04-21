package com.diploma.project.persistence

import com.diploma.project.model.Comment
import com.diploma.project.model.Path
import org.springframework.data.jpa.repository.JpaRepository

interface PathDao: JpaRepository<Path, Long> {
}
