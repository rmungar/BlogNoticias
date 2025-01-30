package org.example.service

import org.example.model.Noticia
import org.example.utils.DatabaseConnector

class NoticiaService {

    private val database = DatabaseConnector.getDatabase()
    private val collection = database.getCollection("collNoticias")


    fun createNoticia(noticia: Noticia) {
        TODO("No se requería en el ejercicio")
    }

    fun updateNoticia(noticia: Noticia) {
        TODO("No se requería en el ejercicio")
    }

    fun getNoticias(): List<Noticia> {
        TODO("No se requería en el ejercicio")
    }

    fun deleteNoticias(id: Int) {
        TODO("No se requería en el ejercicio")
    }
}