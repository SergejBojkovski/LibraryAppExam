package com.example.libraryexam.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var isFocused by remember { mutableStateOf(false) }
    
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        isError = isError,
        supportingText = supportingText,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = Color.Black
        )
    )
} 