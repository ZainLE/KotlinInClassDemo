package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "FirstScreen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("FirstScreen") {
                            FirstScreen(navController)
                        }
                        composable("SecondScreen") {
                            SecondScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Hello, ${stringResource(R.string.firstscreen_text)}",
            color = Color.White,
            textAlign = TextAlign.Center)

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment   = Alignment.CenterVertically
        ) {
            Image(
                painter           = painterResource(R.drawable.ic_android),
                contentDescription = "Android Logo",
                modifier          = Modifier.size(32.dp),
                contentScale      = ContentScale.Fit
            )
            Image(
                painter           = painterResource(R.drawable.ic_kotlin),
                contentDescription = "Kotlin Logo",
                modifier          = Modifier.size(64.dp),
                contentScale      = ContentScale.Fit
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick  = { navController.navigate("SecondScreen") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Go to Second Screen")
        }
    }
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement   = Arrangement.Center
    ) {
        Text("Second Screen!")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Preview
@Composable
fun PreviewFirst() {
    MyApplicationTheme {
        FirstScreen(rememberNavController())
    }
}