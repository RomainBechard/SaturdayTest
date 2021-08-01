package com.romainbechard.saturdaytest.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.romainbechard.saturdaytest.SaturdayTestApplication
import com.romainbechard.saturdaytest.databinding.ResultFragmentBinding
import com.squareup.picasso.Picasso

class ResultFragment : Fragment() {

    private lateinit var binding: ResultFragmentBinding
    private lateinit var application: SaturdayTestApplication
    private val listViewModel by viewModels<ResultViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ResultFragmentBinding.inflate(layoutInflater, container, false).apply {
            viewModel = listViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        application = requireContext().applicationContext as SaturdayTestApplication
        listViewModel.configure(application.repository, application.photosHolder)
        binding.lifecycleOwner = viewLifecycleOwner
        displayImage(binding.imageView)
        displayImage(binding.imageView2)
        displayImage(binding.imageView3)
    }

    private fun displayImage(imageView: ImageView){
        Picasso.get()
            .load(application.photosHolder.selectedPhotos.random().imageUrl)
            .into(imageView)
    }

}