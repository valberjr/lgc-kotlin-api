package com.example.lgckotlinapi.controller

import com.example.lgckotlinapi.model.Course
import com.example.lgckotlinapi.service.CourseService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
@Validated
class CourseController(private val service: CourseService) {

    @GetMapping
    fun findAll(): List<Course> = service.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable @NotNull @Positive id: Long): ResponseEntity<Course> =
        service.findById(id)
            .map { recordFound -> ResponseEntity.ok().body(recordFound) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody @Valid course: Course): Course = service.save(course)

    @PutMapping("/{id}")
    fun update(@PathVariable @NotNull @Positive id: Long, @RequestBody @Valid course: Course): ResponseEntity<Course> =
        service.update(id, course)
            .map { recordFound -> ResponseEntity.ok().body(recordFound) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable @NotNull @Positive id: Long): ResponseEntity<Unit> =
        if (service.deleteById(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
}