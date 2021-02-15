package com.antecer.nekopaw

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.antecer.nekopaw.ui.BookCase
import com.antecer.nekopaw.ui.theme.NekoPawTheme
import com.antecer.nekopaw.ui.home.HomePage
import com.antecer.nekopaw.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("重绘 MainActivity onCreate")

        setContent(null) {
            NekoPawTheme(darkTheme = viewModel.darkTheme) {
                println("重绘 NekoPawTheme")

                Surface(color = MaterialTheme.colors.background) {
                    HomePage(viewModel)
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