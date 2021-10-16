package org.yara.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import org.yara.movies.R
import org.yara.movies.base.BaseFragment
import org.yara.movies.data.remote.model.response.search.Search
import org.yara.movies.databinding.HomeFragmentLayoutBinding
import org.yara.movies.ui.adapter.SearchResultAdapter
import org.yara.movies.ui.main.SearchService
import org.yara.movies.ui.main.SearchUiModel
import org.yara.movies.util.InternetUtil
import org.yara.movies.util.ViewUtility


class HomeFragment : BaseFragment(), SearchService {

    lateinit var binding: HomeFragmentLayoutBinding
    var title: String = "batman"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSearchResult(title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentLayoutBinding.inflate(inflater, container, false);
        binding.loadingObservable = viewModel.loading
        binding.btnSearch.setOnClickListener {
            if (InternetUtil.isInternetOn()) {
                if (!binding.editSearch.text.isNullOrEmpty()) {
                    ViewUtility.hideKeyboard(requireContext(), binding.editSearch)
                    title = binding.editSearch.text.toString()
                    viewModel.getSearchResult(title)
                    binding.editSearch.text.clear()
                } else {
                    Toast.makeText(requireContext(), resources.getString(R.string.Please_enter_title), Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireActivity(), resources.getString(R.string.network_connection), Toast.LENGTH_LONG).show()
            }
        }
        binding.btnRetry.setOnClickListener {

            if (InternetUtil.isInternetOn()) {
                viewModel.getSearchResult(title)
                binding.connectionsLayout.visibility = View.GONE
            } else {
                Toast.makeText(requireActivity(), resources.getString(R.string.network_connection), Toast.LENGTH_LONG).show()
                binding.connectionsLayout.visibility = View.VISIBLE
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.viewState.observe(requireActivity()) {
            render(it)
        }
    }

    fun render(uiModel: SearchUiModel) {

        when (uiModel) {
            is SearchUiModel.GetResponseSuccess -> {
                setList(uiModel.searchResponse.search)
                binding.connectionsLayout.visibility = View.GONE
            }
            is SearchUiModel.GetResponseFailed -> {
                binding.connectionsLayout.visibility = View.VISIBLE
            }
            is SearchUiModel.ConnectionError -> {

            }
        }
    }

    private fun setList(list: List<Search>) {
        val adapter = SearchResultAdapter(list, this)
        binding.searchRecycler.adapter = adapter
    }

    override fun onMovieItemClicked(imdbId: String?) {
        val bundle = bundleOf(resources.getString(R.string.bundle_key_title) to imdbId)
        if (InternetUtil.isInternetOn() && !bundle.isEmpty) {
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        } else {
            Toast.makeText(requireActivity(), resources.getString(R.string.network_connection), Toast.LENGTH_LONG).show()
        }
    }
}