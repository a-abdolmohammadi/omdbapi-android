package org.yara.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.yara.movies.base.BaseFragment
import org.yara.movies.databinding.DetailsFragmentLayoutBinding
import org.yara.movies.ui.details.DetailsUiModel

open class DetailsFragment : BaseFragment() {

    lateinit var imdbid: String
    private lateinit var binding: DetailsFragmentLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            imdbid = HomeFragmentArgs.fromBundle(requireArguments()).imdbId
        }
        viewModel.getDetailsResult(imdbid)
        observeViewState()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DetailsFragmentLayoutBinding.inflate(inflater, container, false)
        binding.loadingObservable = viewModel.loading
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.viewStateDetails.observe(requireActivity()) {
            render(it)
        }
    }

    fun render(uiModel: DetailsUiModel) {
        when (uiModel) {
            is DetailsUiModel.GetResponseSuccess -> {
                binding.detailsModel = uiModel.detailsResponse
            }
            is DetailsUiModel.GetResponseFailed -> {
            }
            is DetailsUiModel.ConnectionError -> {
            }
        }

    }

}