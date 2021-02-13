package com.nekopawclub.nekopawcompose.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel

@Composable
fun HomePage() {
    val viewModel: HomeViewModel = viewModel()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.Search)
            }
        },
        floatingActionButtonPosition = viewModel.homeConfig.fabPosition,
        bodyContent = {
            Column(modifier = Modifier.padding(10.dp)) {
                Row {
                    Text(text = "暗色模式")
                    Switch(
                        checked = viewModel.homeConfig.darkTheme,
                        onCheckedChange = {
                            viewModel.changeDarkTheme(it)
                        })

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = "搜索键位置")
                    Switch(
                        checked = viewModel.homeConfig.fabPosition == FabPosition.End,
                        onCheckedChange = {
                            viewModel.changeFabPosition(if (it) FabPosition.End else FabPosition.Center)
                        })
                }
            }
        }
    )
}