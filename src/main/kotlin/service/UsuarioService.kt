package org.example.service

import org.example.model.Usuario
import org.example.utils.DatabaseConnector

class UsuarioService {

    private val database = DatabaseConnector.getDatabase()
    private val collection = database.getCollection("collUsuarios")

    fun createUsuario(): Usuario {
        TODO("No se requería en el ejercicio")
    }

    fun updateUsuario(usuario: Usuario) {
        TODO("No se requería en el ejercicio")
    }

    fun getUsuario(): Usuario {
        TODO("No se requería en el ejercicio")
    }

    fun deleteUsuario() {
        TODO("No se requería en el ejercicio")
    }
}