package di

import data.HappyHourRepository
import org.koin.dsl.module
import presentation.happyHourListScreen.HappyHourListScreenModel
import presentation.happyHourDetailScreen.HappyHourDetailScreenModel

val commonModule = module {
    factory { HappyHourListScreenModel(get()) }
    single { HappyHourRepository(get()) }
}