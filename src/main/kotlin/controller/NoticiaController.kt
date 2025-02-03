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
                    LogWriter.writeLog("La noticia ya existe.\n$noticia")
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
            LogWriter.writeLog("Obtención de noticias exitosa.\n")
            result.forEach { LogWriter.writeLog("$it\n") }
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
                LogWriter.writeLog("Obtención de noticias por autor exitosa $autorID.\n")
                result.forEach { LogWriter.writeLog("$it\n") }
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

    fun getNoticiaPorTags(tags: List<String>?): List<Noticia>? {
        if (!tags.isNullOrEmpty()) {
            val result = noticiaService.getNoticiasPorTags(tags)
            if (result != null) {
                LogWriter.writeLog("Obtención de noticias por tags ($tags) exitosa.\n")
                result.forEach {
                    LogWriter.writeLog("$it\n")
                }
                return result
            }
            else {
                LogWriter.writeLog("No se encontraron noticias con los tags: $tags.")
                return null
            }
        }
        else {
            LogWriter.writeLog("La lista de tags no puede estar vacía.")
            return null
        }
    }

    fun get10UltimasNoticias(): List<Noticia>?{
        val result = noticiaService.get10UltimasNoticias()
        if (result != null){
            LogWriter.writeLog("Obtención de las ${result.size} últimas noticias exitosa.\n")
            result.forEach { LogWriter.writeLog("$it\n") }
            return result
        }
        else{
            LogWriter.writeExceptionLog(Exception("La base de datos está vacía."))
            return null
        }
    }

    fun deleteNoticia(){
        TODO("No se requería en el ejercicio")
    }

    private fun comprobarDatosNoticias(noticia: Noticia): Boolean{
        if (noticia._id.isNullOrBlank()){
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
                throw Exception("No existen los tags vacíos")
            }
        }
        if (usuarioService.getUsuario(noticia.autor._id!!) == null){
            throw Exception("Las noticias solo las pueden publicar usuarios registrados.")
        }
        else if (noticia.autor.banned){
            throw Exception("Los usuarios baneados no pueden publicar noticias.")
        }
        return true
    }

}