package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Import your R class if your drawables are in res/drawable
// import com.yourpackage.R // Replace com.yourpackage with your actual package name

@Composable
fun VentureTopAppBar(onCloseClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Replace with your actual logo drawable
//        Image(
//            painter = painterResource(id = R.drawable.venture.logo), // Assuming you have venture_logo.png
//            contentDescription = "Venture Logo",
//            modifier = Modifier.size(28.dp)
//        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Venture",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black // Or your desired text color
        )
        Spacer(modifier = Modifier.weight(1f)) // Pushes the close button to the end
        IconButton(onClick = onCloseClick) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                tint = Color.Black // Or your desired icon color
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewVentureTopAppBar() {
    VentureTopAppBar {}
}