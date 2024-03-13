package com.example.lgckotlinapi.dto

import com.example.lgckotlinapi.model.Lesson
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class CourseDTO(

    @JsonProperty("_id")
    val id: Long = 0,

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    val name: String,

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "back-end|front-end")
    @Column(length = 10, nullable = false)
    val category: String,

    val lessons: List<Lesson>
)
