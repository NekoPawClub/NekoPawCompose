package com.nekopawclub.nekopaw.ui.home

import androidx.compose.material.FabPosition
import androidx.compose.runtime.Stable

@Stable
data class HomeConfig(
    var darkTheme: Boolean,
    var fabPosition: FabPosition,
)

