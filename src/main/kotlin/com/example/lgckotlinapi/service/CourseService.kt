package com.example.lgckotlinapi.service

import com.example.lgckotlinapi.exception.RecordNotFoundException
import com.example.lgckotlinapi.model.Course
import com.example.lgckotlinapi.repository.CourseRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class CourseService(private val repository: CourseRepository) {

    fun findAll(): List<Course> = repository.findAll()

    fun findById(@NotNull @Positive id: Long): Course =
        repository
            .findById(id)
            .orElseThrow { RecordNotFoundException(id) }

    fun save(@Valid course: Course): Course = repository.save(course)

    fun update(@NotNull @Positive id: Long, @Valid course: Course): Course =
        repository
            .findById(id)
            .map { recordFound ->
                recordFound.name = course.name
                recordFound.category = course.category
                repository.save(recordFound)
            }
            .orElseThrow { RecordNotFoundException(id) }

    fun deleteById(@NotNull @Positive id: Long): Unit =
        repository.delete(repository.findById(id).orElseThrow { RecordNotFoundException(id) })
}