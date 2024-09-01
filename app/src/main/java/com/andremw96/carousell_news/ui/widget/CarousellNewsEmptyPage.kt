package com.andremw96.carousell_news.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CarousellNewsEmptyPage(
    modifier: Modifier = Modifier,
    retryOnClick: (() -> Unit)?,
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Something Went Wrong",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = "Data Not Found",
            textAlign = TextAlign.Center,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.padding(12.dp))

        if (retryOnClick != null) {
            Button(onClick = {
                retryOnClick()
            }) {
                Text(text = "Retry")
            }
        }
    }
}
