package com.testdeymer.newspulse.mapper

import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.presentation.utils.capital
import com.testdeymer.repository.domain.HitDomain

fun List<HitDomain>.toUiModel(): List<ItemUiModel> {
    return this.mapIndexed { index, domain ->
        ItemUiModel(
            id = domain.objectId,
            title = "${index.plus(1)}. ${domain.title}",
            description = "${domain.author.capital()} · ${domain.createdAtMini}",
            onClick = {}
        )
    }
}