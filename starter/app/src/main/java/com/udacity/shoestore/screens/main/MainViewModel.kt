package com.udacity.shoestore.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>> = _shoes

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe> = _shoe

    fun addShoeToList(item: Shoe) {
        // add new shoe to list
        var shoeItems = shoes.value ?: emptyList()
        shoeItems = shoeItems + item
        _shoes.value = shoeItems
    }

    fun setShoe(item: Shoe) {
        _shoe.value = item
    }
}