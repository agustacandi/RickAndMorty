package com.takehomechallenge.candi.presentation.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.takehomechallenge.candi.R
import com.takehomechallenge.candi.base.BaseFragment
import com.takehomechallenge.candi.databinding.FragmentDetailBinding
import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.domain.character.model.Character
import com.takehomechallenge.core.utils.ConstVal
import com.takehomechallenge.core.utils.Helper
import com.takehomechallenge.core.utils.ext.genderState
import com.takehomechallenge.core.utils.ext.getLocation
import com.takehomechallenge.core.utils.ext.getOrigin
import com.takehomechallenge.core.utils.ext.gone
import com.takehomechallenge.core.utils.ext.show
import com.takehomechallenge.core.utils.ext.speciesState
import com.takehomechallenge.core.utils.ext.statusState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val detailViewModel: DetailViewModel by viewModel()
    private var characterId: String? = null
    private var detailChara: Character? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)

    override fun initIntent() {
        characterId = arguments?.getString(ConstVal.KEY_CHARACTER_ID)
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun initProcess() {
        detailViewModel.apply {
            getCharacterById(characterId ?: "")
            checkFavorite(characterId ?: "")
        }
    }

    override fun initObservers() {
        detailViewModel.detailCharacter.observe(viewLifecycleOwner) { detailCharacter ->
            if (detailCharacter != null) {
                binding.apply {
                    when (detailCharacter) {
                        is RemoteResponse.Loading -> {
                            shimmerDetail.root.show()
                        }

                        is RemoteResponse.Success -> {
                            shimmerDetail.root.gone()
                            detailChara = detailCharacter.data
                            setDetailCharacter(detailCharacter.data)
                        }

                        is RemoteResponse.Empty -> {
                            shimmerDetail.root.gone()
                            Helper.showToast(
                                requireActivity(),
                                getString(R.string.character_not_found)
                            )
                        }

                        is RemoteResponse.Error -> {
                            shimmerDetail.root.gone()
                            Helper.showToast(requireActivity(), detailCharacter.errorMessage)
                        }
                    }
                }
            }
        }

        detailViewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            binding.favoriteButton.apply {
                if (isFavorite) {
                    setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    setImageResource(R.drawable.baseline_favorite_border_24)
                }
                setOnClickListener { buttonFavAction(isFavorite) }
            }
        }
    }

    private fun setDetailCharacter(character: Character) {
        binding.apply {
            image.load(character.image) {
                placeholder(ColorDrawable(Color.LTGRAY))
            }
            name.text = character.name
            status.text = character.status?.statusState()
            species.text = character.species?.speciesState()
            gender.text = character.gender?.genderState()
            location.text = character.location?.name?.getLocation()
            origin.text = character.origin?.name?.getOrigin()
        }
    }

    private fun buttonFavAction(isFavorite: Boolean) {
        detailViewModel.apply {
            if (isFavorite) {
                removeFavorite(characterId ?: "")
            } else {
                val character = Character(
                    id = characterId?.toInt(),
                    name = detailChara?.name,
                    image = detailChara?.image,
                )
                addFavorite(character)
            }
        }
    }

}