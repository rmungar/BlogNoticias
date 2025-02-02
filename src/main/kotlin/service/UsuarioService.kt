package org.example.service

import com.mongodb.client.model.Filters
import org.bson.Document
import org.example.model.Direccion
import org.example.model.Usuario
import org.example.utils.DatabaseConnector
import org.example.utils.LogWriter

class UsuarioService {

    private val database = DatabaseConnector.getDatabase()
    private val collection = database.getCollection("collUsuarios", Usuario::class.java)

    fun createUsuario(usuario: Usuario): Usuario? {
        try {
            val filtroPorID = Filters.eq("id", usuario._id.toString())
            val filtroPorNick = Filters.eq("nick", usuario.nick)
            val usuarioExistente = collection.find(filtroPorID).firstOrNull()
            val usuarioEmailExistente = collection.find(filtroPorNick).firstOrNull()
            if (usuarioExistente != null || usuarioEmailExistente != null) {
                if (usuarioEmailExistente != null) {
                    throw Exception("Ya existe un usuario con ese nickS.")
                }
                else {
                    throw Exception("Ya existe un usuario con ese id.")
                }

            }
            else {
                collection.insertOne(usuario)
                return usuario
            }
        } catch (e:Exception) {
            LogWriter.writeExceptionLog(e)
            return null
        }
    }

    fun updateUsuario(usuario: Usuario) {
        TODO("No se requería en el ejercicio")
    }

    fun getUsuario(id: String): Usuario? {
        try {
            val filtroPorID = Filters.eq("_id", id)
            val usuarioDoc = collection.find(filtroPorID).firstOrNull()
            if (usuarioDoc != null) {
                return usuarioDoc
            }
            else{
                return null
            }
        }catch (e:Exception) {
            LogWriter.writeExceptionLog(e)
            return null
        }
    }

    fun deleteUsuario() {
        TODO("No se requería en el ejercicio")
    }
}