package com.romainbechard.saturdaytest.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.romainbechard.saturdaytest.SaturdayTestApplication
import com.romainbechard.saturdaytest.databinding.ResultFragmentBinding

class ResultFragment : Fragment() {

    private lateinit var binding: ResultFragmentBinding
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application: SaturdayTestApplication = requireContext().applicationContext as SaturdayTestApplication
        listViewModel.configure(application.repository, application.photosHolder)
        binding.lifecycleOwner = viewLifecycleOwner
    }

}