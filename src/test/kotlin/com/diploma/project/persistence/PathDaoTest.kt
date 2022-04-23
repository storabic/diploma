package com.diploma.project.persistence

import com.diploma.project.model.Path
import com.diploma.project.model.PathPoint
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PathDaoTest {
    @Autowired
    lateinit var pathDao: PathDao

    @Autowired
    lateinit var pathPointDao: PathPointDao

    @BeforeEach
    fun setUp() {
        pathDao.deleteAll()
        pathPointDao.deleteAll()
    }

    @Test
    fun `Getting sorted path points`() {
        val path = Path(
            1,
            "name",
            "description",
            null,
            0.4,
            0.3,
            4.5,
            500
        )

        val pathPoint2 = PathPoint(
            2,
            "name",
            false,
            path,
            0.3,
            0.5,
            "Москва, Улица Пушкина, дом 16",
            null
        )

        val pathPoint1 = PathPoint(
            1,
            "name",
            true,
            path,
            0.4,
            0.6,
            "Москва, Улица Пушкина, дом 18",
            pathPoint2
        )

        val pathPoints = listOf(pathPoint2, pathPoint1)
        pathDao.save(path)
        pathPointDao.saveAll(pathPoints)

        val res = pathPointDao.getAllByPathSorted(path.id!!)

        Assertions.assertTrue(res[0].first!!)
        Assertions.assertFalse(res[1].first!!)
    }

    @Test
    fun `Getting nearest path`() {
        val path1 = Path(
            1,
            "name",
            "description",
            null,
            0.4,
            0.3,
            4.5,
            500
        )
        pathDao.save(path1)
        val path2 = Path(
            2,
            "name",
            "description",
            null,
            1.2,
            1.3,
            4.5,
            500
        )
        pathDao.save(path2)
        val res = pathDao.getNearPaths(0.0, 0.0, Pageable.ofSize(1))
        Assertions.assertEquals(0.4, res.content[0].beginLatitude)
    }
}
