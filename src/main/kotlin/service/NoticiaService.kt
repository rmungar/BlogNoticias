package org.example.service

import com.mongodb.client.model.Filters
import org.bson.Document
import org.example.model.Noticia
import org.example.model.Usuario
import org.example.utils.DatabaseConnector
import org.example.utils.LogWriter

class NoticiaService {

    private val database = DatabaseConnector.getDatabase()
    private val collection = database.getCollection("collNoticias")


    fun createNoticia(noticia: Noticia): Noticia? {
        try{
            val filtroPorId = Filters.eq("id", noticia)
            val noticiaDoc = collection.find(filtroPorId).firstOrNull()
            if(noticiaDoc != null){
                throw Exception("Noticia existente.")
            }
            else {
                collection.insertOne(Document(mapOf("id" to noticia.id, "titulo" to noticia.titulo, "contenido" to noticia.contenido, "autor" to noticia.autor,"tags" to noticia.tags, "fechaYhora" to noticia.fechaYhora)))
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
            val filtroPorId = Filters.eq("id", id)
            val noticiaDoc = collection.find(filtroPorId).firstOrNull()
            if(noticiaDoc != null){
                return Noticia(noticiaDoc.getString("id"), noticiaDoc.getString("titulo"),noticiaDoc.getString("contenido"), noticiaDoc.get("autor", Usuario::class.java), noticiaDoc.getList("tags", String::class.java), noticiaDoc.getDate("fechaYhora"))
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
            collection.find().forEach { noticiaDoc ->
                noticias.add(Noticia(noticiaDoc.getString("id"), noticiaDoc.getString("titulo"),noticiaDoc.getString("contenido"), noticiaDoc.get("autor", Usuario::class.java), noticiaDoc.getList("tags", String::class.java), noticiaDoc.getDate("fechaYhora")))
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
        collection.find(Filters.eq("id", autorID)).forEach { noticiaDoc ->
            listaNoticias.add(Noticia(noticiaDoc.getString("id"), noticiaDoc.getString("titulo"),noticiaDoc.getString("contenido"), noticiaDoc.get("autor", Usuario::class.java), noticiaDoc.getList("tags", String::class.java), noticiaDoc.getDate("fechaYhora")))
        }
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