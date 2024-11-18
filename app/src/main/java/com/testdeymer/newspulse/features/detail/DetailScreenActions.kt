package com.testdeymer.newspulse.features.detail

data class DetailScreenActions(
    val onPrimaryAction: () -> Unit,
    val onSecondaryAction: (url: String) -> Unit,
)