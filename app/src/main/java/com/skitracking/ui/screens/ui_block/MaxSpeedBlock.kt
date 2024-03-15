package com.skitracking.ui.screens.ui_block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.skitracking.R
import com.skitracking.ui.screens.base.Container
import com.skitracking.ui.theme.Dimens


@Composable
@Preview(showBackground = true)

fun MaxSpeedBlock() {
    Container(
        name = stringResource(id = R.string.name_container_max_speed)
            .toUpperCase(Locale.current),
        modifier = Modifier.layoutId("maxSpeed")
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.mediumDimen, end = Dimens.mediumDimen)
        ) {

            val (maxSpeedValue, mph, kmh, avg, valueAvg) = createRefs()


            Text(text = stringResource(id = R.string.value_max_speed)
                .toUpperCase(Locale.current),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 45.sp),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
                    .constrainAs(maxSpeedValue) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(mph.start, margin = 10.dp)
                        width = Dimension.fillToConstraints
                    }
            )

            Text(text = stringResource(id = R.string.mph).toUpperCase(Locale.current),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(mph){
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
            )

            Text(text = stringResource(id = R.string.kmh),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(kmh){
                    top.linkTo(mph.bottom)

                    end.linkTo(parent.end)
                }
            )

            Text(text = stringResource(id = R.string.name_avg),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(avg){
                    start.linkTo(parent.start)
                    top.linkTo(maxSpeedValue.bottom)
                    bottom.linkTo(parent.bottom)
                }
            )

            Text(text = stringResource(id = R.string.value_avg),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(valueAvg){
                    end.linkTo(maxSpeedValue.end)
                    top.linkTo(maxSpeedValue.bottom)
                    bottom.linkTo(parent.bottom)
                }
            )

        }
    }

}