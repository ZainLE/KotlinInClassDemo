package com.example.myapplication.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.api.UnsplashProvider
import com.example.myapplication.data.UnsplashItem
import coil3.compose.AsyncImage

@Composable
fun DetailsScreen(
    photoId: String,
    onAction: (() -> Unit)? = null
) {
    var unsplashItem by remember { mutableStateOf<UnsplashItem?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(photoId) {
        UnsplashProvider().fetchPhotoById(photoId, {
            unsplashItem = it
            loading = false
        }, {
            error = it
            loading = false
        })
    }

    when {
        loading -> {
            Text(text = "Loading...")
        }
        error != null -> {
            Text(text = error ?: "Unknown error")
        }
        unsplashItem != null -> {
            val item = unsplashItem!!
            Column {
                val imageUrl = item.urls?.regular
                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = item.description ?: "Photo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .let { if (expanded) it else it.height(250.dp) }
                            .clickable { expanded = !expanded },
                        contentScale = if (expanded) ContentScale.Fit else ContentScale.Crop
                    )
                }
                Row {
                    AddPhotoInfoRow(
                        title1 = R.string.camera,
                        subtitle1 = item.exif?.model ?: "-",
                        title2 = R.string.Aperture,
                        subtitle2 = item.exif?.aperture ?: "-"
                    )
                }
                Row {
                    AddPhotoInfoRow(
                        title1 = R.string.Focal_Length,
                        subtitle1 = item.exif?.focal_length ?: "-",
                        title2 = R.string.Shutter_Speed,
                        subtitle2 = item.exif?.exposure_time ?: "-"
                    )
                }
                Row {
                    AddPhotoInfoRow(
                        title1 = R.string.ISO,
                        subtitle1 = item.exif?.iso?.toString() ?: "-",
                        title2 = R.string.Dimensions,
                        subtitle2 = if (item.width != null && item.height != null) "${item.width} x ${item.height}" else "-"
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stringResource(R.string.Downloads))
                        Text(item.downloads?.toString() ?: "-")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stringResource(R.string.Likes))
                        Text(item.likes?.toString() ?: "-")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stringResource(R.string.camera))
                        Text(item.exif?.make ?: "-")
                    }
                }
                Text(text = item.description ?: "-", modifier = Modifier.padding(8.dp))
                Text(text = item.location?.city ?: item.location?.country ?: "-", modifier = Modifier.padding(8.dp))
                Text(text = item.tags?.joinToString { it.title ?: "-" } ?: "-", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun AddPhotoInfoRow(
    @StringRes title1: Int,
    subtitle1: String,
    @StringRes title2: Int,
    subtitle2: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = stringResource(id = title1),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle1
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = stringResource(id = title2),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle2
            )
        }
    }
}

@Composable
@Preview
fun PreviewDetailsScreen() {
    DetailsScreen("barcelonaimage")
}
