package com.antecer.nekopaw

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.antecer.nekopaw.ui.BookCase
import com.antecer.nekopaw.ui.theme.NekoPawTheme
import com.nekopawclub.nekopawcompose.home.HomePage
import com.nekopawclub.nekopawcompose.home.HomeViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: HomeViewModel by viewModels()
        setContent {
            NekoPawTheme(darkTheme = viewModel.homeConfig.darkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomePage()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NekoPawTheme {
        BookCase()
    }
}