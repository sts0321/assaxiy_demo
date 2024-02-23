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

class AllBooksAdapter: ListAdapter<MyBooksData, AllBooksAdapter.MyViewHolder>(MyDif) {

    var onClickItem:((MyBooksData)->Unit)?=null
    object MyDif: DiffUtil.ItemCallback<MyBooksData>() {
        override fun areItemsTheSame(oldItem: MyBooksData, newItem: MyBooksData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MyBooksData, newItem: MyBooksData): Boolean {
            return oldItem ==newItem
        }

    }
    inner class MyViewHolder(private val binding: ItemBooksBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(){
            getItem(adapterPosition).apply {
                binding.bookName.text = bookName
                binding.authorName.text = bookAuthor
                Glide.with(binding.root.context).load(bookPicture[0]).into(binding.bookImage)
//                binding.bookPicture.setImageResource(bookPicture)
                if(type =="pdf"){
                    binding.apply {
                        bookIcon.setImageResource(R.drawable.books2)
                    }
//                    binding..visibility  = View.GONE
//                    binding.audioBookText.visibility = View.GONE
                }else{
                    binding.apply {
                        bookIcon.setImageResource(R.drawable.headphones)
                    }
                }
                binding.root.setOnClickListener {
                    onClickItem?.invoke(this)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemBooksBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }
}