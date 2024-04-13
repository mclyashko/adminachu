@file:Repository("https://repo.maven.apache.org/maven2/")
@file:DependsOn("org.assertj:assertj-core:3.8.0")
@file:DependsOn("com.google.code.gson:gson:2.8.5")

import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.jetbrains.kotlin.com.google.common.net.HttpHeaders
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.Base64

val client: HttpClient = HttpClient.newBuilder().build()
val gson = Gson()

data class AddAudioRequest(
    val name: String,
    val description: String,
    val file: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddAudioRequest

        if (name != other.name) return false
        if (description != other.description) return false
        if (!file.contentEquals(other.file)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + file.contentHashCode()
        return result
    }
}

data class UpdateAudioRequest(
    val name: String,
    val description: String,
    val file: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UpdateAudioRequest

        if (name != other.name) return false
        if (description != other.description) return false
        if (!file.contentEquals(other.file)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + file.contentHashCode()
        return result
    }
}

data class AddUserRequest(
    val userName: String,
    val email: String,
    val password: String,
)

data class UpdateUserRequest(
    val userName: String,
    val email: String,
    val password: String,
    val activated: Boolean,
)

fun getBasicAuthenticationHeader(username: String, password: String): String {
    val valueToEncode = "$username:$password"
    return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.toByteArray())
}

fun getAudioFile(fileName: String): ByteArray {
    return File("./audio/${fileName}").readBytes()
}

fun authRequestGetUsers200() {
    val request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("http://localhost:8080/api/user"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestGetUserByEmail200() {
    val email = "admin"

    val request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("http://localhost:8080/api/user?email=${email}"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestGetUserByUserName200() {
    val userName = "admin"

    val request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("http://localhost:8080/api/user?userName=${userName}"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestPostUser200() {
    val body = AddUserRequest(
        userName = "user",
        email = "user",
        password = "user",
    )

    val request = HttpRequest.newBuilder()
        .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
        .uri(URI.create("http://localhost:8080/api/user"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .header(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestUpdateUser200() {
    val id = "7013dbc0-74b1-4e90-ade2-5364750e4684"

    val body = UpdateUserRequest(
        userName = "admin",
        email = "not admin",
        password = "admin",
        activated = true,
    )

    val request = HttpRequest.newBuilder()
        .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
        .uri(URI.create("http://localhost:8080/api/user/id?id=${id}"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .header(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun unauthRequestGetAudios401() {
    val request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("http://localhost:8080/api/audio"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(401)
}

fun authRequestGetAudios200() {
    val request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("http://localhost:8080/api/audio"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestPostAudio200() {
    val body = AddAudioRequest(
        name = "La",
        description = "La",
        file = File("./audio/La.mp3").readBytes(),
    )

    val request = HttpRequest.newBuilder()
        .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
        .uri(URI.create("http://localhost:8080/api/audio"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .header(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestGetAudioById200() {
    val id = "57ee4daa-9a55-4722-96d8-c659dd11b700"

    val request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("http://localhost:8080/api/audio?id=${id}"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestGetAudioByName200() {
    val name = "Drop"

    val request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("http://localhost:8080/api/audio?name=${name}"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestUpdateAudio200() {
    val id = "57ee4daa-9a55-4722-96d8-c659dd11b700"

    val body = UpdateAudioRequest(
        name = "Funky",
        description = "Funky",
        file = File("./audio/Funky.mp3").readBytes(),
    )

    val request = HttpRequest.newBuilder()
        .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
        .uri(URI.create("http://localhost:8080/api/audio/id?id=${id}"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .header(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

fun authRequestRemoveAudio200() {
    val id = "57ee4daa-9a55-4722-96d8-c659dd11b700"

    val request = HttpRequest.newBuilder()
        .DELETE()
        .uri(URI.create("http://localhost:8080/api/audio/id?id=${id}"))
        .header(HttpHeaders.AUTHORIZATION, getBasicAuthenticationHeader("admin", "admin"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    Assertions.assertThat(response.statusCode()).isEqualTo(200)
}

authRequestGetUsers200()
authRequestGetUserByEmail200()
authRequestGetUserByUserName200()
authRequestPostUser200()
authRequestUpdateUser200()
unauthRequestGetAudios401()
authRequestPostAudio200()
authRequestGetAudios200()
authRequestGetAudioById200()
authRequestGetAudioByName200()
authRequestUpdateAudio200()
authRequestRemoveAudio200()

println("Done")
