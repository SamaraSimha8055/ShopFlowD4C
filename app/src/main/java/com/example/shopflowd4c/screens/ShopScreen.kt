package com.example.shopflowd4c.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shopflowd4c.R
import com.example.shopflowd4c.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(products: List<Product>) {
    val context = LocalContext.current
    val favorites = remember { mutableStateMapOf<Int, Boolean>() }
    val cartItems = remember { mutableStateMapOf<Int, Boolean>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                Toast.makeText(context, "Favourites List Clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favourites",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {
                Toast.makeText(context, "Cart List Clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        BannerSection()
        CategorySection()

        Text(
            text = "New Products",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        products.forEach { product ->
            val isFavorite = favorites[product.id] ?: false
            val isInCart = cartItems[product.id] ?: false

            ProductCard(
                product = product,
                isFavorite = isFavorite,
                isInCart = isInCart,
                onToggleFavorite = {
                    favorites[product.id] = !isFavorite
                    Toast.makeText(
                        context,
                        "${product.name} ${if (!isFavorite) "added to" else "removed from"} favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onToggleCart = {
                    cartItems[product.id] = !isInCart
                    Toast.makeText(
                        context,
                        "${product.name} ${if (!isInCart) "added to" else "removed from"} cart",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    isFavorite: Boolean,
    isInCart: Boolean,
    onToggleFavorite: () -> Unit,
    onToggleCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // 🔹 Main Card Background
            Image(
                painter = painterResource(id = R.drawable.product_bg_card),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            // 🔸 Favorite Toggle in top-left of the entire card
            IconToggleButton(
                checked = isFavorite,
                onCheckedChange = { onToggleFavorite() },
                modifier = Modifier
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.White
                )
            }

            // 🔸 Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Product Image
                Box(
                    modifier = Modifier
                        .width(250.dp)
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .align(Alignment.CenterHorizontally)
                ) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Product Details
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.product_title_card),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.matchParentSize()
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                product.name,
                                fontWeight = FontWeight.Bold,
                                color = if (product.inStock) Color(0xFFB7F56A) else Color.Red
                            )
                            Text(product.description, style = MaterialTheme.typography.bodySmall,
                                color = Color.White
                            )
                            Text(
                                "Skin Tightness • Dry & Dehydrated Skin",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "₹${product.price}",
                                    color = Color(0xFFB7F56A),
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "₹${product.originalPrice}",
                                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            }
                            Row {
                                repeat(5) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = Color(0xFFFFD700),
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Text("${product.reviewCount} reviews",
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            }
                        }

                        IconToggleButton(
                            checked = isInCart,
                            onCheckedChange = { if (product.inStock) onToggleCart() },
                            enabled = product.inStock
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart",
                                tint = if (isInCart) Color(0xFF3F51B5) else Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BannerSection() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(150.dp)
    ) {
        // Use your drawable/banner image here
        Image(
            painter = painterResource(R.drawable.banner_card),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
        ) {
            Text("GET 20% OFF", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Get 20% off", color = Color.White.copy(alpha = 0.8f))
            Text(
                "12-16 October",
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .background(Color(0xFFB7F56A), RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun CategorySection() {
    val categories = listOf("Skin", "Hair", "Makeup", "Perfume", "Men", "Wellness")

    Column {
        Text(
            text = "Categories",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(categories.size) { category ->
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(80.dp) // Increased size for longer names
                        .background(Color.DarkGray, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = categories[category],
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}