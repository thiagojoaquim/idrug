package br.com.dcomp.ufs.idrug.common

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

class SHA256Converter {
    companion object {
        fun convert(entry : String) = hashString("SHA-256", entry)

        private fun hashString(algoritmo: String, entrada: String): String {
            val bytes = MessageDigest
                .getInstance(algoritmo)
                .digest(entrada.toByteArray())
            return DatatypeConverter.printHexBinary(bytes)
        }
    }
}