package com.andremw96.carousell_news.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    onRecentClick: (() -> Unit)? = null,
    onPopularClick: (() -> Unit)? = null,
) {
    var showMenu by remember { mutableStateOf(false) }

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

            if (onRecentClick != null && onPopularClick != null) {
                Box {
                    IconButton(
                        onClick = { showMenu = !showMenu }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more_horizontal),
                            contentDescription = "",
                            tint = Color.White,
                        )
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(text = {
                            Text(text = "Recent")
                        }, onClick = {
                            onRecentClick()
                            showMenu = false
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Popular")
                        }, onClick = {
                            onPopularClick()
                            showMenu = false
                        })
                    }
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
    }
}
