package com.example.libraryexam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for Book entities
 */
@Dao
interface BookDao {
    
    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooks(): Flow<List<Book>>
    
    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookById(id: Int): Book?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book): Long
    
    @Update
    suspend fun updateBook(book: Book)
    
    @Delete
    suspend fun deleteBook(book: Book)
    
    @Query("SELECT * FROM books WHERE status = :status ORDER BY title ASC")
    fun getBooksByStatus(status: String): Flow<List<Book>>
    
    @Query("SELECT * FROM books WHERE title LIKE '%' || :searchQuery || '%' OR author LIKE '%' || :searchQuery || '%'")
    fun searchBooks(searchQuery: String): Flow<List<Book>>
} 