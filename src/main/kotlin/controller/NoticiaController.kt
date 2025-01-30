package org.example.controller

import org.example.model.Noticia
import org.example.service.NoticiaService

class NoticiaController {

    private val noticiaService = NoticiaService()

    fun createNoticia(){

    }

    fun updateNoticia(noticia: Noticia){
        TODO("No se requería en el ejercicio")
    }

    fun getNoticias(): List<Noticia>{
        return emptyList()
    }

    fun deleteNoticia(){
        TODO("No se requería en el ejercicio")
    }

}