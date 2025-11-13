package com.example.steamy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.steamy.data.model.Producto
import com.example.steamy.navigation.Routes
import com.example.steamy.ui.theme.AzulHielo
import com.example.steamy.ui.viewmodel.CartViewModel
import com.example.steamy.ui.viewmodel.ProductViewModel

@Composable

fun HomeScreen(
    viewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    onItemClick: (Int) -> Unit,
    navController: NavHostController
) {
    val productos by viewModel.productos.collectAsState()

    // 🔐 Paso 6: Verifica autenticación
    LaunchedEffect(Unit) {
        val user = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
        if (user == null || !user.isEmailVerified) {
            navController.navigate(Routes.LOGIN) {
                popUpTo(Routes.HOME) { inclusive = true }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulHielo) // color claro tipo gris suave
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Bienvenido a Steamy",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }



        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 80.dp)
        ) {
            items(productos) { producto ->
                ItemRow(
                    producto = producto,
                    onClick = { navController.navigate(Routes.detailRoute(producto.id)) },
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}


@Composable
fun ItemRow(producto: Producto, onClick: () -> Unit, cartViewModel: CartViewModel) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                AsyncImage(
                    model = producto.productImg,
                    contentDescription = producto.nombre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = producto.nombre, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = producto.descripcion, fontSize = 14.sp)
                    Text(text = producto.categoria, fontSize = 14.sp)
                    Text(text = "$${producto.precio}", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onClick) {
                    Text("Ver detalles")
                }
                Button(onClick = { cartViewModel.addToCart(producto) }) {
                    Text("Agregar al carrito")
                }
            }
        }
    }
}



