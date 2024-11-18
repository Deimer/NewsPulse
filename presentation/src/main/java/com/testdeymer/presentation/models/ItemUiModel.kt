package com.testdeymer.presentation.models

data class ItemUiModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    var onClick: () -> Unit = {},
)