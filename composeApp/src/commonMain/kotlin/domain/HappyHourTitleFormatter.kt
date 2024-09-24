package domain

object HappyHourTitleFormatter {
    fun format(title: String): String {
        return title.split('|')[0]
    }
}