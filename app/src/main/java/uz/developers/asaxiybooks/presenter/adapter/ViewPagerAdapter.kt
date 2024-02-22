package uz.developers.asaxiybooks.presenter.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.developers.asaxiybooks.presenter.screen.MyBooksScreen

class ViewPagerAdapter(
    fm:Fragment,
    val myBooksScreen: MyBooksScreen,
//    val
):FragmentStateAdapter(fm) {
    override fun getItemCount(): Int =4

    override fun createFragment(position: Int): Fragment {
        return MyBooksScreen()
    }
}