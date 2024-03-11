package com.example.lgckotlinapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.hibernate.validator.constraints.Length

@Entity
@SQLDelete(sql = "UPDATE course SET status = 'inactive' WHERE id = ?")
@SQLRestriction("status = 'active'")
class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    val id: Long = 0

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    val name: String = ""

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "back-end|front-end")
    @Column(length = 10, nullable = false)
    val category: String = ""

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "active|inactive")
    @Column(length = 10, nullable = false)
    val status: String = "active"
}