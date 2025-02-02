package org.example.service

import com.mongodb.client.model.Filters
import org.bson.Document
import org.example.controller.NoticiaController
import org.example.model.Comentario
import org.example.model.Direccion
import org.example.model.Noticia
import org.example.model.Usuario
import org.example.utils.DatabaseConnector
import org.example.utils.LogWriter

class ComentarioService {

    private val database = DatabaseConnector.getDatabase()
    private val collection = database.getCollection("collComentarios", Comentario::class.java)


    fun createComentario(comentario: Comentario): Comentario? {
        try {
            collection.insertOne(comentario)
            return comentario
        }
        catch (e: Exception) {
            LogWriter.writeExceptionLog(e)
            return null
        }
    }

    fun updateComentario(comentario: Comentario): Comentario? {
        TODO("No se requería en el ejercicio")
    }

    fun getComentario(): Comentario? {
        TODO("No se requería en el ejercicio")
    }

    fun getComentarioDeNoticia(noticia: Noticia): List<Comentario>? {
        val listaComentario = mutableListOf<Comentario>()
        collection.find().forEach { comentario ->
            if (comentario.noticia == noticia) {
                listaComentario.add(comentario)
            }
        }
        if (listaComentario.isNotEmpty()) {
            return listaComentario
        }
        else{
            return null
        }
    }

    fun deleteComentario(usuario: Usuario) {
        TODO("No se requería en el ejercicio")
    }
}