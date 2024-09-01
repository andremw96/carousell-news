package com.andremw96.carousell_news.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andremw96.carousell_news.R

@Composable
fun CarouselNewsAppBar(
    modifier: Modifier = Modifier,
    title: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 18.dp,
                    start = 48.dp,
                    bottom = 18.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 16.sp
                ),
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more_horizontal),
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
    }
}
