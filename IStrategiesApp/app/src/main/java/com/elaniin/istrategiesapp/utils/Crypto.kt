package com.elaniin.istrategiesapp.utils

import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class Crypto {
    @Throws(Exception::class)
    fun encriptar(datos: String, password: String): String? {
        val secretKey: SecretKeySpec = generateKey(password)
        val cipher: Cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val datosEncriptadosBytes: ByteArray = cipher.doFinal(datos.toByteArray())
        return Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT)
    }

    @Throws(Exception::class)
    fun generateKey(password: String): SecretKeySpec {
        val sha = MessageDigest.getInstance("SHA-256")
        var key: ByteArray? = password.toByteArray(charset("UTF-8"))
        key = sha.digest(key)
        return SecretKeySpec(key, "AES")
    }
}