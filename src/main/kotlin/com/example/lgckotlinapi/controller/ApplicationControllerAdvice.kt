package com.example.lgckotlinapi.controller

import com.example.lgckotlinapi.exception.RecordNotFoundException
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: RecordNotFoundException): String? = ex.message

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: MethodArgumentNotValidException): String =
        ex.bindingResult.fieldErrors.joinToString(separator = "\n") { error ->
            "${error.field} ${error.defaultMessage}"
        }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(ex: ConstraintViolationException): String =
        ex.constraintViolations.joinToString(separator = "\n") { error ->
            "${error.propertyPath} ${error.message}"
        }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException): String =
        if (ex.requiredType != null) {
            val type = ex.requiredType!!.name
            val typeParts = type.split("\\.")
            val typeName = typeParts.last()
            "${ex.name} should be of type $typeName"
        } else {
            "Argument type not valid"
        }

}