package com.example.libraryexam.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository for handling book data operations
 */
class BookRepository(private val bookDao: BookDao) {
    
    // Get all books ordered by title
    val allBooks: Flow<List<Book>> = bookDao.getAllBooks()
    
    // Get books by status
//    fun getBooksByStatus(status: String): Flow<List<Book>> {
//        return bookDao.getBooksByStatus(status)
//    }
    
    // Get book by ID
    suspend fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)
    }
    
    // Insert a new book
    suspend fun insertBook(book: Book): Long {
        return bookDao.insertBook(book)
    }
    
    // Update an existing book
    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }
    
    // Delete a book
    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }
    

} 