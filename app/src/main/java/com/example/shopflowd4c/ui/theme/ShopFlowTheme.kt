package com.example.shopflowd4c.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun ShopFlowTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}