package presentation.happyHourSearchResultScreen

import presentation.components.HappyHourCardState

sealed interface HappyHourSearchResultScreenState {
    data object IsSearching: HappyHourSearchResultScreenState
    data object EmptySearchResult: HappyHourSearchResultScreenState
    data class SearchResult(val result: List<HappyHourCardState>): HappyHourSearchResultScreenState
}
