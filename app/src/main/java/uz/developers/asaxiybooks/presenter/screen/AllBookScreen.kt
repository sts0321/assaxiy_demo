package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenAllbooksBinding
import uz.developers.asaxiybooks.presenter.adapter.AllBooksAdapter

class AllBookScreen:Fragment(R.layout.screen_allbooks) {
    private val binding by viewBinding(ScreenAllbooksBinding::bind)
    private val navArgs by navArgs<AllBookScreenArgs>()
    private val adapter by lazy { AllBooksAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        binding.categoryName.text=navArgs.category.name
        adapter.submitList(navArgs.category.myBooks)
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun initAdapter(){
        binding.rec.adapter=adapter
        binding.rec.layoutManager=LinearLayoutManager(requireContext())
    }
}