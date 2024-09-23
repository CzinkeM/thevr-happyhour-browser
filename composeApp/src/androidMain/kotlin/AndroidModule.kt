import io.ktor.client.engine.okhttp.OkHttp
import networking.HappyHourHttpClient
import networking.createHttpClient
import org.koin.dsl.module

val androidModule = module {
    single { HappyHourHttpClient(createHttpClient(OkHttp.create())) }
}