package networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import networking.dto.HappyHourPageDto

class HappyHourHttpClient(
    private val client: HttpClient
) {
    suspend fun loadHappyHourPage(targetPage: String): HappyHourPageDto {
        val result = try {
            client.post(urlString = HappyHourUrlProvider.HAPPY_HOURS) {
                contentType(ContentType.MultiPart.FormData)
                setBody(
                    FormDataContent(
                        Parameters.build {
                            append("srcTag", "")
                            append("page", targetPage)
                        }
                    )
                )
            }
        } catch (e: UnresolvedAddressException) {
            TODO()
        } catch (e: SerializationException) {
            TODO()
        }

        return when (result.status.value) {
            in 200..299 -> {
                Json.decodeFromString<HappyHourPageDto>(result.body<String>())
            }

            else -> {
                TODO()
            }
        }
    }
}