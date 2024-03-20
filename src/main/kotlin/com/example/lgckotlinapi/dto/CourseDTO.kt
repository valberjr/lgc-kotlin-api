package com.example.lgckotlinapi.dto

import com.example.lgckotlinapi.enums.Category
import com.example.lgckotlinapi.enums.validation.ValueOfEnum
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
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
    @ValueOfEnum(enumClass = Category::class)
    @Column(length = 10, nullable = false)
    val category: String,

    @NotNull
    @NotEmpty
    @Valid
    val lessons: List<LessonDTO>
)
