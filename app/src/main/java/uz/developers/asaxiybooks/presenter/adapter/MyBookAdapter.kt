package uz.developers.asaxiybooks.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.databinding.ItemMybookBinding


class MyBookAdapter:ListAdapter<MyBooksData,MyBookAdapter.MyViewHolder>(MyDif) {


    object MyDif: DiffUtil.ItemCallback<MyBooksData>() {
        override fun areItemsTheSame(oldItem: MyBooksData, newItem: MyBooksData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MyBooksData, newItem: MyBooksData): Boolean {
            return oldItem ==newItem
        }

    }
    inner class MyViewHolder(private val binding: ItemMybookBinding):ViewHolder(binding.root){

        fun bind(){
            getItem(adapterPosition).apply {
                binding.bookName.text = bookName
                binding.authorName.text = bookAuthor
                binding.bookPicture.setImageResource(bookPicture)
                if(type=="pdf"){
                    binding.audioBook.visibility  = View.GONE
                    binding.audioBookText.visibility = View.GONE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder(ItemMybookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind()
    }
}