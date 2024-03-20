package com.example.lgckotlinapi.dto

import com.example.lgckotlinapi.enums.Category
import com.example.lgckotlinapi.model.Course
import com.example.lgckotlinapi.model.Lesson
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


    fun toEntity(courseDTO: CourseDTO): Course {
        val course: Course = Course(
            id = courseDTO.id,
            name = courseDTO.name,
            category = convertCategoryValue(courseDTO.category)
        )

        val lessons = courseDTO.lessons.stream()
            .map { lessonDTO ->
                Lesson(
                    id = lessonDTO.id,
                    name = lessonDTO.name,
                    youtubeUrl = lessonDTO.youtubeUrl,
                    course = course
                )
            }.collect(Collectors.toList())

        course.lessons = lessons

        return course
    }

    fun convertCategoryValue(value: String): Category =
        when (value) {
            "back-end" -> Category.BACKEND
            "front-end" -> Category.FRONTEND
            else -> throw IllegalArgumentException("Invalid category: $value")
        }
}