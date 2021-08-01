package com.romainbechard.saturdaytest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.romainbechard.saturdaytest.R
import com.romainbechard.saturdaytest.SaturdayTestApplication
import com.romainbechard.saturdaytest.databinding.MainFragmentBinding
import com.romainbechard.saturdaytest.tools.EventObserver
import timber.log.Timber
import java.util.*

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: PhotoListAdapter
    private lateinit var application: SaturdayTestApplication
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false).apply {
            viewModel = mainViewModel
        }
        setUpAdapter()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        application = requireContext().applicationContext as SaturdayTestApplication
        binding.lifecycleOwner = this.viewLifecycleOwner
        application.photosHolder.selectedPhotos = mutableListOf()
        mainViewModel.configure(application.repository, application.photosHolder)

        mainViewModel.badSelectionEvent.observe(
            viewLifecycleOwner,
            EventObserver {
                displayErrorToast()
            }
        )

        mainViewModel.goToNextPageEvent.observe(
            viewLifecycleOwner,
            EventObserver {
                findNavController().navigate(R.id.action_go_to_resultFragment)
            }
        )
    }

    private fun setUpAdapter() {
        val viewModel = binding.viewModel
        if (viewModel != null) {
            adapter = PhotoListAdapter(viewModel)
            binding.recyclerView.adapter = adapter
        } else {
            Timber.d("Error during adapter setup")
        }
    }

    private fun displayErrorToast() {
        Toast.makeText(application, "Please select two or more items", Toast.LENGTH_SHORT).show()
    }

}