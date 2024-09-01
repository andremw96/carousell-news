package com.andremw96.carousell_news.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andremw96.carousell_news.R
import com.andremw96.carousell_news.ui.screen.model.CarousellNewsState
import com.andremw96.carousell_news.ui.widget.CarouselNewsAppBar


@Composable
fun CarousellNewsListScreen(
    viewState: CarousellNewsListViewState,
    callbacks: CarousellNewsListCallbacks,
) {
    Scaffold(
        topBar = {
            CarouselNewsAppBar(
                title = stringResource(id = R.string.app_name),
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                when {
                    viewState.isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    viewState.errorMessage != null -> {

                    }

                    viewState.carousellNews.isEmpty() -> {

                    }

                    else -> {
                        LazyColumn {
                            items(viewState.carousellNews) {
                                CarousellNewsItem(
                                    carousellNews = it,
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CarousellNewsItem(
    carousellNews: CarousellNewsState,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(carousellNews.bannerUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = carousellNews.title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            maxLines = 2,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
            )
        )

        Text(
            text = carousellNews.description,
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 12.sp,
            ),
            maxLines = 2,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
            )
        )

        Text(
            text = carousellNews.timeCreated,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 10.sp,
            ),
            modifier = Modifier.padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        )
    }
}
