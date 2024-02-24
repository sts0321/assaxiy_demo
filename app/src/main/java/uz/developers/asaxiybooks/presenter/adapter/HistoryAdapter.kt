package uz.developers.asaxiybooks.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.databinding.ItemBooksBinding

class HistoryAdapter : ListAdapter<MyBooksData, HistoryAdapter.HistoryViewHolder>(InnerDiffUtil) {

    object InnerDiffUtil : DiffUtil.ItemCallback<MyBooksData>() {
        override fun areItemsTheSame(oldItem: MyBooksData, newItem: MyBooksData) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MyBooksData, newItem: MyBooksData) =
            oldItem == newItem

    }

    inner class HistoryViewHolder(val binding: ItemBooksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                val item = getItem(adapterPosition)
                bookName.text = item.bookName
                authorName.text = item.bookAuthor
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            ItemBooksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind()
    }

}

