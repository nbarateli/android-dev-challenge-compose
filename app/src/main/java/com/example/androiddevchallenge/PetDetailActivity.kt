package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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

                })
        }
    }
}