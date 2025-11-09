package com.example.steamy.data.repository

import com.example.steamy.data.model.Producto

class ProductoRepository {

    // Lista simulada de productos
    private val productos = mutableListOf(

        Producto(1,"Battlefield 6","Tarjeta grafica potetente para juegos y IA",2000000.0,"Shooter","https://ac-resources.nyc3.cdn.digitaloceanspaces.com/images/products/20250211_160931_1.png"),
        Producto(2, "Terraria", "Procesador de alto rendimiento para gaming y edición", 1500000.0, "Sandbox", "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcRiLQVoip_O6bXz2ezQwt-_xA0iOM-8ma7vIDF8-QpvSa48JcpkUq0QzKKBkL6nU4ExgFrEOxmIFRkt-ehlMod1J9O3YOH6BNECcnWrULxL1TLYxKnRNChvQA&usqp=CAc"),
        Producto(3, "Red Dead Redemtion 2", "Unidad de estado sólido ultrarrápida para almacenamiento intensivo", 450000.0, "Historia", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcQ18F5_Tor6gJfayn7DzjE4rF4o4UxijMY2e-bvvORIJEcNEL9ysg1wgTuVKEIdsWHqlD6ikyXDtWxa3XSmBXqHmV4DlL46_q_1XMrudDr_OZsyErQgOlQ3FrqKxiwt_3GJTJeSNg&usqp=CAc"),
        Producto(4, "Skyrim Especial edition", "Monitor OLED 4K de 32'' para juegos y diseño profesional", 1800000.0, "Historia, RPG", "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcR4aAeCaSVymm5WZ4cdyhaH7pdMRnCRf3moGJ8i1Ej589cLUyt5AcDKQJT6uRhuQ79dT8idLigpfNmvabnpfxrnV5eRwbjPmNM-g7oNIDZHXdjgm2DfeieuiSacisegwpRW8Ori9g&usqp=CAc"),
        Producto(5, "Shop Titans", "Memoria RAM DDR5 de alto rendimiento para multitarea extrema", 320000.0, "Ecomerce", "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTRLg1qXZ3VSRA9ev_k64rw0ERHpp-MfJXlZ61i3JyNtjqzDl5dXrWz-zZtqlU7WGstGWUyAUq57rZrAurCRZRhBwC_oBvOAAZSn53KEGtBDqsM6vUo9_9KuNTOHA8mLwwC8ShecA&usqp=CAc")
    )

    fun getAllProductos(): List<Producto> = productos

    fun getProductoById(id: Int): Producto? = productos.find { it.id == id }


    fun getProductoByCategoria(categoria: String): Producto? = productos.find { it.categoria == categoria }

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

