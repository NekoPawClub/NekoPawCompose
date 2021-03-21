package com.nekopawclub.nekopaw.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomePage(viewModel: HomeViewModel = HomeViewModel.ins) {
    println("重绘 HomePage")
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.Search, null)
            }
        },
        floatingActionButtonPosition = viewModel.fabPosition
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(text = "暗色模式")
                Switch(
                    checked = viewModel.darkTheme,
                    onCheckedChange = {
                        viewModel.changeDarkTheme(it)
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = "搜索键位置")
                Switch(
                    checked = viewModel.fabPosition == FabPosition.End,
                    onCheckedChange = {
                        viewModel.changeFabPosition(if (it) FabPosition.End else FabPosition.Center)
                    }
                )
            }
        }
    }
}