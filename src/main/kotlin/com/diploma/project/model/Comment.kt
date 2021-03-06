package com.diploma.project.model

import java.util.*
import javax.persistence.*

@Entity
class Comment (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0L,
    var text: String? = null,
    @ManyToOne
    var path: Path? = null,
    @OneToOne
    var author: Account? = null,
    var dateCreated: Date = Date(),
    var thumbsUp: Long = 0
)
