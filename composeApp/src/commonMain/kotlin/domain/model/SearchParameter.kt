package domain.model

import kotlinx.datetime.LocalDate

sealed interface SearchParameter {

    fun searchInfo(): String

    data class DateSearchParameter(
        val date: LocalDate
    ): SearchParameter {
        override fun searchInfo(): String {
            return date.toString()
        }
    }


    data class PartNumberSearchParameter(
        val partNumber: Int,
    ): SearchParameter {
        override fun searchInfo(): String {
            return partNumber.toString()
        }
    }

    data class TextSearchParameter(
        val searchedText: String
    ): SearchParameter {
        override fun searchInfo(): String {
            return searchedText
        }
    }
}