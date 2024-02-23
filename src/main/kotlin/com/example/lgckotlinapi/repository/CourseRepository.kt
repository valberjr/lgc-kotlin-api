package com.example.lgckotlinapi.repository

import com.example.lgckotlinapi.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Long>  {
}