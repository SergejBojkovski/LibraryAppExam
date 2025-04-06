package com.example.libraryexam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a book in the library
 */
@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val author: String,
    val summary: String,
    val yearPublished: Int,
    val status: String // can be In Stock, Rented Out
)

/**
 * Defines possible status values for books
 */
object BookStatus {
    const val IN_STOCK = "In Stock"
    const val RENTED_OUT = "Rented Out"
} 