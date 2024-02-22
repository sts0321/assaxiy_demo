package uz.developers.asaxiybooks.presenter.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nasiyaapp.utils.myLog
import uz.developers.asaxiybooks.presenter.screen.IntroPages

class IntroAdapter(activity: Fragment) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) = IntroPages().apply {
        "createFragment".myLog()
        arguments = bundleOf(Pair("POS", position), Pair("DATA", "position"))
    }
}