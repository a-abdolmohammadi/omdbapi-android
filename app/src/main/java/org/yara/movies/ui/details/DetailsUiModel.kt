package org.yara.movies.ui.details

import org.yara.movies.data.remote.model.response.details.Details
import org.yara.movies.data.remote.model.response.search.SearchList
import org.yara.movies.ui.main.SearchUiModel

open class DetailsUiModel {

    data class GetResponseSuccess(val detailsResponse: Details) : DetailsUiModel()
    data class GetResponseFailed(val searchResponse: Details) : DetailsUiModel()
    data class ConnectionError(val source: String? = null) :DetailsUiModel()
}