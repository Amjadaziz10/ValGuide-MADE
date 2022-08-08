package com.amjad.valguide.favorites

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.valguide.agents.AgentsListAdapter
import com.amjad.valguide.agents.detailagent.DetailAgentActivity
import com.amjad.valguide.core.domain.model.Agents
import com.amjad.valguide.core.utils.Constants
import com.amjad.valguide.di.FavoritesModuleDependencies
import com.amjad.valguide.favorites.databinding.ActivityFavoritesBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoritesActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val favoritesViewModel: FavoritesViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoritesComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoritesModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityFavoritesBinding.inflate(layoutInflater)

        binding.rvFavorites.layoutManager = LinearLayoutManager(this)

        setupData()

        setContentView(binding.root)
    }

    private fun setupData() {
        val adapter = AgentsListAdapter()
        binding.rvFavorites.adapter = adapter

        adapter.setOnItemClickCallback(object : AgentsListAdapter.OnItemClickCallback{
            override fun onItemClicked(
                data: Agents,
                imageViewAgent: ImageView,
                imageViewBackground: ImageView,
                textViewAgent: TextView
            ) {
                showSelectedUser(data, imageViewAgent,imageViewBackground,textViewAgent)
            }


        })

        favoritesViewModel.agents.observe(this) {
            adapter.submitList(it)
            binding.emptyView.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun showSelectedUser(data: Agents, imageViewAgent: ImageView, imageViewBackground: ImageView, textViewAgent: TextView) {
        val intent = Intent (this, DetailAgentActivity::class.java)
        intent.putExtra(Constants.EXTRA_DATA, data)
        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(imageViewAgent, "agent"),
                Pair(textViewAgent, "agent_name"),
                Pair(imageViewBackground, "background"),
            )
        startActivity(intent, optionsCompat.toBundle())
    }
}