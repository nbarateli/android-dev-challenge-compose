package com.example.androiddevchallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PetStoreViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetStoreViewModel::class.java))
            return PetStoreViewModel() as T
        throw IllegalArgumentException()
    }


}