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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.model.Pet
import com.example.androiddevchallenge.ui.model.ageToString
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.viewmodel.PetStoreViewModel
import com.example.androiddevchallenge.ui.viewmodel.PetStoreViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel: PetStoreViewModel by viewModels { PetStoreViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                MyApp(viewModel)
            }
        }

        viewModel.petClickEvents.observeForever {
            PetDetailActivity.start(it, this)
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(viewModel: PetStoreViewModel) {
    Column {

        Text(text = "Ready... Set... GO!")
        PetList(viewModel)
    }
}

@Composable
fun PetList(viewModel: PetStoreViewModel) {
    val pets by viewModel.pets.observeAsState(emptyList())

    LazyColumn(Modifier.fillMaxWidth()) {
        items(pets) { pet ->
            PetItem(pet = pet) {
                viewModel.onPetClick(it)
            }
        }
    }
}

@Composable
fun PetItem(pet: Pet, onClick: (Pet) -> Unit) {

    Card(

        modifier = Modifier
            .fillMaxWidth()

            .clip(RoundedCornerShape(6.dp))
            .padding(8.dp),
        shape = MaterialTheme.shapes.large,
    ) {

        Row(Modifier.clickable { onClick(pet) }) {

            Image(
                painter = painterResource(id = pet.pictureId),
                pet.name,
                modifier = Modifier
                    .size(128.dp)
                    .requiredWidth(128.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(12.dp)),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )

            Text(
                text = "${pet.species.emoji} ${pet.name}, ${ageToString(pet.ageMonths)}",
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(PetStoreViewModel())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(PetStoreViewModel())
    }
}
