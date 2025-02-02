package org.example.model

import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

data class Comentario(
    @BsonId
    var _id: String?,
    val contenido: String,
    val noticia: Noticia,
    val usuario: Usuario,
    val fechaYhora: Date
) {

    init {
        _id = usuario._id + noticia._id
    }

    override fun toString(): String {
        return "Comentario escrito por ${usuario.nick} en la noticia: $noticia el $fechaYhora."
    }

}