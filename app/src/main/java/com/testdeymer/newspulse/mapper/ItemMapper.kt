package com.testdeymer.newspulse.mapper

import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.presentation.utils.capital
import com.testdeymer.repository.domain.HitDomain

fun List<HitDomain>.toUiModel(): List<ItemUiModel> {
    return this.mapIndexed { index, domain ->
        ItemUiModel(
            id = domain.objectId,
            title = "${index.plus(1)}. ${domain.title.ifEmpty { "Without title" }}",
            description = "${domain.author.capital()} · ${domain.createdAtMini}",
            onClick = {}
        )
    }
}

fun HitDomain.toUiModel(): ItemUiModel {
    return ItemUiModel(
        id = this.objectId,
        title = this.title.ifEmpty { "Without title" },
        author = this.author,
        fullDate = this.updatedAtFull,
        commentText = this.commentText,
        url = this.url,
    )
}