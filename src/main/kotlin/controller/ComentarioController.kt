package org.example.controller

import org.example.model.Comentario
import org.example.service.ComentarioService
import org.example.service.NoticiaService
import org.example.service.UsuarioService
import org.example.utils.LogWriter
import java.time.Instant
import java.util.*

class ComentarioController {

    private val comentarioService = ComentarioService()
    private val noticiaService = NoticiaService()
    private val usuarioService = UsuarioService()

    fun createComentario(comentario: Comentario?): Comentario? {
        try {
            if (comentario != null) {
                comprobarDatosComentario(comentario)
                val result = comentarioService.createComentario(comentario)
                if (result != null) {
                    LogWriter.writeLog("Creaciñón de comentario exitosa.\n $result")
                    return result
                }
                else {
                    return null
                }
            }
            else {
                LogWriter.writeLog("El comentario no puede ser nulo.")
                return null
            }
        }
        catch (e: Exception) {
            LogWriter.writeLog("El comentario no puede ser nulo.")
            return null
        }
    }

    fun updateComentario(comentario: Comentario){
        TODO("No se requería en el ejercicio")
    }

    fun getComentario(){

    }

    fun getComentarioDeNoticia(noticiaID: String?): List<Comentario>? {
        if (!noticiaID.isNullOrEmpty()) {
            val noticia = noticiaService.getNoticia(noticiaID)
            if (noticia != null){
                val result = comentarioService.getComentarioDeNoticia(noticia)
                if (result != null) {
                    LogWriter.writeLog("Obtención de comentarios de una noticia exitoso.\n ${result.forEach { "$it\n" }}")
                    return result
                }
                else{
                    LogWriter.writeLog("No hay comentarios para esta noticia.")
                    return null
                }
            }
            else {
                LogWriter.writeLog("Ninguna noticia con el id: $noticiaID.")
                return null
            }
        }
        else {
            LogWriter.writeLog("El id de la noticia no puede ser nulo o estar vacío.")
            return null
        }
    }


    fun deleteComentario(comentario: Comentario){
        TODO("No se requería en el ejercicio")
    }


    private fun comprobarDatosComentario(comentario: Comentario){
        if (comentario.contenido.isBlank()){
            throw Exception("El contenido de un comentario no puede ser nulo.")
        }
        if (comentario.fechaYhora.after(Date.from(Instant.now()))){
            throw Exception("Fecha no válida para el comentario.")
        }
        if (noticiaService.getNoticia(comentario.noticia.id!!) == null){
            throw Exception("No se puede comentar una noticia inexistente.")
        }
        if (usuarioService.getUsuario(comentario.usuario.id!!) == null){
            throw Exception("Usuario no puede ser nulo.")
        }
        else if (comentario.usuario.banned){
            throw Exception("Un usuario banneado no puede escribir comentarios.")
        }
    }


}