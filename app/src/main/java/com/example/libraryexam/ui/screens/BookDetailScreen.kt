package com.example.libraryexam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.libraryexam.data.Book
import com.example.libraryexam.data.BookStatus
import com.example.libraryexam.ui.viewmodels.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    bookId: Int,
    onNavigateBack: () -> Unit,
    viewModel: BookViewModel = viewModel()
) {
    var book by remember { mutableStateOf<Book?>(null) }
    
    LaunchedEffect(bookId) {
        book = viewModel.getBookById(bookId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        book?.let {
                            viewModel.deleteBook(it)
                            onNavigateBack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        book?.let { currentBook ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "ID: ${currentBook.id}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Title:",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = currentBook.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Author:",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = currentBook.author,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Year Published:",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = currentBook.yearPublished.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Status:",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = currentBook.status,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "Summary:",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = currentBook.summary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Button(
                            onClick = {
                                val newStatus = if (currentBook.status == BookStatus.IN_STOCK) {
                                    BookStatus.RENTED_OUT
                                } else {
                                    BookStatus.IN_STOCK
                                }
                                
                                val updatedBook = currentBook.copy(status = newStatus)
                                viewModel.updateBook(updatedBook)
                                book = updatedBook
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (currentBook.status == BookStatus.IN_STOCK) 
                                    "Mark as Rented Out" 
                                else 
                                    "Mark as In Stock"
                            )
                        }
                    }
                }
            }
        } ?: run {
            Text(
                text = "Book not found",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            )
        }
    }
} 