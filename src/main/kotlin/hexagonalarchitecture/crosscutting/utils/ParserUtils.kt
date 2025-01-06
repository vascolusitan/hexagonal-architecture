package hexagonalarchitecture.crosscutting.utils

import com.fasterxml.jackson.databind.ObjectMapper

object ParserUtils {

    fun <T> parseJsonData(byteArray: ByteArray, clazz: Class<T>): T {
        val jsonString = String(byteArray)
        return ObjectMapper().readValue(jsonString, clazz)
    }

}