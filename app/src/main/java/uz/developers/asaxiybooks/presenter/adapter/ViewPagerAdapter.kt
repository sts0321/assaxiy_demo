package uz.developers.asaxiybooks.presenter.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.developers.asaxiybooks.presenter.screen.AudioBookScreen
import uz.developers.asaxiybooks.presenter.screen.LibraryScreen
import uz.developers.asaxiybooks.presenter.screen.MyBooksScreen
import uz.developers.asaxiybooks.presenter.screen.ProfileScreen

class ViewPagerAdapter(
    fm:Fragment,
    val myBooksScreen: MyBooksScreen,
    val libraryScreen: LibraryScreen,
    val audioBookScreen: AudioBookScreen,
    val profileScreen: ProfileScreen
):FragmentStateAdapter(fm) {
    override fun getItemCount(): Int =4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->myBooksScreen
            1->libraryScreen
            2->audioBookScreen
            else->profileScreen
        }
    }
}