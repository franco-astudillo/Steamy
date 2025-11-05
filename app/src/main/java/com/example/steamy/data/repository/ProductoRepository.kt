package com.example.steamy.data.repository

import com.example.steamy.data.model.Producto

class ProductoRepository {

    // Lista simulada de productos
    private val productos = mutableListOf(
        Producto(1, "Battlefield 6", "La experiencia bélica definitiva. En una guerra de tanques, cazas y gigantescos arsenales de combate, el arma más mortífera es tu patrulla.\n", 62900.0, "https://i.3djuegos.com/juegos/20484/battlefield__2025_/fotos/ficha/battlefield__2025_-5974119.jpg"),
        Producto(2, "Terraria", "¡Cava, lucha, explora, construye! Con este juego de aventuras repleto de acción nada es imposible. ¡Pack de Cuatro también disponible!", 5750.0, "")
    )

    fun getAllProductos(): List<Producto> = productos

    fun getProductoById(id: Int): Producto? = productos.find { it.id == id }

    fun addProducto(producto: Producto) {
        productos.add(producto)
    }

    fun removeProducto(id: Int) {
        productos.removeIf { it.id == id }
    }

    fun updateProducto(producto: Producto) {
        val index = productos.indexOfFirst { it.id == producto.id }
        if (index != -1) {
            productos[index] = producto
        }
    }
}

