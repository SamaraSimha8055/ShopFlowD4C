package com.example.shopflowd4c.models

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val originalPrice: Double,
    val imageUrl: String,
    val inStock: Boolean,
    val rating: Float,
    val reviewCount: Int,
    val isBestSeller: Boolean
)