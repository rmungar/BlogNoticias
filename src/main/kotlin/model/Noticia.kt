package org.example.model

import java.util.Date

data class Noticia(
    var id: String? = null,
    val titulo: String,
    val contenido: String,
    val autor: Usuario,
    val tags: List<String>,
    val fechaYhora: Date
) {

    init {
        id = "${autor.nick}-$titulo"
    }

    override fun toString(): String {
        return "Comentario escrito por ${autor.nick} el $fechaYhora."
    }

}