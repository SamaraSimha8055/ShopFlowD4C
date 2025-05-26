package com.example.shopflowd4c.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.shopflowd4c.models.Product
import com.example.shopflowd4c.screens.ShopScreen
import com.example.shopflowd4c.ui.theme.ShopFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ShopFlowTheme {
                val products = listOf(
                    Product(
                        1, "Soap", "Mysore Sandal Soap (75 g) – Smallflower",
                        110.00, 130.00,
                        "https://www.smallflower.com/cdn/shop/products/MysoreSandalSoap.jpg?v=1654292884&width=1946",
                        true, 4.5f, 249, true
                    ),
                    Product(
                        2, "Sunscreen", "Haldi & Hyaluronic Acid Sunscreen - 50gm",
                        355.20, 444.00,
                        "https://www.drsheths.com/cdn/shop/files/Shoot_d60b9023-a9bf-4db7-a0fa-c94437f80677.jpg?v=1745474519",
                        true, 4.5f, 249, false
                    ),
                    Product(
                        3, "Toothpaste", "Colgate Active Salt Toothpaste, 100gm – Andaman GreenGrocers",
                        52.20, 60.00,
                        "https://andamangreengrocers.com/wp-content/uploads/2024/07/salt.jpg",
                        false, 4.5f, 249, false
                    )
                )
                ShopScreen(products = products)
            }
        }
    }
}