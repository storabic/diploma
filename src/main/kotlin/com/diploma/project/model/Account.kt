package com.diploma.project.model

import javax.persistence.*

@Entity
data class Account (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0L,
    var phone: String? = null,
    val name: String? = null,
    var password: String? = null,
)
