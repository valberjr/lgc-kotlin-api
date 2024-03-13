package com.example.lgckotlinapi.enums.converters

import com.example.lgckotlinapi.enums.Status
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class StatusConverter : AttributeConverter<Status, String> {
    override fun convertToDatabaseColumn(status: Status): String =
        status.value

    override fun convertToEntityAttribute(value: String): Status =
        Status.entries.firstOrNull { c -> c.value == value }
            ?: throw IllegalArgumentException("Invalid category value: $value")
}