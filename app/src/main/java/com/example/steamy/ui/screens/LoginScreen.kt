package com.example.steamy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.steamy.ui.components.ButtonModificado
import com.example.steamy.ui.components.TextFieldModificado
import com.example.steamy.ui.theme.AzulHielo
import com.example.steamy.ui.theme.Purple80
import com.example.steamy.ui.viewmodel.AuthViewModel
import com.example.steamy.ui.viewmodel.AuthState

@Composable
// Esta pantalla permite al usuario iniciar sesión en TitanCake.
fun LoginScreen(
    onLogin: (String, String) -> Unit, // Función que se ejecuta cuando el usuario toca "Ingresar".
    onNavigateToRegister: () -> Unit,  // Función que se ejecuta cuando el usuario quiere registrarse.
    onSuccess: () -> Unit,  // Función que se ejecuta cuando el inicio de sesión fue exitoso.
    authViewModel: AuthViewModel,   // ViewModel que maneja el estado de autenticación.
) {
    // Variables para guardar lo que el usuario escribe.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // Variable para mostrar errores locales (como contraseña vacía).
    var localError by remember { mutableStateOf<String?>(null) }

    // Observamos el estado de autenticación (cargando, éxito, error).
    val authState by authViewModel.authState.collectAsState()

    // Si hay un error de autenticación, lo mostramos en pantalla.
    if (authState is AuthState.Error) {
        val errorMessage = (authState as AuthState.Error).message
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(top = 8.dp)
        )
    }


    // Si el estado cambia a éxito, ejecutamos la función de éxito
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onSuccess()
        }
    }

    // Estructura principal de la pantalla.
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize().background(AzulHielo)
                .padding(padding)
        ) {
            // Título de la pantalla.
            Text(
                text = "Inicio sesion",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Purple80,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Campo para escribir el correo.
            TextFieldModificado(email, { email = it }, false, "Correo")
            Spacer(modifier = Modifier.height(16.dp))

            // Campo para escribir la contraseña.
            TextFieldModificado(password, { password = it }, true, "Contraseña")
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para iniciar sesión.
            ButtonModificado("Ingresar", { if (password == "")  {
                localError = "La contraseña no puede estar vacia" // Validamos que la contraseña no esté vacía.

            } else {
                // Si hay un error local, lo mostramos.
                localError = null
                onLogin(email, password)
            }


            })

            localError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))
            // Mostramos un indicador de carga o error según el estado de autenticación.
            when (authState) {
                is AuthState.Loading -> CircularProgressIndicator()
                is AuthState.Error -> Text(
                    text = (authState as AuthState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
                else -> {}
            }

            Spacer(modifier = Modifier.weight(1f))
            // Texto para navegar al registro si el usuario no tiene cuenta.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Registrate",
                    color = AzulHielo,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onNavigateToRegister() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}