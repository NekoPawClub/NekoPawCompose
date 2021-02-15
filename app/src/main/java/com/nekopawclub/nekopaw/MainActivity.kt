package com.nekopawclub.nekopaw

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nekopawclub.nekopaw.ui.BookCase
import com.nekopawclub.nekopaw.ui.theme.NekoPawTheme
import com.nekopawclub.nekopaw.ui.home.HomePage
import com.nekopawclub.nekopaw.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("重绘 MainActivity onCreate")

        setContent(null) {
            NekoPawTheme(darkTheme = HomeViewModel.ins.darkTheme) {
                println("重绘 NekoPawTheme")

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

@Preview(showBackground = true)
@Composable
fun Preview1(){
    NekoPawTheme {
        HomePage()
    }
}