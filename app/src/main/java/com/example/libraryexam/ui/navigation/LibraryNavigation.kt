package com.example.libraryexam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.libraryexam.ui.screens.AddBookScreen
import com.example.libraryexam.ui.screens.BookDetailScreen
import com.example.libraryexam.ui.screens.BookListScreen

object Routes {
    const val BOOK_LIST = "book_list"
    const val BOOK_DETAIL = "book_detail/{bookId}"
    const val ADD_BOOK = "add_book"
    
    fun bookDetail(bookId: Int): String = "book_detail/$bookId"
}

@Composable
fun LibraryNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.BOOK_LIST
    ) {
        composable(Routes.BOOK_LIST) {
            BookListScreen(
                onBookClick = { bookId ->
                    navController.navigate(Routes.bookDetail(bookId))
                },
                onAddClick = {
                    navController.navigate(Routes.ADD_BOOK)
                }
            )
        }
        
        composable(
            route = Routes.BOOK_DETAIL,
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            BookDetailScreen(
                bookId = bookId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Routes.ADD_BOOK) {
            AddBookScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
} 