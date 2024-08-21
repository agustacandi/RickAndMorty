package com.takehomechallenge.candi.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.takehomechallenge.candi.base.BaseFragment
import com.takehomechallenge.candi.databinding.FragmentSearchBinding
import com.takehomechallenge.candi.ui.CharacterAdapter
import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.utils.Helper
import com.takehomechallenge.core.utils.ext.gone
import com.takehomechallenge.core.utils.ext.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            searchBar.setOnClickListener {
                searchView.show()
            }
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                val query = searchView.text.toString()
                searchView.hide()
                searchBar.setText(query)
                if (query.isNotBlank() || query.isNotBlank()) {
                    searchViewModel.getCharacterByName(query)
                }
                false
            }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
        searchViewModel.searchResult.observe(viewLifecycleOwner) { searchResult ->
            if (searchResult != null) {
                binding.apply {
                    when (searchResult) {
                        is RemoteResponse.Loading -> {
                            showShimmer(true)
                        }

                        is RemoteResponse.Success -> {
                            showShimmer(false)
                            val characterAdapter = CharacterAdapter { id ->
                                SearchFragmentDirections.actionSearchFragmentToDetailFragment(id.toString())
                            }
                            characterAdapter.submitList(searchResult.data)
                            rvSearchResult.apply {
                                adapter = characterAdapter
                                layoutManager = GridLayoutManager(requireActivity(), 2)
                            }
                        }

                        is RemoteResponse.Error -> {
                            showShimmer(false)
                            if (searchResult.errorMessage.contains("404")) {
                                rvSearchResult.gone()
                                itemNotFound.root.show()
                            } else {
                                Helper.showToast(requireActivity(), searchResult.errorMessage)
                            }
                        }

                        is RemoteResponse.Empty -> {
                            showShimmer(false)
                            Helper.showToast(requireActivity(), "Data is empty")
                        }
                    }
                }
            }
        }

    }

    private fun showShimmer(state: Boolean) {
        binding.apply {
            if (state) {
                shimmerCharacter.root.show()
                rvSearchResult.gone()
                itemNotFound.root.gone()
            } else {
                shimmerCharacter.root.gone()
                rvSearchResult.show()
                itemNotFound.root.gone()
            }
        }
    }

}