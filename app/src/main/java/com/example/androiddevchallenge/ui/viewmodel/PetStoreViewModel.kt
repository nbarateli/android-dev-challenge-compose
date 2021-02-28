/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
