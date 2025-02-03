package org.example.model

import org.bson.codecs.pojo.annotations.BsonId
import java.util.Date

data class Noticia(
    @BsonId
    var _id: String?,
    val titulo: String,
    val contenido: String,
    val autor: Usuario,
    val tags: List<String>,
    val fechaYhora: Date
) {

    init {
        _id = "${autor.nick}-$titulo"
    }

    override fun toString(): String {
        return "Noticia ($titulo) escrita por ${autor.nick} el $fechaYhora."
    }

}