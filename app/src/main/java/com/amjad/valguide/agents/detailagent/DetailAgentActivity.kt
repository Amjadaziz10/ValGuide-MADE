package com.amjad.valguide.agents.detailagent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.amjad.valguide.R
import com.amjad.valguide.core.domain.model.Agents
import com.amjad.valguide.databinding.ActivityDetailAgentBinding
import com.amjad.valguide.core.utils.Constants.EXTRA_DATA
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailAgentActivity : AppCompatActivity() {
    private val detailAgentViewModel: DetailAgentViewModel by viewModels()
    private lateinit var binding: ActivityDetailAgentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAgentBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        setupData()

        binding.backBtn.setOnClickListener {
            super.onBackPressed()
        }

        setContentView(binding.root)
    }


    private fun setupData(){
        val data = intent.getParcelableExtra<Agents>(EXTRA_DATA)
        data?.let {
            binding.apply {
                agentDetailTv.text = data.displayName
                biographyDesc.text = data.description
                Glide.with(applicationContext)
                    .load(data.fullPortraitV2)
                    .into(agentDetailImg)
                Glide.with(applicationContext)
                    .load(data.background)
                    .into(bgDetailImg)

                var isFavorite = data.favorite
                setFavoriteBtn(isFavorite)
                binding.favoritesBtn.setOnClickListener {
                    isFavorite = !isFavorite
                    toastFavorite(isFavorite)
                    detailAgentViewModel.setFavoriteAgent(data, isFavorite)
                    setFavoriteBtn(isFavorite)
                }
            }
        }

    }

    private fun setFavoriteBtn(isFavorite: Boolean?){
        if (isFavorite == true) {
            binding.favoritesBtn.setBackgroundResource(R.drawable.button_ability_active)
        } else {
            binding.favoritesBtn.setBackgroundResource(R.drawable.button_ability)
        }
    }

    private fun toastFavorite(isFavorite: Boolean?){
        if (isFavorite == true) {
            Toast.makeText(this, R.string.toast_favorite, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.toast_favorite_no, Toast.LENGTH_SHORT).show()
        }
    }

}