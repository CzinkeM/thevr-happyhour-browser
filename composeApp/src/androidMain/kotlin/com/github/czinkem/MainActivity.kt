package com.github.czinkem

import App
import android.os.Bundle
import androidModule
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import data.getDatabaseBuilder
import di.commonModule
import domain.AppLauncher
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(
                commonModule,
                androidModule,
                module {
                    single { AppLauncher(this@MainActivity) }
                    single { getDatabaseBuilder(this@MainActivity).build() }
                }
            )
        }
        setContent {
            App()
        }
    }
}