package com.skitracking.ui.screens.ui_block

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skitracking.R
import com.skitracking.ui.screens.base.Container


@Composable
@Preview(showBackground = true)

fun LatBlock(lat: String) {
    Container(
        name = stringResource(id = R.string.name_container_lat)
            .toUpperCase(Locale.current),
        modifier = Modifier
            .layoutId("lat")
            .height(50.dp)

    ) {
        Text(text = lat,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
    }

}