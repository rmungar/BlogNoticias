package org.example.model

import org.bson.codecs.pojo.annotations.BsonId

data class Usuario(
    @BsonId
    var _id: String?,
    val nombre: String,
    val nick: String,
    val email: String,
    val banned: Boolean,
    val direccion: Direccion,
    val tlfs: List<String>
) {

    init {
        _id = email
    }

    override fun toString(): String {
        return "Usuario > ID: $_id ; Nombre: $nombre ; Nick $nick ; Banned: $banned ; Direccion: $direccion"
    }


}