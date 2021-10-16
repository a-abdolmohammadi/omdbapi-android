package org.yara.movies.ui.main

import org.yara.movies.data.remote.model.response.search.SearchList

sealed class SearchUiModel {
    data class GetResponseSuccess(val searchResponse: SearchList) : SearchUiModel()
    data class GetResponseFailed(val searchResponse: SearchList) : SearchUiModel()
    data class ConnectionError(val source: String? = null) :SearchUiModel()
}
