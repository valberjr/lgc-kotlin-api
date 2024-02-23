package com.example.lgckotlinapi

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    var id: Long = 0

    @Column(length = 200, nullable = false)
    var name: String = ""

    @Column(length = 200, nullable = false)
    var category: String = ""
}