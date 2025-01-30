package org.example.model

data class Direccion(
    val calle: String,
    val numero: Int,
    val puerta: String,
    val codigoPostal: Int,
    val ciudad: String,
) {

    override fun toString(): String {
        return "$calle, número: $numero, $puerta, CP: $codigoPostal, $ciudad."
    }

}