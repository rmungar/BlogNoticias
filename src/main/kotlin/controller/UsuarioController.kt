package org.example.controller

import org.example.model.Usuario
import org.example.service.UsuarioService
import org.example.utils.LogWriter
import kotlin.Exception

class UsuarioController {

    private val usuarioService = UsuarioService()


    fun createUser(usuario: Usuario?): Usuario? {
        if (usuario != null) {
            try {
                comprobarDatosUsuario(usuario)
                val result = usuarioService.createUsuario(usuario)
                if (result != null) {
                    LogWriter.writeLog("Creación exitosa de un nuevo usuario.\n$result")
                    return result
                }
                else{
                    LogWriter.writeLog("El usuario ya existe. \n$result")
                    return null
                }
            }
            catch(e:Exception){
                LogWriter.writeExceptionLog(e)
                return null
            }

        }
        else{
            LogWriter.writeLog("El usuario no puede ser nulo.")
            return null
        }
    }

    fun updateUser(){
        TODO("No se requería en el ejercicio")
    }

    fun getUser(id: String?): Usuario? {
        if (id != null) {
            val result = usuarioService.getUsuario(id)
            if (result != null) {
                LogWriter.writeLog("Obtencion de usuario exitosa.\n$result")
                return result
            }
            else {
                LogWriter.writeLog("Ningun usuario con el id: $id.")
                return null
            }
        }
        else {
            LogWriter.writeLog("El id de usuario no puede ser nulo.")
            return null
        }
    }

    fun deleteUser(){
        TODO("No se requería en el ejercicio")
    }


    private fun comprobarDatosUsuario(usuario: Usuario): Boolean{
        if (usuario.id.isNullOrBlank()){
            throw Exception("El id de usuario no puede ser nulo o estar vacío.")
        }
        if (usuario.nombre.isBlank()){
            throw Exception("El nombre del usuario no puede estar vacío.")
        }
        if (usuario.email.isBlank()){
            throw Exception("El email de usuario no puede estar vacío.")
        }
        if (usuario.nick.isBlank()){
            throw Exception("El nick de usuario no puede estar vacío.")
        }
        return true
    }

}