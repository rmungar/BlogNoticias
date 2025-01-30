package org.example.service

import org.example.model.Comentario
import org.example.model.Usuario
import org.example.utils.DatabaseConnector

class ComentarioService {

    private val database = DatabaseConnector.getDatabase()
    private val collection = database.getCollection("collComentarios")

    fun createComentario(): Comentario {
        TODO("No se requería en el ejercicio")
    }

    fun updateComentario(comentario: Comentario): Comentario {
        TODO("No se requería en el ejercicio")
    }

    fun getUsuario(): Usuario {
        TODO("No se requería en el ejercicio")
    }

    fun deleteComentario(usuario: Usuario) {
        TODO("No se requería en el ejercicio")
    }
}