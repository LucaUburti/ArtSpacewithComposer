package com.example.artspacewithcompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspacewithcompose.ui.theme.ArtSpaceWithComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val pictureList = populatePictureList()
                    println("arraylist size: " + pictureList.size)
                    ArtSpaceScreen(pictureList)
                }
            }
        }
    }
}

fun populatePictureList(): ArrayList<PictureData> {
    val pictureList = ArrayList<PictureData>()
    pictureList.add(
        PictureData(
            R.drawable.apples,
            "Apples turning red",
            "Wheewee Pottyhall",
            (2000..2023).random()
        )
    )
    pictureList.add(PictureData(R.drawable.cat, "Curious cat", "Hicktu Sockface", (2000..2023).random()))
    pictureList.add(
        PictureData(
            R.drawable.dandelion,
            "Dandelion in high winds",
            "Gumtu Spottybrain",
            (2000..2023).random()
        )
    )
    pictureList.add(
        PictureData(
            R.drawable.dragonfly,
            "Precarious Dragonfly",
            "Sniffbuns Hippyhall",
            (2000..2023).random()
        )
    )
    pictureList.add(
        PictureData(
            R.drawable.monkeys,
            "Eyebrowns pulling Monkeys",
            "Fluffmoo AntFadden",
            (2000..2023).random()
        )
    )
    pictureList.add(
        PictureData(
            R.drawable.poppies,
            "Psychedelic Poppies",
            "Poorster PimpleSmittens",
            (2000..2023).random()
        )
    )
    pictureList.add(
        PictureData(
            R.drawable.streetcar,
            "Suicide by streetcar",
            "Bittypants Hippykins",
            (2000..2023).random()
        )
    )
    pictureList.add(
        PictureData(
            R.drawable.watch,
            "Can't wait to read this",
            "Poopaloo Oinkkins",
            (2000..2023).random()
        )
    )
    pictureList.add(
        PictureData(
            R.drawable.zebra,
            "Zebra minding its own business",
            "Captain Shenanigans",
            (2000..2023).random()
        )
    )
    return pictureList
}

data class PictureData(val imgRes: Int, val title: String, val artist: String, val year: Int)

@Composable
fun ArtSpaceScreen(pictureList: ArrayList<PictureData>) {
    var id: Int by remember { mutableStateOf(0) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()

    ) {
        Surface(
            modifier = Modifier
                .wrapContentSize()
                .weight(0.6F)
                .padding(all = 24.dp),
            shape = RoundedCornerShape(8.dp),
            //border = BorderStroke(2.dp, Color.LightGray),
            elevation = 10.dp,

            ) {
            Image(
                painter = painterResource(id = pictureList[id].imgRes),
                contentDescription = pictureList[id].title,
                modifier = Modifier
                    .fillMaxSize()

                    .padding(all = 8.dp)

            )
        }
        Spacer(modifier = Modifier.weight(0.05F))
        Surface(
            modifier = Modifier
                .wrapContentSize()
                .weight(0.2F),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.LightGray),
            elevation = 10.dp,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pictureList[id].title,
                    fontSize = 20.sp,
                    fontWeight = Bold,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        //.fillMaxWidth()
                        //.align(Alignment.CenterVertically)
                        .wrapContentSize()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)

                )
                Text(
                    text = pictureList[id].artist + " (" + pictureList[id].year + ")",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        //.fillMaxWidth()
                        //.align(Alignment.CenterVertically)
                        .wrapContentSize()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)

                )
            }

        }
        Row(
            modifier = Modifier
                .wrapContentSize()
                .weight(0.15F)
                .padding(all = 8.dp),
        ) {
            Button(modifier = Modifier
                .weight(0.3F)
                .padding(start = 32.dp),
                onClick = {
                    println("old id: $id")
                    id = (id - 1).mod(pictureList.size)
                    println("new id: $id")
                }) {
                Text(text = "Prev", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(horizontal = 32.dp))
            Button(modifier = Modifier
                .weight(0.3F)
                .padding(end = 32.dp),
                onClick = {
                    println("old id: $id")
                    id = (id + 1).mod(pictureList.size)
                    println("new id: $id")
                }) {
                Text(text = "Next", fontSize = 20.sp)
            }
        }

    }//column end

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceWithComposeTheme {
        val pictureList = populatePictureList()
        ArtSpaceScreen(pictureList)
    }
}