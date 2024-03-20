package com.example.lgckotlinapi.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length

data class LessonDTO(

    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    val id: Long,

    @NotNull
    @NotBlank
    @Length(min = 10, max = 11)
    val name: String,

    @NotNull
    val youtubeUrl: String
)
