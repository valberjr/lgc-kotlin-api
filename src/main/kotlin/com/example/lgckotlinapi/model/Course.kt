package com.example.lgckotlinapi.model

import com.example.lgckotlinapi.enums.Category
import com.example.lgckotlinapi.enums.Status
import com.example.lgckotlinapi.enums.converters.CategoryConverter
import com.example.lgckotlinapi.enums.converters.StatusConverter
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.hibernate.validator.constraints.Length

@Entity
@SQLDelete(sql = "UPDATE course SET status = 'inactive' WHERE id = ?")
@SQLRestriction("status = 'active'")
class Course(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    val id: Long,

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    val name: String,

    @NotNull
    @Length(max = 10)
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter::class)
    val category: Category,

    @NotNull
    @Length(max = 10)
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter::class)
    val status: Status = Status.ACTIVE,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "course")
    @JsonProperty("lessons")
    val lessons: List<Lesson> = emptyList()
)