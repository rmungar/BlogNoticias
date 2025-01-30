package org.example.model

import java.util.Date

data class Noticia(
    val titulo: String,
    val contenido: String,
    val autor: Usuario,
    val tags: List<String>,
    val fechaYhora: Date
) {

    override fun toString(): String {
        return "Comentario escrito por ${autor.nick} el $fechaYhora."
    }

}