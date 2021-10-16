package org.yara.movies.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class ParentViewModel : ViewModel() {

    @JvmField
    var loading = ObservableBoolean(false)

    private val compositeDisposable = CompositeDisposable()

    protected fun addToUnsubscribed(disposable: Disposable?) {
        compositeDisposable.add(disposable!!)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}