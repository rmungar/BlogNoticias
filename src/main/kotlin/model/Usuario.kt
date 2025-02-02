package org.example.model

data class Usuario(
    var id: String? = null,
    val nombre: String,
    val nick: String,
    val email: String,
    val banned: Boolean,
    val direccion: Direccion,
    val tlfs: List<String>
) {

    init {
        id = email
    }

    override fun toString(): String {
        return "Usuario > ID: $id ; Nombre: $nombre ; Nick $nick ; Banned: $banned ; Direccion: $direccion"
    }


}