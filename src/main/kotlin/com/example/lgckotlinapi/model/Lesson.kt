package com.example.lgckotlinapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(length = 100, nullable = false)
    val name: String,

    @Column(length = 11, nullable = false)
    val youtubeUrl: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // fix for circular dependency
    val course: Course
)