package com.testdeymer.presentation.models

data class ItemUiModel(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val description: String = "",
    val fullDate: String = "",
    val commentText: String = "",
    val url: String = "",
    var onClick: () -> Unit = {},
) {
    val hasUrl: Boolean get() = url.isNotEmpty()
}