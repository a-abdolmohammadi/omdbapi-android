package org.yara.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import org.yara.movies.repository.MainRepository
import org.yara.movies.ui.details.DetailsUiModel
import org.yara.movies.ui.main.SearchUiModel
import org.yara.movies.util.InternetUtil
import javax.inject.Inject

open class MainViewModel
@Inject
constructor(private val repository: MainRepository) : ParentViewModel() {

    private val _viewState = MutableLiveData<SearchUiModel>()
    val viewState: LiveData<SearchUiModel> = _viewState

    private val _viewStateDetails = MutableLiveData<DetailsUiModel>()
    val viewStateDetails: LiveData<DetailsUiModel> = _viewStateDetails

    private fun getSearchResultFromLocal() {
        loading.set(true)
        val disposable: Disposable = repository.loadLocalMovie()
                .subscribe({ movie ->
                    loading.set(false)
                    if (movie.isNotEmpty()) {
                        _viewState.postValue(SearchUiModel.GetResponseSuccess(repository.convertLocalMovieToSearchList(movie)))
                    } else {
                        _viewState.postValue(SearchUiModel.GetResponseFailed(repository.convertLocalMovieToSearchList(movie)))
                    }
                }) {
                    loading.set(false)
                }
        addToUnsubscribed(disposable)
    }

    fun getSearchResult(title: String) {
        if (InternetUtil.isInternetOn()) {
            loading.set(true)
            val disposable: Disposable = repository.getSearch(title.trim())
                    .subscribe({ dataWrapper ->
                        loading.set(false)
                        if (dataWrapper.response == "True") {
                            _viewState.postValue(SearchUiModel.GetResponseSuccess(dataWrapper))
                            repository.deleteCachedBookmark()
                            repository.saveMovieListLocal(repository.convertSearchToLocalMovie(dataWrapper.search))
                        } else {
                            _viewState.postValue(SearchUiModel.GetResponseFailed(dataWrapper))
                        }
                    }) {
                        loading.set(false)
                    }
            addToUnsubscribed(disposable)
        } else {
             getSearchResultFromLocal()
            _viewState.postValue(SearchUiModel.ConnectionError())
        }
    }

    fun getDetailsResult(imdbID: String) {
        if (InternetUtil.isInternetOn()) {
            loading.set(true)
            val disposable: Disposable = repository.getDetails(imdbID.trim())
                    .subscribe({ dataWrapper ->
                        loading.set(false)
                        if (dataWrapper.response == "True") {
                            _viewStateDetails.postValue(DetailsUiModel.GetResponseSuccess(dataWrapper))
                        } else {
                            _viewStateDetails.postValue(DetailsUiModel.GetResponseFailed(dataWrapper))
                        }
                    }) {

                        loading.set(false)
                    }
            addToUnsubscribed(disposable)
        } else {
            _viewState.postValue(SearchUiModel.ConnectionError())
        }
    }
}