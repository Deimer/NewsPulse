package com.testdeymer.newspulse.features.home

data class HomeScreenActions(
    val onPrimaryAction: (objectId: String) -> Unit,
)
