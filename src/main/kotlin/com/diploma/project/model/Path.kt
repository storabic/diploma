package com.diploma.project.model

import org.hibernate.annotations.BatchSize
import javax.persistence.*

@Entity
data class Path (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,

    @BatchSize(size = 25)
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        mappedBy = "invoice"
    )
    var points: MutableList<PathPoint>
)
