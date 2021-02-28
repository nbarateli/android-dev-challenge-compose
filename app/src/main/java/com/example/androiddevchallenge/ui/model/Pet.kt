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

    val monthString = "$monthRemainder month${if (monthRemainder > 1) "s" else ""}";
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