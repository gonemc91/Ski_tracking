package com.skitracking.ui.screens.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skitracking.ui.theme.Dimens


@Composable


fun Container(
    name: String = "",
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    OutlinedCard(
        border = BorderStroke(width = 1.dp, Color.Gray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(size = 4.dp),
        modifier = modifier
            .height(Dimens.containerHeight)
    ) {
        Text(text = name, style =  MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = Dimens.mediumDimen))
        content()
    }
}



