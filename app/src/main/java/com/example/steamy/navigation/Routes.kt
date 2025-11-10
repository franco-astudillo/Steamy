package com.example.steamy.navigation

object Routes {

    const val HOME = "home"
    const val DETAIL = "detail/{productoId}"

    const val CART = "cart"

    fun detailRoute(productoId: Int) = "detail/$productoId"
}