package ru.ke.mainpagearchexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.ke.mainpagearchexample.compose.MainPageScreen

class MainActivity : ComponentActivity() {

    private val viewModel: MainPageViewModel = MainPageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by viewModel.state.collectAsState()
            MainPageScreen(state = state)
        }
    }

}
