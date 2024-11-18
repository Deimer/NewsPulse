package com.testdeymer.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.testdeymer.presentation.R
import com.testdeymer.presentation.theme.Gray40

@Composable
fun DividerCompose(
    modifier: Modifier = Modifier,
    color: Color = Gray40,
    thickness: Dp = dimensionResource(id = R.dimen.dimen_1),
    topPadding: Dp = dimensionResource(id = R.dimen.dimen_8),
    bottomPadding: Dp = dimensionResource(id = R.dimen.dimen_8),
    isVisible: Boolean = true
) {
    if (isVisible) {
        HorizontalDivider(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = topPadding,
                    bottom = bottomPadding
                ),
            color = color,
            thickness = thickness
        )
    }
}