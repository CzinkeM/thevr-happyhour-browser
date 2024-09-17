package theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

val happyHourBlack = Color(0xFF211E24)
val happyHourGray = Color(0xFFD8CDDB)
val happyHourPurple = Color(0xFF3a13bb)

val colorScheme = darkColors(
    primary = happyHourPurple,
    onPrimary = happyHourGray,
    background = happyHourBlack,
    onBackground = happyHourGray,
    surface = happyHourGray,
    onSurface = happyHourBlack
)

