package infrastructure.utils

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

object Utils{
    private val formats = listOf(
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",    // Sem offset
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX", // Com offset (+00:00)
        "yyyy-MM-dd'T'HH:mm:ss'Z'",        // Formato sem frações de segundo
        "yyyy-MM-dd'T'HH:mm:ssXXX"         // Formato sem frações, mas com offset
    )


    fun hashFileName(originalName: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val timeStamp = System.currentTimeMillis().toString()
        val hash = digest.digest("$originalName$timeStamp".toByteArray())
        return hash.joinToString("") { "%02x".format(it) }
    }


    fun toDate(timestamp: String): Date {

        for (format in formats) {
            try {
                val sdf = SimpleDateFormat(format, Locale.getDefault()).apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }
                return sdf.parse(timestamp) ?: Date()
            } catch (e : Exception) {
            }
        }
        throw IllegalArgumentException("Formato de data inválido: $timestamp")
    }

    fun fromDate(date: Date): String{
        for(format in formats){
            try {
                val sdf = SimpleDateFormat(format, Locale.getDefault()).apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }
                return sdf.format(date)
            }catch (e: Exception){
            }
        }
        throw IllegalArgumentException("Formato de data inválido: $date")
    }


}