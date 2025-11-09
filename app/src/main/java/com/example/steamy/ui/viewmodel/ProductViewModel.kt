package com.example.steamy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamy.data.model.Producto
import com.example.steamy.data.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


// ViewModel principal que maneja la lógica de productos.
class ProductViewModel(
    private val repository: ProductoRepository = ProductoRepository()

) : ViewModel() {

    // Estado interno que guarda la lista de productos disponibles

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())

    // Exponemos los productos como un flujo de solo lectura para que la UI pueda observarlos.

    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    init{
        // Al iniciar el ViewModel, lanzamos una corrutina para cargar todos los productos desde el repositorio

        viewModelScope.launch {
            _productos.value = repository.getAllProductos()
        }
    }

    // Funcion para obtener un producto especifico por su id

    fun getProducto(id: Int): Producto? = repository.getProductoById(id)

    // Funcion para obtener un producto por su categoria

    fun getProductoCategoria(categoria: String): Producto? = repository.getProductoByCategoria(categoria)
}