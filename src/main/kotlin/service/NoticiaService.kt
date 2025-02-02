package org.example.service

import com.mongodb.client.model.Filters
import org.bson.Document
import org.example.model.Direccion
import org.example.model.Noticia
import org.example.model.Usuario
import org.example.utils.DatabaseConnector
import org.example.utils.LogWriter

class NoticiaService {

    private val database = DatabaseConnector.getDatabase()
    private val collection = database.getCollection("collNoticias", Noticia::class.java)


    fun createNoticia(noticia: Noticia): Noticia? {
        try{
            val filtroPorId = Filters.eq("_id", noticia)
            val noticiaDoc = collection.find(filtroPorId).firstOrNull()
            if(noticiaDoc != null){
                throw Exception("Noticia existente.")
            }
            else {
                collection.insertOne(noticia)
                return noticia
            }
        }
        catch(e:Exception){
            LogWriter.writeExceptionLog(e)
            return null
        }
    }

    fun updateNoticia(noticia: Noticia) {
        TODO("No se requería en el ejercicio")
    }

    fun getNoticia(id: String): Noticia? {
        try {
            val filtroPorId = Filters.eq("_id", id)
            val noticia = collection.find(filtroPorId).firstOrNull()
            if(noticia != null){
                return noticia
            }
            else{
                return null
            }
        }
        catch(e:Exception){
            LogWriter.writeExceptionLog(e)
            return null
        }
    }

    fun getNoticias(): List<Noticia>? {
        try {
            val noticias = mutableListOf<Noticia>()
            collection.find().forEach { noticia ->
                noticias.add(noticia)
            }
            return noticias
        }
        catch(e:Exception){
            LogWriter.writeExceptionLog(e)
            return null
        }

    }

    fun getNoticiasPorAutor(autorID: String): List<Noticia>? {
        val listaNoticias = mutableListOf<Noticia>()
        collection.find(Filters.eq("autor._id", autorID)).forEach { noticia ->

            listaNoticias.add(noticia)
        }
        if(listaNoticias.isNotEmpty()) {
            return listaNoticias
        }
        else {
            return null
        }
    }

    fun getNoticiasPorTags(tags: List<String>): List<Noticia>? {
        val listaNoticias = mutableListOf<Noticia>()
        collection.find().forEach { noticiaDoc ->
            val noticiaActual = noticiaDoc
            if (noticiaActual.tags.isNotEmpty()) {
                noticiaActual.tags.forEach {
                    for (tag in tags) {
                        if (tag == it && !listaNoticias.contains(noticiaActual)) {
                            listaNoticias.add(noticiaActual)
                        }
                    }
                }
            }
        }
        if(listaNoticias.isNotEmpty()) {
            return listaNoticias
        }
        else{
            return null
        }
    }

    fun get10UltimasNoticias(): List<Noticia>? {
        val listaNoticias = mutableListOf<Noticia>()
        collection.find().limit(10).forEach { noticia -> listaNoticias.add(noticia) }
        if(listaNoticias.isNotEmpty()) {
            return listaNoticias
        }
        else {
            return null
        }
    }

    fun deleteNoticias(id: Int) {
        TODO("No se requería en el ejercicio")
    }
}