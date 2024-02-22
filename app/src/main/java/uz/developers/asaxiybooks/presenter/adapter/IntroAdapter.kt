package uz.developers.asaxiybooks.presenter.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class IntroAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) = Task2Page().apply {
        arguments = bundleOf(Pair("POS", position), Pair("DATA", "position"))
    }
}