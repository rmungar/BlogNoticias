package org.example.model

data class Usuario(
    var _id: String? = null,
    val nombre: String,
    val nick: String,
    val email: String,
    val banned: String,
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