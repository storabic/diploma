package com.diploma.project.model

import org.hibernate.annotations.BatchSize
import javax.persistence.*

@Entity
class Path(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = 0L,

    var name: String? = null,

    /**
     * Current average rating, when new user rate comes onto us, we change rate to
     * ([currentRating] * [ratedCount] + newUserRate) / (++[ratedCount])
     */
    var currentRating: Double = 0.0,

    var ratedCount: Long = 0L,

    @BatchSize(size = 25)
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        mappedBy = "path"
    )
    var points: MutableList<PathPoint>? = mutableListOf()
)
