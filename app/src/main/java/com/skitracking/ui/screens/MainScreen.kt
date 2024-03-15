package com.skitracking.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.skitracking.ui.ScreenState
import com.skitracking.ui.screens.ui_block.DestinationBlock
import com.skitracking.ui.screens.ui_block.LatBlock
import com.skitracking.ui.screens.ui_block.LonBlock
import com.skitracking.ui.screens.ui_block.MaxSpeedBlock
import com.skitracking.ui.theme.Dimens






@Composable
fun MainScreen(
    screenState: ScreenState,
    startIntent: () -> Intent,
    endIntent:() -> Intent,
) {
    ConstraintLayout(modifier = Modifier
        .padding(Dimens.minDimen)
        .fillMaxWidth(),
        constraintSet = constrains()
    ) {
        MaxSpeedBlock()
        DestinationBlock()
        LatBlock(screenState.lat)
        LonBlock(screenState.long)
        ButtonForLocation(
            startIntent, endIntent
        )

    }
}

private fun constrains() = ConstraintSet{
    val maxSpeed = createRefFor("maxSpeed")
    val destination = createRefFor("destination")
    val lat = createRefFor("lat")
    val lon = createRefFor("lon")
    val buttons = createRefFor("buttons")

    val centerGuideLine = createGuidelineFromEnd(0.5f)

    constrain(maxSpeed){
        start.linkTo(parent.start)
        end.linkTo(centerGuideLine, margin = Dimens.minDimen)
        width = Dimension.fillToConstraints
    }

    constrain(destination){
        start.linkTo(centerGuideLine, margin = Dimens.minDimen)
        end.linkTo(parent.end)
        width = Dimension.fillToConstraints
    }

    constrain(lat){
        start.linkTo(parent.start)
        end.linkTo(centerGuideLine, margin = Dimens.minDimen)
        top.linkTo(maxSpeed.bottom, margin = Dimens.minDimen)
        width = Dimension.fillToConstraints
    }

    constrain(lon) {
        start.linkTo(centerGuideLine)
        end.linkTo(parent.end)
        top.linkTo(maxSpeed.bottom, margin = Dimens.minDimen)
        width = Dimension.fillToConstraints
    }

    constrain(buttons){
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(lon.bottom, margin = Dimens.minDimen)
        bottom.linkTo(parent.bottom)
    }


}

@Composable
fun ButtonForLocation(
    startIntent: ()-> Intent,
   endIntent: ()-> Intent,
) {
    Column(
        modifier = Modifier
       .layoutId("buttons")
    ) {

        Spacer(modifier = Modifier.height(250.dp))


        Button(onClick = {
           startIntent()
        })
        {
            Text(text = "Start")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
          endIntent()
        }) {
            Text(text = "Stop")
        }
    }
}



