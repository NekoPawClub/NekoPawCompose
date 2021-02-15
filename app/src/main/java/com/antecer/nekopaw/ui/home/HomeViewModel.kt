package com.antecer.nekopaw.ui.home

import androidx.compose.material.FabPosition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private val homeConfig = HomeConfig(
            darkTheme = false,
            fabPosition = FabPosition.Center
    )

    var fabPosition by mutableStateOf(homeConfig.fabPosition)
    var darkTheme by mutableStateOf(homeConfig.darkTheme)

    private fun onChangeAndSave(){
        // save("homeConfig", homeConfig.toJson())
    }

    fun changeDarkTheme(darkTheme: Boolean){
        if(this.darkTheme != darkTheme){
            this.darkTheme = darkTheme
            homeConfig.darkTheme = darkTheme
            onChangeAndSave()
        }
    }

    fun changeFabPosition(fabPosition: FabPosition){
        if(this.fabPosition != fabPosition){
            this.fabPosition = fabPosition
            homeConfig.fabPosition = fabPosition
            onChangeAndSave()
        }
    }
}