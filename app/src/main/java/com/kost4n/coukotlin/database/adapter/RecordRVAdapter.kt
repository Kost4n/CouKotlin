package com.kost4n.coukotlin.database.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kost4n.coukotlin.R
import com.kost4n.coukotlin.database.entity.RecordEntity

class RecordRVAdapter: ListAdapter<RecordEntity, RecordRVAdapter.RecordHolder>(DiffCallback()) {
    class RecordHolder(view: View): RecyclerView.ViewHolder(view)
    class DiffCallback : DiffUtil.ItemCallback<RecordEntity>() {
        override fun areItemsTheSame(oldItem: RecordEntity, newItem: RecordEntity) =
            oldItem.date == newItem.date

        override fun areContentsTheSame(oldItem: RecordEntity, newItem: RecordEntity) =
            oldItem == newItem
    }

    private lateinit var listener: RecyclerClickListener
    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        val recordHolder = RecordHolder(v)
//        val record = recordHolder.itemView.findViewById<CardView>()
//        record.setOnClickListener{
//            listener.onItemClick(recordHolder.adapterPosition)
//        }


        return recordHolder
    }

    override fun onBindViewHolder(holder: RecordHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

interface RecyclerClickListener {
    fun onItemRemoveClick(position: Int)
    fun onItemClick(position: Int)
}