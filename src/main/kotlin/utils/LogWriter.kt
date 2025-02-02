package org.example.utils

import java.io.File
import java.lang.Exception
import java.nio.file.Paths
import java.time.Instant
import java.util.*

object LogWriter {

    private val route = Paths.get("").toAbsolutePath().toString()
    val logDoc = File("$route/src/main/resources/log.txt")
    val exceptionDoc = File("$route/src/main/resources/exceptionLog.txt")

    fun writeLog(text: String) {
        logDoc.appendText("${Date.from(Instant.now())}\n$text\n\n")
    }

    fun writeExceptionLog(exception: Exception) {
        exceptionDoc.appendText("${Date.from(Instant.now())}\n${exception.message.toString()}\n\n")
    }


}