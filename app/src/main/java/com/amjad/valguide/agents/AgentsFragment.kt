package com.amjad.valguide.agents

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.valguide.agents.detailagent.DetailAgentActivity
import com.amjad.valguide.core.data.Resource
import com.amjad.valguide.core.domain.model.Agents
import com.amjad.valguide.core.utils.Constants.EXTRA_DATA
import com.amjad.valguide.databinding.FragmentAgentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentsFragment : Fragment() {
    private val agentsViewModel: AgentsViewModel by viewModels()
    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)

        binding.rvAgents.layoutManager = LinearLayoutManager(activity)

        binding.favBtn.setOnClickListener {
            val uri = Uri.parse("valguide://favorites")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        setupData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupData() {
        val adapter = AgentsListAdapter()
        binding.rvAgents.adapter = adapter

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

        agentsViewModel.agents.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading -> binding.pBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.pBar.visibility = View.GONE
                    adapter.submitList(it.data)
                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun showSelectedUser(data: Agents, imageViewAgent: ImageView, imageViewBackground: ImageView, textViewAgent: TextView) {
        val intent = Intent (activity, DetailAgentActivity::class.java)
        intent.putExtra(EXTRA_DATA, data)
        val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity as Activity,
                        Pair(imageViewAgent, "agent"),
                        Pair(textViewAgent, "agent_name"),
                        Pair(imageViewBackground, "background"),
                    )
        startActivity(intent, optionsCompat.toBundle())
    }

}