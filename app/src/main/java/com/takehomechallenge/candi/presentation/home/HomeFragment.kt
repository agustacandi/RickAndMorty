package com.takehomechallenge.candi.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.takehomechallenge.candi.base.BaseFragment
import com.takehomechallenge.candi.databinding.FragmentHomeBinding
import com.takehomechallenge.candi.ui.CharacterAdapter
import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.utils.Helper
import com.takehomechallenge.core.utils.ext.gone
import com.takehomechallenge.core.utils.ext.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
        homeViewModel.characters.observe(viewLifecycleOwner) { characters ->
            if (characters != null) {
                binding.apply {
                    when (characters) {
                        is RemoteResponse.Loading -> {
                            showShimmer(true)
                        }

                        is RemoteResponse.Success -> {
                            showShimmer(false)
                            val characterAdapter = CharacterAdapter { id ->
                                HomeFragmentDirections.actionHomeFragmentToDetailFragment(id.toString())
                            }
                            characterAdapter.submitList(characters.data)
                            rvCharacter.apply {
                                adapter = characterAdapter
                                layoutManager = GridLayoutManager(requireContext(), 2)
                            }
                        }

                        is RemoteResponse.Empty -> {
                            showShimmer(false)
                            Helper.showToast(requireActivity(), "Data Empty")
                        }

                        is RemoteResponse.Error -> {
                            showShimmer(false)
                            Helper.showToast(requireActivity(), characters.errorMessage)
                        }
                    }
                }
            }
        }
    }

    override fun initObservers() {
    }

    private fun showShimmer(state: Boolean) {
        binding.apply {
            if (state) {
                shimmerCharacter.root.show()
                rvCharacter.gone()
            } else {
                shimmerCharacter.root.gone()
                rvCharacter.show()
            }
        }
    }

}