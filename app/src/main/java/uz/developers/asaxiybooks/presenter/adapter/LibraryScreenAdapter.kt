package uz.developers.asaxiybooks.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasiyaapp.utils.myLog
import uz.developers.asaxiybooks.data.model.CategoryBooksData
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.databinding.ItemInnerListBooksBinding

class LibraryScreenAdapter : ListAdapter<CategoryBooksData, LibraryScreenAdapter.OuterHolder>(
    OuterDiffUtil
) {


    private var onClickBook: ((MyBooksData) -> Unit)? = null
    private var onClickCategory: ((CategoryBooksData) -> Unit)? = null
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
                onClickBook?.invoke(it)
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
                adapter.submitList(getItem(adapterPosition).myBooks)
                categoryName.text=getItem(adapterPosition).name
            }
        }
    }

    object OuterDiffUtil : DiffUtil.ItemCallback<CategoryBooksData>() {
        override fun areItemsTheSame(
            oldItem: CategoryBooksData,
            newItem: CategoryBooksData
        ): Boolean {
            return oldItem.docId == newItem.docId
        }

        override fun areContentsTheSame(
            oldItem: CategoryBooksData,
            newItem: CategoryBooksData
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
        this.onClickBook = onClickProduct
    }

    fun setOnClickCategory(onClickCategory: ((CategoryBooksData) -> Unit)) {
        this.onClickCategory = onClickCategory
    }

}