package com.diploma.project.persistence

import com.diploma.project.model.PathPoint
import org.springframework.data.jpa.repository.JpaRepository

interface PathPointDao : JpaRepository<PathPoint, Long> {
}
