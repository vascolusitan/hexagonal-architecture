package hexagonalarchitecture.crosscutting.utils

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Base64
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParserUtilsTest {

    private class TestObject @JsonCreator constructor(
        @JsonProperty("testId") val testId: String,
        @JsonProperty("description") val description: String
    )

    @Test
    fun `should parse json byte array to object`() {
        // arrange
        val base64 = "ewogICJ0ZXN0SWQiOiAiMzc3MzJlOGMtYWY2Ny00ZmY5LWEyODMtM2NlYjU1ZmNkNGYyIiwKICAiZGVzY3Jpc" +
                "HRpb24iOiAiRXhhbXBsZSB0ZXN0IGRlc2NyaXB0aW9uIgp9"
        val byteArray = Base64.getDecoder().decode(base64)
        val expectedTestObject = TestObject(
            testId = "37732e8c-af67-4ff9-a283-3ceb55fcd4f2",
            description = "Example test description"
        )

        // act
        val result = ParserUtils.parseJsonData(byteArray, TestObject::class.java)

        // assert
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedTestObject)

    }

}