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
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.model.Pet
import com.example.androiddevchallenge.ui.model.ageToString
import com.example.androiddevchallenge.ui.theme.MyTheme

class PetDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pet = intent.getParcelableExtra<Pet>(EXTRA_PET)
        if (pet == null) {
            finish()
            return
        }
        title = pet.name
        setContent {
            MyTheme {
                MyPet(pet)
            }
        }
    }

    @Composable
    private fun MyPet(pet: Pet) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = pet.pictureId),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentDescription = pet.name,
                contentScale = ContentScale.Crop
            )
            Text(
                text = pet.description,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "${ageToString(pet.ageMonths)} old",
                modifier = Modifier.padding(0.dp, 8.dp)
            )
        }
    }

    companion object {
        private const val EXTRA_PET = "PetDetail.EXTRA_PET"
        fun start(pet: Pet, context: Context) {
            context.startActivity(
                Intent(context, PetDetailActivity::class.java).apply {
                    putExtra(EXTRA_PET, pet)
                }
            )
        }
    }
}
