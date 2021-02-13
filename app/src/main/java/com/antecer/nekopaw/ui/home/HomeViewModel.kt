package com.nekopawclub.nekopawcompose.home

import androidx.compose.material.FabPosition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var homeConfig by mutableStateOf(HomeConfig(
            darkTheme = false,
            fabPosition = FabPosition.Center
        ))

    fun changeDarkTheme(darkTheme: Boolean){
        homeConfig = HomeConfig(darkTheme, homeConfig.fabPosition)
    }
    fun changeFabPosition(fabPosition: FabPosition){
        homeConfig = HomeConfig(homeConfig.darkTheme, fabPosition)
    }
}