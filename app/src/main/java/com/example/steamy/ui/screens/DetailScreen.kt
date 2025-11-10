package com.example.steamy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.steamy.ui.theme.AzulHielo
import com.example.steamy.ui.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Esta pantalla muestra los detalles de un producto específico.
fun DetailScreen(itemId: Int, viewModel: ProductViewModel, onBack: () -> Unit) {
    val item = viewModel.getProducto(itemId) // Buscamos el producto usando su ID.

    // Usamos Scaffold para estructurar la pantalla con una barra superior.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle", style = MaterialTheme.typography.titleLarge) }, // Título que aparece en la parte superior.
                navigationIcon = {
                    // Botón de retroceso para volver a la pantalla anterior.
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulHielo
                )
            )
        },
        containerColor = AzulHielo
    ) { padding ->
        // Contenido principal de la pantalla.
        Column(
            modifier = Modifier
                .padding(padding) // Respetamos el espacio reservado por el Scaffold.
                .fillMaxSize()  // definimos el ancho.
                .padding(16.dp) // agregamos un margen para que se vea ordenado.
                .background(AzulHielo)
        ) {
            if (item != null) {
                AsyncImage(
                    model = item.productImg, // URL de la imagen.
                    contentDescription = "Imagen de ${item.nombre}", // Descripción para accesibilidad.
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(8.dp)), // Bordes redondeados
                    contentScale = ContentScale.Fit // Recortamos la imagen para que se vea bien.
                )

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp), // separa cada texto
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 24.sp
                    )

                    Text(
                        text = item.descripcion,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "Categoría: ${item.categoria}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "Precio: $${item.precio}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }


            } else {
                // Si no encontramos el producto, mostramos un mensaje de error.
                Text("Item no encontrado")
            }

        }
    }
}