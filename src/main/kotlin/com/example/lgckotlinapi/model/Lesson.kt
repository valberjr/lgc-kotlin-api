package com.example.lgckotlinapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length

@Entity
class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    val name: String,

    @NotNull
    @NotBlank
    @Length(min = 10, max = 11)
    @Column(length = 11, nullable = false)
    val youtubeUrl: String,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // fix for circular dependency
    val course: Course
)