package com.example.steamy.data.repository

import com.example.steamy.data.model.Producto

class ProductoRepository {

    // Lista simulada de productos
    private val productos = mutableListOf(

        Producto(1,"Battlefield 6","La experiencia bélica definitiva. En una guerra de tanques, cazas y gigantescos arsenales de combate, el arma más mortífera es tu patrulla",62900.0,"Disparos","https://cdn1.epicgames.com/offer/a14a02aa3c8143729605eaf7c93d7501/EGS_Battlefield6_BattlefieldStudios_S1_2560x1440-6a45f8547ef32debb4e18b178809d63a"),
        Producto(2, "Terraria", "¡Cava, lucha, explora, construye! Con este juego de aventuras repleto de acción nada es imposible. ¡Pack de Cuatro también disponible!", 5750.0, "Sandbox", "https://i.3djuegos.com/juegos/7475/terraria/fotos/ficha/terraria-2183253.webp"),
        Producto(3, "Red Dead Redemtion 2", "Arthur Morgan y la banda de Van der Linde se ven obligados a huir. Con agentes federales y cazarrecompensas pisándoles los talones, la banda deberá atracar, robar y luchar para sobrevivir en su camino por el escabroso territorio.", 53990.0, "Historia", "https://media.vandal.net/m/42944/red-dead-redemption-2-2019101812583956_1.jpg"),
        Producto(4, "Skyrim Especial edition", "The Elder Scrolls V: Skyrim Special Edition, ganador de más de 200 premios al “Juego del año”, da vida a la fantasía épica con un nivel de detalle asombroso. La Special Edition incluye el juego aclamado por la crítica y complementos con nuevas funcionalidades.", 26990.0, "Historia, RPG", "https://i.3djuegos.com/juegos/18136/the_elder_scrolls_v_skyrim_anniversary_edition/fotos/ficha/the_elder_scrolls_v_skyrim_anniversary_edition-5504232.jpg"),
        Producto(5, "The lord of the rings", "Disfruta de un mundo abierto épico, recreado por el galardonado sistema Némesis. Forja un nuevo Anillo de Poder, conquista fortalezas en grandes batallas y domina Mordor con tu propio ejército de orcos en La Tierra Media™: Sombras de Guerra™.", 37999.0, "Exploracion, RPG", "https://i.3djuegos.com/juegos/15082/la_tierramedia_sombras_de_guerra__mobi_/fotos/ficha/la_tierramedia_sombras_de_guerra__mobi_-3781020.jpg")
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

