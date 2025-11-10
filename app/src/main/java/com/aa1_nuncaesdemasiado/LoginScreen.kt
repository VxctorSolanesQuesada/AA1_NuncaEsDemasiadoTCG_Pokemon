package com.aa1_nuncaesdemasiado

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aa1_nuncaesdemasiado.ui.theme.AA1_NuncaEsDemasiadoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onContinue: (String) -> Unit,
    onNoAccount: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top block: title + subtitle + explanation
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "NuncaEsDemasiado TCG",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Iniciar sesión con tu cuenta",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Introduce tu correo electrónico para iniciar sesión en esta aplicación",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }

        // Middle block: email + continue + small link
        Column {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("email@domain.com") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { onContinue(email) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Continuar")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "¿No tienes cuenta?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable { onNoAccount() }
            )
        }

        // Bottom block: OR divider, social buttons, terms
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(
                    text = "  o  ",
                    modifier = Modifier.padding(horizontal = 6.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Divider(modifier = Modifier.weight(1f))
            }

            SocialButton(text = "Continuar con Google", background = Color(0xFFEEEEEE)) { /* sin funcionalidad */ }
            Spacer(modifier = Modifier.height(8.dp))
            SocialButton(text = "Continuar con Apple", background = Color.Black, contentColor = Color.White) { /* sin funcionalidad */ }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Al hacer clic en continuar, aceptas nuestros Términos de Servicio y nuestra Política de Privacidad",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SocialButton(
    text: String,
    background: Color,
    contentColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(background, shape = RoundedCornerShape(10.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = contentColor)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun LoginScreenPreview() {
    AA1_NuncaEsDemasiadoTheme {
        LoginScreen(onContinue = {}, onNoAccount = {})
    }
}
