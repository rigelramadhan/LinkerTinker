package one.reevdev.linkertinker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import one.reevdev.linkertinker.ui.LinkerTinkerApp
import one.reevdev.linkertinker.ui.theme.LinkerTinkerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LinkerTinkerTheme {
                LinkerTinkerApp()
            }
        }
    }
}