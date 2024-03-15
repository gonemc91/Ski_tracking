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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.skitracking.R
import com.skitracking.ui.screens.base.Container
import com.skitracking.ui.theme.Dimens


@Composable
@Preview(showBackground = true)

fun DestinationBlock() {
    Container(
        name = stringResource(id = R.string.name_container_destination)
            .toUpperCase(Locale.current),
        modifier = Modifier
            .layoutId("destination")
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.mediumDimen, end = Dimens.mediumDimen)
        ) {

            val (valueDestination,
                mi,
                km,
                ascent,
                valueAscent,
                totalDestination,
                valueTotalDestination) = createRefs()


            Text(text = stringResource(id = R.string.value_max_speed)
                .toUpperCase(Locale.current),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
                    .constrainAs(valueDestination) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(mi.start, margin = 10.dp)
                        width = Dimension.fillToConstraints
                    }
            )

            Text(text = stringResource(id = R.string.mi),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(mi){
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
            )

            Text(text = stringResource(id = R.string.km),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(km){
                    top.linkTo(mi.bottom)

                    end.linkTo(parent.end)
                }
            )

            Text(text = stringResource(id = R.string.name_ascent),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(ascent){
                    start.linkTo(parent.start)
                    top.linkTo(valueDestination.bottom)
                }
            )

            Text(text = stringResource(id = R.string.value_avg),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(valueAscent){
                    end.linkTo(valueDestination.end)
                    top.linkTo(valueDestination.bottom)
                }
            )

            Text(text = stringResource(id = R.string.name_total),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(totalDestination){
                    start.linkTo(parent.start)
                    top.linkTo(ascent.bottom)
                    bottom.linkTo(parent.bottom)
                }
            )

            Text(text = stringResource(id = R.string.value_total_destination),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(valueTotalDestination){
                    end.linkTo(valueDestination.end)
                    top.linkTo(valueAscent.bottom)
                    bottom.linkTo(parent.bottom)
                }
            )

        }
    }

}