package com.example.lgckotlinapi.controller

import com.example.lgckotlinapi.dto.CourseDTO
import com.example.lgckotlinapi.dto.CoursePageDTO
import com.example.lgckotlinapi.service.CourseService
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
@Validated
class CourseController(private val service: CourseService) {

    @GetMapping
    fun findAll(
        @RequestParam(defaultValue = "0") @PositiveOrZero page: Int,
        @RequestParam(defaultValue = "10") @Positive @Max(100) pageSize: Int
    ): CoursePageDTO = service.findAll(page, pageSize)

    @GetMapping("/{id}")
    fun findById(@PathVariable @NotNull @Positive id: Long): CourseDTO = service.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody @Valid course: CourseDTO): CourseDTO = service.save(course)

    @PutMapping("/{id}")
    fun update(@PathVariable @NotNull @Positive id: Long, @RequestBody @Valid course: CourseDTO): CourseDTO =
        service.update(id, course)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable @NotNull @Positive id: Long): Unit = service.deleteById(id)
}