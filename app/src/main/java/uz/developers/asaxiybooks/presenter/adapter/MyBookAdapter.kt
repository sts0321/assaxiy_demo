package uz.developers.asaxiybooks.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.databinding.ItemMybookBinding


class MyBookAdapter:ListAdapter<MyBooksData,MyBookAdapter.MyViewHolder>(MyDif) {

    var onClickItem:((MyBooksData)->Unit)?=null


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
                binding.bookAuthor.text = bookAuthor
                Glide.with(binding.root.context).load(bookPicture[0]).into(binding.imgBook)
//                binding.bookPicture.setImageResource(bookPicture)
                if(type =="pdf"){
                    binding.apply {
                        typeImg.setBackgroundResource(R.drawable.books2)
                        typeText.text="(Kitob)"
                    }
//                    binding..visibility  = View.GONE
//                    binding.audioBookText.visibility = View.GONE
                }else{
                    binding.apply {
                        typeImg.setBackgroundResource(R.drawable.headphones)
                        typeText.text="(Audio kitob)"
                    }
                }
                binding.root.setOnClickListener {
                    onClickItem?.invoke(this)
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