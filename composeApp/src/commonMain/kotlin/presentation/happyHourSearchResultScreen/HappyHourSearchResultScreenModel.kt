package presentation.happyHourSearchResultScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.HappyHourRepository
import domain.mapper.toHappyHourCardState
import domain.model.HappyHourVideo
import domain.model.SearchParameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class HappyHourSearchResultScreenModel(
    private val repository: HappyHourRepository
) : ScreenModel {
    private val _searchInProgress = MutableStateFlow(false)

    private val _searchResultHappyHourList = MutableStateFlow<List<HappyHourVideo>>(emptyList())

    val screenState =
        combine(_searchInProgress, _searchResultHappyHourList) { isSearching, resultList ->
            if (isSearching) {
                HappyHourSearchResultScreenState.IsSearching
            } else {
                if (resultList.isEmpty()) {
                    HappyHourSearchResultScreenState.EmptySearchResult
                } else {
                    HappyHourSearchResultScreenState.SearchResult(resultList.map { it.toHappyHourCardState() })
                }
            }
        }.stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(3000),
            HappyHourSearchResultScreenState.IsSearching
        )

    fun search(parameter: SearchParameter) {
        screenModelScope.launch(Dispatchers.IO) {
            _searchInProgress.update { true }
            when (parameter) {
                is SearchParameter.DateSearchParameter -> searchByDate(parameter.date)
                is SearchParameter.PartNumberSearchParameter -> searchByPartNumber(parameter.partNumber)
                is SearchParameter.TextSearchParameter -> searchByText(parameter.searchedText)
            }
            _searchInProgress.update { false }
        }
    }

    private suspend fun searchByDate(searchedDate: LocalDate) {
        val allHappyHours = repository.happyHours()
        _searchResultHappyHourList.update {
            allHappyHours
                .filter { video -> video.publishedDate == searchedDate }
        }
    }

    private suspend fun searchByPartNumber(partNumber: Int) {
        val allHappyHours = repository.happyHours()
        _searchResultHappyHourList.update {
            allHappyHours
                .filter { video -> video.part == partNumber }
        }
    }

    private suspend fun searchByText(searchedText: String) {
        val allHappyHours = repository.happyHours()
        _searchResultHappyHourList.update {
            allHappyHours
                .filter { video -> video.searchString.contains(searchedText) }
        }
    }
}