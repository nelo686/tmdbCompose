package es.mrmoustard.tmdbco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import es.mrmoustard.tmdbco.ui.TmdbcoApp
import es.mrmoustard.tmdbco.ui.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TmdbcoApp {
                Navigation(rememberNavController())
            }
        }
    }
}
