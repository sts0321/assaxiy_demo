package uz.developers.asaxiybooks.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.databinding.ItemBooksBinding

class HistoryAdapter : ListAdapter<MyBooksData, HistoryAdapter.HistoryViewHolder>(InnerDiffUtil) {

    private var itemClickListener: ((MyBooksData) -> Unit)? = null
    private var time = System.currentTimeMillis()

    object InnerDiffUtil : DiffUtil.ItemCallback<MyBooksData>() {
        override fun areItemsTheSame(oldItem: MyBooksData, newItem: MyBooksData) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MyBooksData, newItem: MyBooksData) =
            oldItem == newItem

    }

    inner class HistoryViewHolder(val binding: ItemBooksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.item.setOnClickListener {
                if (System.currentTimeMillis() - time >= 500) {
                    time = System.currentTimeMillis()
                    itemClickListener?.invoke(getItem(adapterPosition))
                }
            }
        }

        fun bind() {
            binding.apply {
                val item = getItem(adapterPosition)
                bookName.text = item.bookName
                authorName.text = item.bookAuthor

                if (item.type=="pdf"){
                    bookIcon.setImageResource(R.drawable.headphones)
                }else{
                    bookIcon.setImageResource(R.drawable.book)
                }

                Glide.with(binding.root.context)
                    .load(item.bookPicture[0])
                    .into(binding.bookImage)
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

    fun onClick(block: (MyBooksData) -> Unit) {
        this.itemClickListener = block
    }

}

