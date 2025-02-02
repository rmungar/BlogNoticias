package org.example.controller

import org.example.model.Noticia
import org.example.service.NoticiaService
import org.example.service.UsuarioService
import org.example.utils.LogWriter
import java.time.Instant
import java.util.*

class NoticiaController {

    private val noticiaService = NoticiaService()
    private val usuarioService = UsuarioService()

    fun createNoticia(noticia: Noticia?): Noticia? {
        if (noticia != null) {
            try {
                comprobarDatosNoticias(noticia)
                val result = noticiaService.createNoticia(noticia)
                if (result != null) {
                    LogWriter.writeLog("Creación exitosa de una nueva noticia.\n$result")
                    return result
                }
                else{
                    LogWriter.writeLog("La noticia ya existe.")
                    return null
                }
            }
            catch (e: Exception){
                LogWriter.writeExceptionLog(e)
                return null
            }
        }
        else {
            LogWriter.writeLog("La noticia no puede ser nula.")
            return null
        }
    }

    fun updateNoticia(noticia: Noticia){
        TODO("No se requería en el ejercicio")
    }

    fun getNoticias(): List<Noticia>?{
        val result = noticiaService.getNoticias()
        if (result != null){
            LogWriter.writeLog("Obtencion de noticias exitosa.\n${result.forEach { "$it\n" }}")
            return result
        }
        else {
            LogWriter.writeLog("No se encontraron noticias.")
            return null
        }
    }

    fun getNoticia(id: String?): Noticia? {
        if (id != null) {
            val result =  noticiaService.getNoticia(id)
            if (result != null) {
                LogWriter.writeLog("Obtención de noticia exitosa.\n$result")
                return result
            }
            else {
                LogWriter.writeLog("Ninguna noticia con el id: $id.")
                return null
            }
        }
        else {
            LogWriter.writeLog("El id de la noticia no puede ser nulo.")
            return null
        }
    }


    fun getNoticiaPorAutor(autorID: String?): List<Noticia>? {
        if (!autorID.isNullOrEmpty() && autorID.isNotBlank()) {
            val result = noticiaService.getNoticiasPorAutor(autorID)
            if (result != null ) {
                LogWriter.writeLog("Obtención de noticias por autor exitosa.\n${result.forEach { "$it\n" }}")
                return result
            }
            else {
                LogWriter.writeLog("No se encontraron noticias para el autor con id: $autorID.")
                return null
            }
        }
        else {
            LogWriter.writeLog("El id de autor no puede ser nulo o estar vacío.")
            return null
        }
    }


    fun deleteNoticia(){
        TODO("No se requería en el ejercicio")
    }


    private fun comprobarDatosNoticias(noticia: Noticia): Boolean{
        if (noticia.id.isNullOrBlank()){
            throw Exception("El id de la noticia no puede ser nulo o estar vacío.")
        }
        if (noticia.titulo.isBlank()){
            throw Exception("El título de la noticia no puede estar vacío.")
        }
        if (noticia.contenido.isBlank()){
            throw Exception("Las noticias han de tener contenido.")
        }
        if (noticia.fechaYhora.after(Date.from(Instant.now()))){
            throw Exception("Fecha no válida.")
        }
        for (tag in noticia.tags){
            if (tag.isBlank()){
                throw Exception("No existen los tags vacios")
            }
        }
        if (usuarioService.getUsuario(noticia.autor.id!!) == null){
            throw Exception("Las noticias solo las pueden publicar usuarios registrados.")
        }
        else if (noticia.autor.banned){
            throw Exception("Los usuarios banneados no pueden publicar noticias.")
        }
        return true
    }

}