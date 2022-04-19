package com.diploma.project.model

import javax.persistence.*

@Entity
class PathPoint(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?,

    @ManyToOne(fetch = FetchType.EAGER)
    var path: Path?
) {
    constructor() : this(null, null)
}
