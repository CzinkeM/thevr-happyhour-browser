package domain.model

import kotlinx.datetime.LocalDate

sealed interface SearchParameter {
    data class DateSearchParameter(
        val date: LocalDate
    ): SearchParameter


    data class PartNumberSearchParameter(
        val partNumber: Int,
    ): SearchParameter

    data class TextSearchParameter(
        val searchedText: String
    ): SearchParameter
}