package theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

val happyHourBlack = Color(0xFF211E24)
val happyHourGray = Color(0xFFD8CDDB)
val happyHourPurple = Color(0xFF3a13bb)
val happyHourLightPurple = Color(0xFF8160EF)

val deepRed = Color(0xFFff0000)


val colorScheme = darkColors(
    primary = happyHourPurple,
    primaryVariant = happyHourLightPurple,
    onPrimary = happyHourGray,
    background = happyHourBlack,
    onBackground = happyHourGray,
    surface = happyHourGray,
    onSurface = happyHourBlack,
    error = deepRed,
    onError = Color.White,
)

