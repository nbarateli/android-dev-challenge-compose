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
package com.example.androiddevchallenge.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    val name: String,
    val description: String,
    val species: PetSpecies,
    @DrawableRes val pictureId: Int,
    val ageMonths: Int
) : Parcelable {
    override fun toString(): String {
        return "${species.emoji} $name, ${ageToString(ageMonths)}"
    }
}

fun ageToString(months: Int): String {
    val monthRemainder = months % 12
    val years = months / 12

    val monthString = "$monthRemainder month${if (monthRemainder > 1) "s" else ""}"
    val yearString = "$years year${if (years > 1) "s" else ""}"
    return when {
        years < 1 -> monthString
        monthRemainder < 1 -> yearString
        else -> "$yearString and $monthString"
    }
}

enum class PetSpecies(val emoji: String) {
    DOG("🐶"),
    CAT("🐱")
}
