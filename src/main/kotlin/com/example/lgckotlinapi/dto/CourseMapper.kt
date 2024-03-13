package com.example.lgckotlinapi.dto

import com.example.lgckotlinapi.enums.Category
import com.example.lgckotlinapi.model.Course
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class CourseMapper {

    fun toDTO(course: Course): CourseDTO {
        val lessons: List<LessonDTO> = course.lessons
            .stream()
            .map { lesson ->
                LessonDTO(
                    id = lesson.id,
                    name = lesson.name,
                    youtubeUrl = lesson.youtubeUrl
                )
            }
            .collect(Collectors.toList())

        return CourseDTO(
            id = course.id,
            name = course.name,
            category = course.category.value,
            lessons = lessons
        )
    }


    fun toEntity(courseDTO: CourseDTO): Course = Course(
        id = courseDTO.id,
        name = courseDTO.name,
        category = convertCategoryValue(courseDTO.category)
    )

    fun convertCategoryValue(value: String): Category =
        when (value) {
            "back-end" -> Category.BACKEND
            "front-end" -> Category.FRONTEND
            else -> throw IllegalArgumentException("Invalid category: $value")
        }
}