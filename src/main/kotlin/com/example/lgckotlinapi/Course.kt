package com.example.lgckotlinapi

import jakarta.persistence.*

@Entity
class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(length = 200, nullable = false)
    var name: String = ""

    @Column(length = 200, nullable = false)
    var category: String = ""
}