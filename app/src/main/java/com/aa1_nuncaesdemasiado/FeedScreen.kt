package com.aa1_nuncaesdemasiado

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Person as PersonIcon
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.aa1_nuncaesdemasiado.ui.theme.AA1_NuncaEsDemasiadoTheme

data class FeedItem(val id: Int, val title: String, val description: String, val time: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen (onCategoryClick: (FeedItem) -> Unit = {}, onCardClick: (FeedItem) -> Unit = {})  {
    val horizontalState = rememberScrollState()
    val horizontalItems = remember {
        List(8) { i ->
            FeedItem(
                id = i,
                title = "Categoria $i",
                description = "",
                time = ""
            )
        }
    }
    val cardItems = remember {
        List(2) { i ->
            FeedItem(
                id = i,
                title = "Titular destacado ${i + 1}",
                description = "Description duis aute irure dolor reprehenderit in voluptate velit...",
                time = "Today · ${25 + i} min"
            )
        }
    }

    Scaffold(
        bottomBar = { BottomNav() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Header + search
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "ND TCG",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                var query by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Hinted search text") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            // Section title + horizontal scroll icons (más grandes)
            Text(
                text = "Section title",
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(horizontalState)
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                horizontalItems.forEachIndexed { idx, item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(100.dp)
                            .clickable { onCategoryClick(item) } // <-- aquí pasamos el item
                    ) {
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFCCCCCC)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberVectorPainter(Icons.Default.Person),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = item.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Section title + exactly 2 fixed cards
            Text(
                text = "Section title",
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                cardItems.forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* futuro: abrir detalle */ },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(modifier = Modifier.padding(12.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(110.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFBBBBBB))
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = item.description,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = item.time,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun BottomNav() {
    NavigationBar {
        NavigationBarItem(selected = true, onClick = { /* home */ }, icon = {
            Icon(rememberVectorPainter(Icons.Default.Home), contentDescription = "Home")
        }, label = { Text("Home") })
        NavigationBarItem(selected = false, onClick = { /* search */ }, icon = {
            Icon(rememberVectorPainter(Icons.Default.Search), contentDescription = "Search")
        }, label = { Text("Search") })
        NavigationBarItem(selected = false, onClick = { /* add */ }, icon = {
            Icon(rememberVectorPainter(Icons.Default.Add), contentDescription = "Add")
        }, label = { Text("Add") })
        NavigationBarItem(selected = false, onClick = { /* profile */ }, icon = {
            //Icon(rememberVectorPainter(PersonIcon), contentDescription = "Profile")
        }, label = { Text("Profile") })
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun FeedScreenPreview() {
    com.aa1_nuncaesdemasiado.ui.theme.AA1_NuncaEsDemasiadoTheme {
        FeedScreen()
    }
}


