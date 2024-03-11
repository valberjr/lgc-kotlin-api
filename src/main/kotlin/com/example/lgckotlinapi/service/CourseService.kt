package com.example.lgckotlinapi.service

import com.example.lgckotlinapi.model.Course
import com.example.lgckotlinapi.repository.CourseRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.*

@Service
@Validated
class CourseService(private val repository: CourseRepository) {

    fun findAll(): List<Course> = repository.findAll()

    fun findById(@NotNull @Positive id: Long): Optional<Course> =
        repository.findById(id);

    fun save(@Valid course: Course): Course = repository.save(course)

    fun update(@NotNull @Positive id: Long, @Valid course: Course): Optional<Course> =
        repository.findById(id)
            .map { recordFound ->
                recordFound.name = course.name
                recordFound.category = course.category
                repository.save(recordFound)
            }

    fun deleteById(@NotNull @Positive id: Long): Boolean =
        repository.findById(id).map { _ ->
            repository.deleteById(id)
            true
        }.orElse(false)
}