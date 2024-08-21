package com.takehomechallenge.candi.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.takehomechallenge.candi.R
import com.takehomechallenge.candi.base.BaseFragment
import com.takehomechallenge.candi.databinding.FragmentFavoriteBinding
import com.takehomechallenge.candi.ui.CharacterAdapter
import com.takehomechallenge.core.utils.ext.showConfirmDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.fab.setOnClickListener {
            showConfirmDialog(
                title = getString(R.string.are_you_sure),
                message = getString(R.string.this_action_will_remove_all_favorite),
                positiveButtonText = getString(R.string.yes),
                onPositiveClick = { favoriteViewModel.removeAllFavorite() },
                negativeButtonText = getString(R.string.no)
            )
        }
    }

    override fun initProcess() {
        favoriteViewModel.getFavoriteCharacter()
    }

    override fun initObservers() {
        favoriteViewModel.favorite.observe(viewLifecycleOwner) {
            val characterAdapter = CharacterAdapter { id ->
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(id.toString())
            }
            characterAdapter.submitList(it)
            binding.rvFavorite.apply {
                adapter = characterAdapter
                layoutManager = GridLayoutManager(requireActivity(), 2)
            }
        }
    }

}