package di

import data.HappyHourRepository
import org.koin.dsl.module
import presentation.happyHourDetailScreen.HappyHourDetailScreenModel
import presentation.happyHourListScreen.HappyHourListScreenModel
import presentation.happyHourSearchResultScreen.HappyHourSearchResultScreenModel

val commonModule = module {
    factory { HappyHourListScreenModel(get()) }
    factory { HappyHourDetailScreenModel(get()) }
    factory { HappyHourSearchResultScreenModel(get()) }
    single { HappyHourRepository(get(), get()) }
}