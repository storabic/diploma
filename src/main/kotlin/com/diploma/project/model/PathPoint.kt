package com.diploma.project.model

import javax.persistence.*

//Map<Long, PathPoint> map = new HashMap<>();
//for (PathPoint point: points) {
//    map.put(point.id, point);
//}


@Entity
class PathPoint(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0L,
    var name: String? = null,
    var first: Boolean? = null,
    @ManyToOne(fetch = FetchType.EAGER)
    var path: Path? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var address: String? = null,
    @OneToOne
    var nextPathPoint: PathPoint? = null
)
