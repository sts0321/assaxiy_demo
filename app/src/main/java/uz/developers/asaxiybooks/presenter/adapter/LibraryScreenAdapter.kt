package com.example.uzummarketclient.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasiyaapp.utils.myLog
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.databinding.ItemInnerListBooksBinding
import uz.developers.asaxiybooks.presenter.adapter.LibraryScreenInnerAdapter

class LibraryScreenAdapter : ListAdapter<MyBooksData, LibraryScreenAdapter.OuterHolder>(
    OuterDiffUtil
) {

    val favouriteLiveData = MutableLiveData<List<MyBooksData>>()

    private var onClickProduct: ((MyBooksData) -> Unit)? = null
    private var onClickCategory: ((MyBooksData) -> Unit)? = null
    private var time = System.currentTimeMillis()

    inner class OuterHolder(val binding: ItemInnerListBooksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapter = LibraryScreenInnerAdapter()

        init {
            binding.btnAll.setOnClickListener {
                if (System.currentTimeMillis() - time >= 500) {
                    time = System.currentTimeMillis()
                    onClickCategory?.invoke(getItem(adapterPosition))
                }
            }
            adapter.setOnClickProduct {
                onClickProduct?.invoke(it)
            }
        }

        init {
            binding.rvListInner.adapter = adapter
            binding.rvListInner.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bind() {
            binding.apply {
                " bu Adapter".myLog()

            }
        }
    }

    object OuterDiffUtil : DiffUtil.ItemCallback<MyBooksData>() {
        override fun areItemsTheSame(
            oldItem: MyBooksData,
            newItem: MyBooksData
        ): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(
            oldItem: MyBooksData,
            newItem: MyBooksData
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterHolder {
        return OuterHolder(
            ItemInnerListBooksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OuterHolder, position: Int) {
        holder.bind()
    }

    fun setOnClickProduct(onClickProduct: ((MyBooksData) -> Unit)) {
        this.onClickProduct = onClickProduct
    }

    fun setOnClickCategory(onClickCategory: ((MyBooksData) -> Unit)) {
        this.onClickCategory = onClickCategory
    }

}