package com.andremw96.carousell_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.andremw96.carousell_news.ui.screen.CarousellNewsListScreen
import com.andremw96.carousell_news.ui.screen.CarousellNewsListViewModel
import com.andremw96.carousell_news.ui.theme.Carousell_newsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Carousell_newsTheme {
                val viewModel: CarousellNewsListViewModel by viewModels()
                val viewState by viewModel.viewState.collectAsState()

                LaunchedEffect(key1 = Unit) {
                    viewModel.loadCarousellNews()
                }

                CarousellNewsListScreen(
                    viewState = viewState,
                    callbacks = viewModel,
                )
            }
        }
    }
}