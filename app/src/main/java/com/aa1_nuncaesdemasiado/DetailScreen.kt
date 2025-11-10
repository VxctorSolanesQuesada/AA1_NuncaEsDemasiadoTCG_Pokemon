package com.aa1_nuncaesdemasiado

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.aa1_nuncaesdemasiado.ui.theme.AA1_NuncaEsDemasiadoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(item: FeedItem, onBack: () -> Unit) {
    var selectedLanguage by remember { mutableStateOf("English") }
    val scroll = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = item.title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Imagen grande (misma representación que la del feed, ahora rectangular grande)
            FeedImagePlaceholder(isCircle = false, size = 220.dp)

            Text(text = item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(text = "SIR - PRISMATIC EVOLUTIONS", style = MaterialTheme.typography.bodyMedium)

            // Language toggle
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SegmentedLanguageToggle(selected = selectedLanguage, onSelect = { selectedLanguage = it })
                Text(text = selectedLanguage, style = MaterialTheme.typography.bodyMedium)
            }

            // Prices block
            Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Market Price", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "10€", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Sell Value", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "7,5€", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Button(
                onClick = { /* añadir a lista */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Add to list")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SegmentedLanguageToggle(selected: String, onSelect: (String) -> Unit) {
    Row {
        FilterChip(
            selected = selected == "English",
            onClick = { onSelect("English") },
            label = { Text("English") },
            modifier = Modifier.padding(end = 8.dp)
        )
        FilterChip(
            selected = selected == "Japanese",
            onClick = { onSelect("Japanese") },
            label = { Text("Japanese") }
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 920)
@Composable
fun DetailScreenPreview() {
    AA1_NuncaEsDemasiadoTheme {
        val sample = FeedItem(1, "UMBREON - 176/132", "SIR - PRISMATIC EVOLUTIONS", "Today · 23 min")
        DetailScreen(item = sample, onBack = {})
    }
}

@Composable
fun FeedImagePlaceholder(isCircle: Boolean = true, size: Dp = 96.dp, contentSizeFraction: Float = 0.45f) {
    if (isCircle) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(Color(0xFFCCCCCC)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberVectorPainter(Icons.Default.Person),
                contentDescription = null,
                modifier = Modifier.size((size.value * contentSizeFraction).dp)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFBBBBBB)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberVectorPainter(Icons.Default.Person),
                contentDescription = null,
                modifier = Modifier.size((size.value * contentSizeFraction).dp)
            )
        }
    }
}
