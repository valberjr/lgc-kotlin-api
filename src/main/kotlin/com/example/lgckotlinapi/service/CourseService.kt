package com.example.lgckotlinapi.service

import com.example.lgckotlinapi.dto.CourseDTO
import com.example.lgckotlinapi.dto.CourseMapper
import com.example.lgckotlinapi.exception.RecordNotFoundException
import com.example.lgckotlinapi.model.Course
import com.example.lgckotlinapi.repository.CourseRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.stream.Collectors

@Service
@Validated
class CourseService(
    private val repository: CourseRepository,
    private val mapper: CourseMapper
) {

    fun findAll(): List<CourseDTO> = repository
        .findAll()
        .stream()
        .map { mapper.toDTO(it) }
        .collect(Collectors.toList())

    fun findById(@NotNull @Positive id: Long): CourseDTO = repository
        .findById(id)
        .map { mapper.toDTO(it) }
        .orElseThrow { RecordNotFoundException(id) }

    fun save(@Valid @NotNull course: CourseDTO): CourseDTO =
        mapper.toDTO(repository.save(mapper.toEntity(course)))

    fun update(@NotNull @Positive id: Long, @Valid @NotNull courseDTO: CourseDTO): CourseDTO =
        repository
            .findById(id)
            .map { _ ->
                val course = mapper.toEntity(courseDTO)
                val recordFound = Course(courseDTO.id, courseDTO.name, mapper.convertCategoryValue(courseDTO.category))
                recordFound.lessons.clear()
                course.lessons.forEach(recordFound.lessons::add)
                mapper.toDTO(repository.save(recordFound))
            }
            .orElseThrow { RecordNotFoundException(id) }

    fun deleteById(@NotNull @Positive id: Long): Unit =
        repository.delete(repository.findById(id).orElseThrow { RecordNotFoundException(id) })
}