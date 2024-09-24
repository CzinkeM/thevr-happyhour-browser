package di

import data.HappyHourRepository
import org.koin.dsl.module
import presentation.HappyHourListScreen.HappyHourListScreenModel

val commonModule = module {
    factory { HappyHourListScreenModel(get()) }
    single { HappyHourRepository(get()) }
}