package com.diploma.project.controller

import com.diploma.project.model.Account
import com.diploma.project.model.dto.update.ChangePathDto
import com.diploma.project.model.dto.create.CreatePathDto
import com.diploma.project.model.dto.create.CreatePathPointDto
import com.diploma.project.persistence.AccountDao
import com.diploma.project.persistence.PathDao
import com.diploma.project.persistence.PathPointDao
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PathControllerTest {
    @Autowired
    lateinit var pathDao: PathDao

    @Autowired
    lateinit var accountDao: AccountDao

    @Autowired
    lateinit var pathController: PathController

    @Autowired
    lateinit var pathPointDao: PathPointDao

    @BeforeEach
    fun setUp() {
        pathDao.deleteAll()
        pathPointDao.deleteAll()
        accountDao.deleteAll()
        accountDao.save(Account(phone = "8800", name = "Artem", password = "Tolmachev"))
    }

    @Test
    fun `Create path`() {
        val createPointsDto = listOf(
            CreatePathPointDto(
                name = "point1",
                latitude = 1.3,
                longitude = 1.4,
                address = "address1"
            ),
            CreatePathPointDto(
                name = "point2",
                latitude = 2.3,
                longitude = 2.4,
                address = "address2"
            )
        )

        val createPathDto = CreatePathDto(
            authorUserId = 1,
            description = "description",
            name = "name",
            points = createPointsDto
        )

        val createdPathDto = pathController.createPath(createPathDto)
        Assertions.assertNotNull(createdPathDto)
        Assertions.assertNotNull(pathDao.getById(2))
    }

    @Test
    fun `Change existed path`() {
        `Create path`()
        val changePointsDto = listOf(
            CreatePathPointDto(
                name = "point1",
                latitude = 100.3,
                longitude = 1.4,
                address = "new_address1"
            ),
            CreatePathPointDto(
                name = "point2",
                latitude = 2.3,
                longitude = 100.4,
                address = "new_address2"
            ),
            CreatePathPointDto(
                name = "point3",
                latitude = 100.3,
                longitude = 100.4,
                address = "address3"
            )
        )
        pathController.changePath(
            ChangePathDto(2, 1, "new_description", "new_name", changePointsDto)
        )
        val changedPath = pathDao.getById(2)
        Assertions.assertEquals("new_description", changedPath.description)
        Assertions.assertEquals("new_name", changedPath.name)
        val pathPoints = pathPointDao.getAllByPath(changedPath)
        Assertions.assertEquals(3, pathPoints.size)
        Assertions.assertEquals(100.3, pathPoints[0].latitude)
    }
}
