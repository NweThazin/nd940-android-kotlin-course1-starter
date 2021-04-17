package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ListItemShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.main.MainViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_list,
                container,
                false
        )
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        viewModel.shoes.observe(viewLifecycleOwner, { newShoes ->
            newShoes?.let {
                addItemToList(it)
            }
        })

        binding.floatingActionBar.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        return binding.root
    }

    private fun addItemToList(items: List<Shoe>) {
        binding.layoutShoes.removeAllViews()
        items.map {
            val itemBinding = ListItemShoeBinding.inflate(LayoutInflater.from(requireContext()), null, false)
            itemBinding.shoe = it
            binding.layoutShoes.addView(itemBinding.root)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logOut -> {
                performLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun performLogout() {
        findNavController().navigate(R.id.action_shoeListFragment_to_login_fragment)
    }
}