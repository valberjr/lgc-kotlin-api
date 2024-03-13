package com.example.lgckotlinapi.model

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
    val course: Course
)