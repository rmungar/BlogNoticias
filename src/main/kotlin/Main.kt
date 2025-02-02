package org.example

import org.example.controller.ComentarioController
import org.example.controller.NoticiaController
import org.example.controller.UsuarioController
import org.example.model.Comentario
import org.example.model.Direccion
import org.example.model.Noticia
import org.example.model.Usuario
import java.time.Instant
import java.util.*


fun main() {

    testRegistrarUsuarios()
    testPublicarNoticias()
    testListarNoticiasDeUnUsuario()
    testObtenerComentariosDeUnaNoticia()
    testBuscarNoticiasPorTags()
    listar10UltimasNoticias()
}



fun testRegistrarUsuarios(){

    val UsuarioController = UsuarioController()


    val direccionUsuario1 = Direccion("Buenas tardes",9, "C", 11100, "San Fernando")
    val usuario1 = Usuario(
        _id = null,
        nombre = "Pepe",
        nick = "Ppe",
        direccion = direccionUsuario1,
        banned = false,
        email = "pepe@example.com",
        tlfs = listOf("000000000")
    )

    val direccionUsuario2 = Direccion("Buenas noches",1,"Z",11200, "San Fernando")
    val usuario2 = Usuario(
        _id = null,
        nombre = "Pepa",
        nick = "Ppa",
        direccion = direccionUsuario2,
        banned = true,
        email = "pepa@example.com",
        tlfs = listOf("111111111")
    )

    val direccionUsuario3 = Direccion("Buenas tardes",5,"H",11600, "San Fernando")
    val usuario3 = Usuario(
        _id = null,
        nombre = "",
        nick = "Ppi",
        direccion = direccionUsuario3,
        banned = false,
        email = "pepi@example.com",
        tlfs = listOf("222222222")
    )

    val direccionUsuario4 = Direccion("Buenas Mañanas",5,"J",11900, "San Fernando")
    val usuario4 = Usuario(
        _id = null,
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
        _id = null,
        nombre = "Pepe",
        nick = "Ppe",
        direccion = direccionUsuario1,
        banned = false,
        email = "pepe@example.com",
        tlfs = listOf("000000000")
    )

    val direccionUsuario2 = Direccion("Buenas noches",1,"Z",11200, "San Fernando")
    val usuario2 = Usuario(
        _id = null,
        nombre = "Pepa",
        nick = "Ppa",
        direccion = direccionUsuario2,
        banned = true,
        email = "pepa@example.com",
        tlfs = listOf("111111111")
    )

    val direccionUsuarioNoExistente = Direccion("Ningun Lugar",0, "A", 0, "En ningun lado")
    val usuarioNoExistente = Usuario(
        _id = null,
        nombre = "No existo",
        nick = "Inexistente",
        direccion = direccionUsuarioNoExistente,
        banned = false,
        email = "none@example.com",
        tlfs = listOf("000000000")
    )

    // NOTICIA CON FORMATO CORRECTO Y USUARIO QUE EXITE --> SE PUBLICA
    val noticia1 = Noticia(
        _id = null,
        titulo = "Pepe se hace millonario.",
        contenido = "Pepe, el vecino del barrio se hace millonario tras 30 años de jugar a la Bonoloto.",
        autor = usuario1,
        tags = listOf("Barrio", "Sorprendente", "Dinero"),
        fechaYhora = Date.from(Instant.now()),
    )

    // NOTICIAS DE UN USUARIO QUE EXISTE PERO TIENEN FALLOS EN EL FORMATO --> NO SE PUBLICAN
    val noticia2 = Noticia(
        _id = null,
        titulo = "",
        contenido = "Pepe, el vecino del barrio se hace millonario tras 30 años de jugar a la Bonoloto.",
        autor = usuario1,
        tags = listOf("Barrio", "Sorprendente", "Dinero"),
        fechaYhora = Date.from(Instant.now()),
    )

    val noticia3 = Noticia(
        _id = null,
        titulo = "Pepe se hace millonario.",
        contenido = "",
        autor = usuario1,
        tags = listOf("Barrio", "Sorprendente", "Dinero"),
        fechaYhora = Date.from(Instant.now()),
    )

    // NOTICIA DE UN USUARIO BANNEADO --> NO SE PUBLICAN
    val noticia4 = Noticia(
        _id = null,
        titulo = "Pepe se hace millonario.",
        contenido = "Pepe, el vecino del barrio se hace millonario tras 30 años de jugar a la Bonoloto.",
        autor = usuario2,
        tags = listOf("Barrio", "Sorprendente", "Dinero"),
        fechaYhora = Date.from(Instant.now()),
    )

    // NOTICIA DE UN USUARIO QUE NO EXISTE --> NO SE PUBLICA
    val noticiaInexistente = Noticia(
        _id = null,
        titulo = "Nadie desaparece",
        contenido = "No pasó nada.",
        autor = usuarioNoExistente,
        tags = listOf("Existencial", "Nadie", "Nada"),
        fechaYhora = Date.from(Instant.now()),
    )


    // PUBLICAR UNA NOTICIA CON UN USUARIO REGISTRADO (Revisar log.txt)
    NoticiaController.createNoticia(noticia1)

    // PULICAR NOTICIAS CON FALLOS EN EL FORMATO (Revisar exceptionLog.txt)
    NoticiaController.createNoticia(noticia2)
    NoticiaController.createNoticia(noticia3)

    // PUBLICAR UNA NOTICIA DE UN USUARIO BANNEADO (Revisar exceptionLog.txt)
    NoticiaController.createNoticia(noticia4)


    // PUBLICAR UNA NOTICIA CON UN USUARIO NO REGISTRADO (Revisar exceptionLog.txt)
    NoticiaController.createNoticia(noticiaInexistente)


}

fun testListarNoticiasDeUnUsuario() {

    val NoticiaController = NoticiaController()

    val direccionUsuario1 = Direccion("Buenas tardes",9, "C", 11100, "San Fernando")
    val usuario1 = Usuario(
        _id = null,
        nombre = "Pepe",
        nick = "Ppe",
        direccion = direccionUsuario1,
        banned = false,
        email = "pepe@example.com",
        tlfs = listOf("000000000")
    )


    // NOTICIAS CON FORMATO CORRECTO Y USUARIO QUE EXITE --> SE PUBLICAN
    val noticia1 = Noticia(
        _id = null,
        titulo = "Pepe se hace billonario.",
        contenido = "Pepe, el vecino del barrio se hace billonario.",
        autor = usuario1,
        tags = listOf("Barrio", "Sorprendente", "Dinero"),
        fechaYhora = Date.from(Instant.now()),
    )
    val noticia2 = Noticia(
        _id = null,
        titulo = "Pepe se hace trillonario.",
        contenido = "Pepe, el vecino del barrio se hace trillonario.",
        autor = usuario1,
        tags = listOf("Barrio", "Sorprendente", "Dinero"),
        fechaYhora = Date.from(Instant.now()),
    )
    val noticia3 = Noticia(
        _id = null,
        titulo = "Pepe lo pierde todo.",
        contenido = "Pepe, el vecino del barrio lo pierde todo en el casino.",
        autor = usuario1,
        tags = listOf("Barrio", "Sorprendente", "Dinero"),
        fechaYhora = Date.from(Instant.now()),
    )

    // PUBLICAR 3 NOTICIAS VÁLIDAS PARA LA PRUEBA
    NoticiaController.createNoticia(noticia1)
    NoticiaController.createNoticia(noticia2)
    NoticiaController.createNoticia(noticia3)

    // OBTENER LAS NOTICIAS DE UN USUARIO
    NoticiaController.getNoticiaPorAutor(usuario1._id)
}

fun testObtenerComentariosDeUnaNoticia(){

    val ComentarioController = ComentarioController()

    // USUARIOS DE PRUEBA
    val direccionUsuario1 = Direccion("Buenas tardes",9, "C", 11100, "San Fernando")
    val usuario1 = Usuario(
        _id = null,
        nombre = "Pepe",
        nick = "Ppe",
        direccion = direccionUsuario1,
        banned = false,
        email = "pepe@example.com",
        tlfs = listOf("000000000")
    )

    val direccionUsuario2 = Direccion("Buenas noches",1,"Z",11200, "San Fernando")
    val usuario2 = Usuario(
        _id = null,
        nombre = "Pepa",
        nick = "Ppa",
        direccion = direccionUsuario2,
        banned = true,
        email = "pepa@example.com",
        tlfs = listOf("111111111")
    )

    // NOTICIA DE PRUEBA
    val noticia1 = Noticia(
        _id = null,
        titulo = "Pepe lo pierde todo.",
        contenido = "Pepe, el vecino del barrio lo pierde todo en el casino.",
        autor = usuario1,
        tags = listOf("Barrio", "Sorprendente", "Dinero"),
        fechaYhora = Date.from(Instant.now()),
    )

    // COMENTARIOS DE PRUEBA
    // COMENTARIO DE UNA PERSONA REGISTRADA Y NO BANNEADA (Revisar log.txt)
    val comentario1 = Comentario(
        _id = null,
        contenido = "No me lo puedo creer.",
        noticia = noticia1,
        usuario = usuario1,
        fechaYhora = Date.from(Instant.now()),
    )

    // COMENTARIO DE UNA PERSONA REGISTRADA Y BANNEADA (Revisar exceptionLog.txt)
    val comentario2 = Comentario(
        _id = null,
        contenido = "Se lo merece.",
        noticia = noticia1,
        usuario = usuario2,
        fechaYhora = Date.from(Instant.now()),
    )


    // COMENTARIO DE UNA PERSONA REGISTRADA Y NO BANNEADA (Revisar log.txt)
    ComentarioController.createComentario(comentario1)

    // COMENTARIO DE UNA PERSONA REGISTRADA Y BANNEADA (Revisar exceptionLog.txt)
    ComentarioController.createComentario(comentario2)

    ComentarioController.getComentarioDeNoticia(noticia1._id)

}

fun testBuscarNoticiasPorTags(){

    val NoticiaController = NoticiaController()

    // LISTA DE TAGS PARA BUSCAR NOTICIAS
    val tags = listOf("Barrio", "Nadie", "Nada")

    NoticiaController.getNoticiaPorTags(tags)
}

fun listar10UltimasNoticias(){
    val NoticiaController = NoticiaController()
    NoticiaController.get10UltimasNoticias()
}