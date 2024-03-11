package com.example.lgckotlinapi.exception

class RecordNotFoundException(id: Long) : RuntimeException("Record not found with id: $id")