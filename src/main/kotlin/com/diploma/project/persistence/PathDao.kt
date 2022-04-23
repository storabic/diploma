package com.diploma.project.persistence

import com.diploma.project.model.Account
import com.diploma.project.model.Path
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PathDao : JpaRepository<Path, Long> {
    /**
     * 111.111 * DEGREES(ACOS(LEAST(1.0, COS(RADIANS(a.Latitude))
     * COS(RADIANS(b.Latitude))
     * COS(RADIANS(a.Longitude - b.Longitude)) + SIN(RADIANS(a.Latitude))
     * SIN(RADIANS(b.Latitude))))) AS distance_in_km
     */
    @Query(
        "select p from Path p order by abs(DEGREES(ACOS(LEAST(1.0, COS(RADIANS(:latitude))\n" +
                "     * COS(RADIANS(p.beginLatitude))\n" +
                "     * COS(RADIANS(:longitude - p.beginLongitude)) + SIN(RADIANS(:latitude))\n" +
                "     * SIN(RADIANS(p.beginLatitude)))))) ",
        countQuery = "select count(p) from Path p"
    )
    fun getNearPaths(latitude: Double, longitude: Double, pageable: Pageable): Page<Path>

    fun getAllByAuthor(account: Account, pageable: Pageable): Page<Path>
}
