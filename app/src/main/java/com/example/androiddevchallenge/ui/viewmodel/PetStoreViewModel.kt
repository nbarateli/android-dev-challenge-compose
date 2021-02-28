package com.example.androiddevchallenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.model.Pet
import com.example.androiddevchallenge.ui.model.PetSpecies

class PetStoreViewModel : ViewModel() {
    fun onPetClick(pet: Pet) {
        _petClickEvents.value = (pet)
    }

    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> by ::_pets

    private val _petClickEvents = MutableLiveData<Pet>()
    val petClickEvents: LiveData<Pet> by ::_petClickEvents

    init {
        _pets.postValue(
            listOf(
                Pet("Bambi", "Queen Bambi", PetSpecies.CAT, R.drawable.pet_image_bambi_1, 17),
                Pet(
                    "Bambi",
                    "Bambi under the chair",
                    PetSpecies.CAT,
                    R.drawable.pet_image_bambi_2,
                    17
                ),
                Pet("Charlie", "Schleepy boi", PetSpecies.DOG, R.drawable.pet_image_charlie_1, 4),
                Pet("Charlie", "Sploot", PetSpecies.DOG, R.drawable.pet_image_charlie_2, 4),
                Pet("Charlie", "He sit", PetSpecies.DOG, R.drawable.pet_image_charlie_3, 4),
                Pet("Chonky", "Chonk", PetSpecies.DOG, R.drawable.pet_image_chonk, 24),
                Pet("Foxy", "Crazy foxy", PetSpecies.CAT, R.drawable.pet_image_foxy_1, 18),
                Pet("Foxy", "She stan", PetSpecies.CAT, R.drawable.pet_image_foxy_2, 18),
            )
        )
    }
}