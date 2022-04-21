package com.diploma.project.model

import javax.persistence.*

@Entity
class Rate(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = 0L,
    @ManyToOne
    var user: Account? = null,
    @ManyToOne
    var comment: Comment? = null,
    @ManyToOne
    var Path: Path? = null
)
