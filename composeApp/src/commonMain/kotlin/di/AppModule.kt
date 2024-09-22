package di

import data.HappyHourRepository
import org.koin.dsl.module
import presentation.MainScreenModel

val appModule = module {
    factory { MainScreenModel(get()) }

    single { HappyHourRepository() }
}