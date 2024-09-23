package di

import data.HappyHourRepository
import org.koin.dsl.module
import presentation.MainScreenModel

val commonModule = module {
    factory { MainScreenModel(get(),get()) }
    single { HappyHourRepository() }
}