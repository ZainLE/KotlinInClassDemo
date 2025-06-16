package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Scaffold { inner ->
                    NavHost(
                        navController = navController,
                        startDestination = "first",
                        modifier = Modifier.padding(inner)
                    ) {
                        composable("first") { StatsScreen(onNext = { navController.navigate("second") }) }
                        composable("second") { SecondScreen(onBack = { navController.popBackStack() }) }
                    }
                }
            }
        }
    }
}

@Composable
fun StatsScreen(onNext: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.barcelonaimage),
                contentDescription = "Cathedral",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
                    .background(
                        Color(0x66000000),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pin),
                    contentDescription = "Location pin",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Barcelona, Spain",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.circleimage),
                contentDescription = "User Image",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Fit
            )

            Text(
                "Biel Morro",
                color = Color.White,
//                modifier = Modifier.padding(start = 8.dp)
            )

            Row(modifier = Modifier.padding(horizontal = 30.dp)) {
                Image(
                    painter = painterResource(R.drawable.ic_download),
                    contentDescription = "Download",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(26.dp)
                )
                Image(
                    painter = painterResource(R.drawable.ic_heart),
                    contentDescription = "Heart",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(26.dp)

                )
                Image(
                    painter = painterResource(R.drawable.ic_bookmark),
                    contentDescription = "Bookmark",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(26.dp)
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .padding(horizontal = 32.dp)
                .padding(top = 20.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )
        //        Spacer(Modifier.height(16.dp))

        Spacer(Modifier.height(8.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            MetricRow(
                leftTitle = stringResource(R.string.camera),
                leftValue = stringResource(R.string.camera_model),
                rightTitle = stringResource(R.string.Aperture),
                rightValue = stringResource(R.string.Aperture_number),
            )

            Spacer(Modifier.height(8.dp))

            MetricRow(
                leftTitle = stringResource(R.string.Focal_Length),
                leftValue = stringResource(R.string.Focal_Length_number),
                rightTitle = stringResource(R.string.Shutter_Speed),
                rightValue = stringResource(R.string.Shutter_Speed_number),
            )

            Spacer(Modifier.height(8.dp))

            MetricRow(
                leftTitle = stringResource(R.string.ISO),
                leftValue = stringResource(R.string.ISO_number),
                rightTitle = stringResource(R.string.Dimensions),
                rightValue = stringResource(R.string.Dimensions_number),
            )
            HorizontalDivider(
                modifier = Modifier.padding(32.dp),
                thickness = 1.dp,
                color = Color.DarkGray
            )
        }

//        Spacer(Modifier.height(24.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column() {
                Text(stringResource(R.string.Views), color = Color.White)
                Text(stringResource(R.string.Views_count), color = Color.White)
            }
//
            Column() {
                Text(stringResource(R.string.Downloads), color = Color.White)
                Text(stringResource(R.string.Download_count), color = Color.White)
            }

            Column() {
                Text(stringResource(R.string.Likes), color = Color.White)
                Text(stringResource(R.string.Like_count), color = Color.White)
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(32.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )

        Spacer(Modifier.weight(1f))
        Row {
            // Next button
            Button(
                onClick = onNext,
                modifier = Modifier
//                .fillMaxWidth()
                    .padding(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text("Barcelona")


            }
            Button(
                onClick = onNext,
                modifier = Modifier
//                .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    text = "Spain"
                )

            }
        }
    }
}

@Composable
fun MetricRow(
    leftTitle: String,
    leftValue: String,
    rightTitle: String,
    rightValue: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = leftTitle, color = Color.LightGray, fontSize = 12.sp)
            Text(text = leftValue, color = Color.White, fontSize = 14.sp)
        }
        Column {
            Text(
                text = rightTitle,
                color = Color.LightGray,
                fontSize = 12.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Right
            )
            Text(
                text = rightValue,
                color = Color.White,
                fontSize = 14.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Right
            )
        }
    }
}


@Composable
fun SecondScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Second Screen!", color = Color.White)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Go Back")
        }
    }
}