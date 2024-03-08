package com.example.lgckotlinapi.controller

import com.example.lgckotlinapi.Course
import com.example.lgckotlinapi.repository.CourseRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
class CourseController(private val courseRepository: CourseRepository) {

    @GetMapping
    fun list(): List<Course> =
        courseRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Course> =
        courseRepository.findById(id)
            .map { recordFound -> ResponseEntity.ok().body(recordFound) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody course: Course): Course =
        courseRepository.save(course)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody course: Course): ResponseEntity<Course> =
        courseRepository.findById(id)
            .map { recordFound ->
                recordFound.name = course.name
                recordFound.category = course.category
                val updatedRecord = courseRepository.save(recordFound)
                ResponseEntity.ok().body(updatedRecord)
            }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> =
        courseRepository.findById(id)
            .map { _ ->
                courseRepository.deleteById(id)
                ResponseEntity<Unit>(HttpStatus.NO_CONTENT)
            }
            .orElse(ResponseEntity.notFound().build())
}