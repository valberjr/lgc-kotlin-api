package com.example.lgckotlinapi.dto

import com.example.lgckotlinapi.model.Course
import org.springframework.stereotype.Component

@Component
class CourseMapper {

    fun toDTO(course: Course): CourseDTO = CourseDTO(
        id = course.id,
        name = course.name,
        category = course.category
    )

    fun toEntity(courseDTO: CourseDTO): Course = Course(
        id = courseDTO.id,
        name = courseDTO.name,
        category = courseDTO.category
    )
}