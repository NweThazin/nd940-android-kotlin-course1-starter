package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.main.MainViewModel
import timber.log.Timber
import java.lang.NumberFormatException

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_detail,
                container,
                false
        )
        binding.btnCancel.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
        binding.btnSave.setOnClickListener {
            saveShoeEntry()
        }

        return binding.root
    }

    private fun saveShoeEntry() {
        try {
            val shoe = Shoe(
                    name = binding.shoeName ?: "",
                    size = binding.shoeSize?.toDouble() ?: 0.0,
                    company = binding.shoeCompany ?: "",
                    description = binding.shoeDescription ?: "",
                    images = emptyList()
            )
            // add shoe to shoe list in view model
            viewModel.addShoeToList(shoe)
            Timber.i(shoe.toString())
            // to navigate back
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            Timber.e("Error in converting from string to double")
        }
    }
}