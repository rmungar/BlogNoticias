package org.example.model

import java.util.*

data class Comentario(
    val contenido: String,
    val noticia: Noticia,
    val usuario: Usuario,
    val fechaYhora: Date
) {

    override fun toString(): String {
        return "Comentario escrito por ${usuario.nick} en la noticia: $noticia el $fechaYhora."
    }

}