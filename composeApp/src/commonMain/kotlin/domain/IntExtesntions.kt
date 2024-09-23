package domain

fun Int?.isNullOrMinus(): Boolean {
    return this == null || this < 0
}