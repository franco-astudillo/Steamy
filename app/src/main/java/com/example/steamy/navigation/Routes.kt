package com.example.steamy.navigation

object Routes {

    const val HOME = "home"
    const val DETAIL = "detail/{productoId}"

    const val CART = "cart"

    const val LOGIN = "login"

    const val REGISTER = "register"

    fun detailRoute(productoId: Int) = "detail/$productoId"
}