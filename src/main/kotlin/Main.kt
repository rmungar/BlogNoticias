package org.example

import org.example.controller.ComentarioController
import org.example.controller.NoticiaController
import org.example.controller.UsuarioController
import org.example.model.Direccion
import org.example.model.Usuario


fun main() {

    val ComentarioController = ComentarioController()

    //testRegistrarUsuarios()



}



fun testRegistrarUsuarios(){

    val UsuarioController = UsuarioController()


    val direccionUsuario1 = Direccion("Buenas tardes",9, "C", 11100, "San Fernando")
    val usuario1 = Usuario(
        id = null,
        nombre = "Pepe",
        nick = "Ppe",
        direccion = direccionUsuario1,
        banned = false,
        email = "pepe@example.com",
        tlfs = listOf("000000000")
    )

    val direccionUsuario2 = Direccion("Buenas noches",1,"Z",11200, "San Fernando")
    val usuario2 = Usuario(
        id = null,
        nombre = "Pepa",
        nick = "Ppa",
        direccion = direccionUsuario2,
        banned = true,
        email = "pepa@example.com",
        tlfs = listOf("111111111")
    )

    val direccionUsuario3 = Direccion("Buenas tardes",5,"H",11600, "San Fernando")
    val usuario3 = Usuario(
        id = null,
        nombre = "",
        nick = "Ppi",
        direccion = direccionUsuario3,
        banned = false,
        email = "pepi@example.com",
        tlfs = listOf("222222222")
    )

    val direccionUsuario4 = Direccion("Buenas Mañanas",5,"J",11900, "San Fernando")
    val usuario4 = Usuario(
        id = null,
        nombre = "Pepe",
        nick = "Ppe",
        direccion = direccionUsuario4,
        banned = false,
        email = "pepe@example.com",
        tlfs = listOf("000000000")
    )

    // CREAR UN USUARIO SIN FALLOS (Revisar log.txt)
    UsuarioController.createUser(usuario1)
    UsuarioController.createUser(usuario2)

    // CREAR UN USUARIO CON FALLOS (Revisar log.txt y exceptionLog.txt)
    UsuarioController.createUser(usuario3)  // El nombre de usuario no puede estar vacío.
    UsuarioController.createUser(usuario4)  // Nick y email duplicados.

}

fun testPublicarNoticias() {

    val NoticiaController = NoticiaController()

    val direccionUsuario1 = Direccion("Buenas tardes",9, "C", 11100, "San Fernando")
    val usuario1 = Usuario(
        id = null,
        nombre = "Pepe",
        nick = "Ppe",
        direccion = direccionUsuario1,
        banned = false,
        email = "pepe@example.com",
        tlfs = listOf("000000000")
    )




}
