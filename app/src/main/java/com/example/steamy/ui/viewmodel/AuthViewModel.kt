package com.example.steamy.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewModelScope
import com.example.steamy.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _currentUid = MutableStateFlow<String?>(null)
    val currentUid: StateFlow<String?> = _currentUid.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getUidFlow().collect { uid ->
                _currentUid.value = uid
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.login(email, password)
            _authState.value = result.fold(
                onSuccess = { AuthState.Success(it) },
                onFailure = {
                    val errorCode = (it as? FirebaseAuthException)?.errorCode
                    val message = when (errorCode) {
                        "ERROR_INVALID_EMAIL" -> "Correo inválido"
                        else -> "Correo o contraseña incorrectos"
                    }
                    AuthState.Error(message)
                }
            )
        }
    }

    fun register(email: String, password: String, name: String, confirm: String) {
        viewModelScope.launch {
            if (password != confirm) {
                _authState.value = AuthState.Error("Las contraseñas deben coincidir")
                return@launch
            }

            _authState.value = AuthState.Loading
            val result = repository.register(email, password, name)
            _authState.value = result.fold(
                onSuccess = { AuthState.Success(it) },
                onFailure = {
                    val errorCode = (it as? FirebaseAuthException)?.errorCode
                    val message = when (errorCode) {
                        "ERROR_EMAIL_ALREADY_IN_USE" -> "Este correo ya está registrado"
                        "ERROR_INVALID_EMAIL" -> "Correo inválido"
                        else -> "Error al registrar"
                    }
                    AuthState.Error(message)
                }
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
            _authState.value = AuthState.Idle
        }
    }
}