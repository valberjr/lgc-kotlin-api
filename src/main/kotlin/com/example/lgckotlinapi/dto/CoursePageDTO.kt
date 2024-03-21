package com.example.lgckotlinapi.dto

class CoursePageDTO(
    val courses: List<CourseDTO>,
    val totalElements: Long,
    val totalPages: Int
)