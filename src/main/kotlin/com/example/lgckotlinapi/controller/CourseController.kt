package com.example.lgckotlinapi.controller

import com.example.lgckotlinapi.Course
import com.example.lgckotlinapi.repository.CourseRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
class CourseController(private val courseRepository: CourseRepository) {

    @GetMapping
    fun list(): List<Course> =
        courseRepository.findAll()


    @PostMapping
    fun create(@RequestBody course: Course): Course =
        courseRepository.save(course)

}