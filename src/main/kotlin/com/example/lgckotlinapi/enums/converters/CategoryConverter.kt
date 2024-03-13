package com.example.lgckotlinapi.enums.converters

import com.example.lgckotlinapi.enums.Category
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class CategoryConverter : AttributeConverter<Category, String> {
    override fun convertToDatabaseColumn(category: Category): String =
        category.value

    override fun convertToEntityAttribute(value: String): Category =
        Category.entries.firstOrNull { c -> c.value == value }
            ?: throw IllegalArgumentException("Invalid category value: $value")
}