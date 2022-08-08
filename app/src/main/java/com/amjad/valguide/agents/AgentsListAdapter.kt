package com.amjad.valguide.agents

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amjad.valguide.core.domain.model.Agents
import com.amjad.valguide.databinding.AgentItemBinding
import com.bumptech.glide.Glide


class AgentsListAdapter :
    ListAdapter<Agents, AgentsListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = AgentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListViewHolder(private val binding: AgentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Agents) {
            Glide.with(itemView)
                .load(data.displayIcon)
                .into(binding.agentImg)
            Glide.with(itemView)
                .load(data.background)
                .into(binding.bgImg)
            binding.agentTv.text = data.displayName
            binding.root.setOnClickListener {
                val imageViewAgent = binding.agentImg
                val imageViewBackground = binding.bgImg
                val textViewAgent = binding.agentTv
                onItemClickCallback.onItemClicked(data, imageViewAgent,imageViewBackground,textViewAgent)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Agents, imageViewAgent: ImageView, imageViewBackground: ImageView, textViewAgent: TextView)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Agents>() {
            override fun areItemsTheSame(oldItem: Agents, newItem: Agents): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: Agents, newItem: Agents): Boolean {
                return oldItem == newItem
            }
        }
    }
}