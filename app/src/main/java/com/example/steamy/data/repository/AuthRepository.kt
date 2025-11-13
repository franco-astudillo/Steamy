package com.example.steamy.data.repository


import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepository {

    // ✅ Estas funciones deben ser suspend
    suspend fun login(email: String, password: String): Result<String> {
        return try {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .await()

            val user = FirebaseAuth.getInstance().currentUser
            if (user != null && user.isEmailVerified) {
                Result.success(user.uid)
            } else {
                Result.failure(Exception("Verifica tu correo antes de iniciar sesión"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(email: String, password: String, name: String): Result<String> {
        return try {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .await()

            val user = FirebaseAuth.getInstance().currentUser
            user?.sendEmailVerification()?.await()

            Result.success(user?.uid ?: "")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

    fun getUidFlow(): Flow<String?> = flow {
        emit(FirebaseAuth.getInstance().currentUser?.uid)
    }
}