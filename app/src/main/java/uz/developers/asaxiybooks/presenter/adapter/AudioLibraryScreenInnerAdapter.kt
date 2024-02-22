package uz.developers.asaxiybooks.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.databinding.ItemLibraryBookBinding

class AudioLibraryScreenInnerAdapter :
    ListAdapter<MyBooksData, AudioLibraryScreenInnerAdapter.InnerHolder>(InnerDiffUtil) {
    private var onClickProduct : ((MyBooksData) -> Unit)? = null
    private var time  = System.currentTimeMillis()
    inner class InnerHolder(private val binding: ItemLibraryBookBinding) :
        ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                if(System.currentTimeMillis() - time >= 500) {
                    time = System.currentTimeMillis()
                    onClickProduct?.invoke(getItem(adapterPosition))
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.apply {
                val item = getItem(adapterPosition)
                bookAuthor.text = item.bookAuthor
                bookName.text = item.bookName

                Glide.with(binding.root.context)
                    .load(item.bookPicture[0])
                    .into(binding.imgBook)
            }
        }
    }

    /**
    Glide
    .with(requireContext())
    .load("https://firebasestorage.googleapis.com/v0/b/chatappb7.appspot.com/o/luffy-laughing-one-5120x2880-12358.png?alt=media&token=75107024-806b-4d78-85a5-e6148a98ade5")
    .centerCrop()
    .placeholder(R.drawable.shape1)
    .error(R.drawable.shape2)
    .into(binding.imageLuffy)
     */

    object InnerDiffUtil : DiffUtil.ItemCallback<MyBooksData>() {
        override fun areItemsTheSame(oldItem: MyBooksData, newItem: MyBooksData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyBooksData, newItem: MyBooksData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        return InnerHolder(
            ItemLibraryBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.bind()
    }
    fun setOnClickProduct(onClickProduct : (MyBooksData) -> Unit) {
        this.onClickProduct = onClickProduct
    }
    private fun numberFormat(number : String?) : String{
        if(number == null) return ""
        val num = number.toString().toLongOrNull() ?: return ""
        return String.format("%,d", num).replace(",", " ")
    }
}