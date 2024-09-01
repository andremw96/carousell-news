package com.andremw96.carousell_news.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andremw96.carousell_news.ui.screen.model.CarousellNewsState
import com.andremw96.carousell_news.ui.widget.CarouselNewsAppBar
import com.andremw96.carousell_news.ui.widget.CarousellNewsEmptyPage
import com.andremw96.carousell_news.ui.widget.CarousellNewsErrorPage


@Composable
fun CarousellNewsListScreen(
    viewState: CarousellNewsListViewState,
    callbacks: CarousellNewsListCallbacks,
) {
    Scaffold(
        topBar = {
            CarouselNewsAppBar(
                title = "Carousell News",
                modifier = Modifier.background(Color.Red),
                onRecentClick = {
                    callbacks.sortNewsBy(SortBy.Recent)
                },
                onPopularClick = {
                    callbacks.sortNewsBy(SortBy.Popular)
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                val textSortBy = if (viewState.sortedBy == SortBy.Recent) "Sorted By: Recent" else "Sorted By: Popular"

                Text(
                    text = textSortBy,
                    modifier = Modifier.padding(8.dp)
                )

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
                        CarousellNewsErrorPage(viewState.errorMessage) {
                            callbacks.loadCarousellNews()
                        }
                    }

                    viewState.carousellNews.isEmpty() -> {
                        CarousellNewsEmptyPage {
                            callbacks.loadCarousellNews()
                        }
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
                color = Color(0xFF262629),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            maxLines = 2,
            modifier = Modifier.padding(
                top = 12.dp,
                start = 16.dp,
                end = 16.dp,
            )
        )

        Text(
            text = carousellNews.description,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Color(0xFF262629),
                fontSize = 14.sp,
            ),
            maxLines = 2,
            modifier = Modifier.padding(
                top = 4.dp,
                start = 16.dp,
                end = 16.dp,
            )
        )

        Text(
            text = carousellNews.timeCreatedString,
            style = TextStyle(
                color = Color(0xFF8F939C),
                fontSize = 12.sp,
            ),
            modifier = Modifier.padding(
                top = 8.dp,
                start = 16.dp,
                bottom = 12.dp
            )
        )
    }
}
