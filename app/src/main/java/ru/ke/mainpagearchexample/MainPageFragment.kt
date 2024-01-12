package ru.ke.mainpagearchexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import ru.ke.mainpagearchexample.compose.MainPageScreen

class MainPageFragment : Fragment() {

    private val viewModel: MainPageViewModel = MainPageViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()
                MainPageScreen(state = state)
            }
        }
    }

}