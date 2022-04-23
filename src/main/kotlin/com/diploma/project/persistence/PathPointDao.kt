package com.diploma.project.persistence

import com.diploma.project.model.Path
import com.diploma.project.model.PathPoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PathPointDao : JpaRepository<PathPoint, Long> {
    fun getAllByPath(path: Path): List<PathPoint>

    @Query(
        nativeQuery = true, value = "WITH recursive q(id, name, first, path_id, latitude, longitude, address, next_path_point_id) AS\n" +
                "         (\n" +
                "             SELECT id, name, first, path_id, latitude, longitude, address, next_path_point_id\n" +
                "             FROM path_point p\n" +
                "             WHERE p.first = true\n" +
                "               and p.path_id = ?1\n" +
                "             UNION ALL\n" +
                "             SELECT p.id, p.name, p.first, p.path_id, p.latitude, p.longitude, p.address, p.next_path_point_id\n" +
                "             FROM path_point p\n" +
                "                      JOIN q\n" +
                "                           ON q.next_path_point_id = p.id and p.path_id = ?1\n" +
                "         )\n" +
                "SELECT q.*\n" +
                "FROM q"
    )
    fun getAllByPathSorted(pathId: Long): List<PathPoint>
}
