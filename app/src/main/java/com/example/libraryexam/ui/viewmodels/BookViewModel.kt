package com.example.libraryexam.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryexam.data.Book
import com.example.libraryexam.data.BookRepository
import com.example.libraryexam.data.LibraryDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: BookRepository
    val allBooks: Flow<List<Book>>
    
    init {
        val bookDao = LibraryDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        allBooks = repository.allBooks
    }
    
    fun addBook(book: Book) {
        viewModelScope.launch {
            repository.insertBook(book)
        }
    }
    
    fun updateBook(book: Book) {
        viewModelScope.launch {
            repository.updateBook(book)
        }
    }
    
    fun deleteBook(book: Book) {
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }
    
    suspend fun getBookById(id: Int): Book? {
        return repository.getBookById(id)
    }
    

} 