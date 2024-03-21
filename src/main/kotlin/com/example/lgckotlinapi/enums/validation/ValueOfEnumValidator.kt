package com.example.lgckotlinapi.enums.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ValueOfEnumValidator : ConstraintValidator<ValueOfEnum, CharSequence> {
    private lateinit var acceptedValues: List<String>

    override fun initialize(annotation: ValueOfEnum) {
        acceptedValues = annotation.enumClass.java.enumConstants
            .map { it.toString() }
    }

    override fun isValid(value: CharSequence?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        return acceptedValues.contains(value.toString())
    }
}
