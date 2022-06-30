package com.example.composedepartment.ui

import android.content.res.Configuration
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.example.composedepartment.ui.base.theme.ComposeDepartmentTheme
import com.example.composedepartment.ui.base.theme.PinkDarker
import com.example.composedepartment.ui.base.theme.PinkLighter
import ru.surfstudio.compose.modifier.ifTrue

private const val TOP_CONTAINER_ID = "top"
private const val BOTTOM_CONTAINER_ID = "bottom"

private const val START_ANIMATION_VALUE = 0F
private const val END_ANIMATION_VALUE = 360F

@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun AnimationsScreen() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()
    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)

    val animateMotionLayoutProgress by animateFloatAsState(
        targetValue = if (swipingState.progress.to == SwipingStates.COLLAPSED) {
            swipingState.progress.fraction
        } else {
            1f - swipingState.progress.fraction
        }
    )

    MotionLayout(
        start = startConstraintSet(),
        end = endConstraintSet(),
        progress = animateMotionLayoutProgress,
        modifier = Modifier
            .fillMaxSize()
            .swipeable(
                state = swipingState,
                orientation = Orientation.Vertical,
                anchors = mapOf(
                    0f to SwipingStates.EXPANDED,
                    screenHeight to SwipingStates.COLLAPSED,
                )
            )
    ) {
        val state = swipingState.currentValue
        val animate = state == SwipingStates.COLLAPSED && animateMotionLayoutProgress == 1f

        TopContainer(
            animateMotionLayoutProgress = animateMotionLayoutProgress,
            animate = animate
        )
        BottomContainer(
            animateMotionLayoutProgress = animateMotionLayoutProgress,
            animate = animate
        )
    }
}

private enum class SwipingStates {
    EXPANDED,
    COLLAPSED
}

@Composable
private fun TopContainer(animateMotionLayoutProgress: Float, animate: Boolean) {
    val transition = updateTransition(animate, label = "top animations")

    val rotation by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 2500,
                easing = LinearOutSlowInEasing
            )
        },
        label = "top rotation animation"
    ) { if (it) END_ANIMATION_VALUE else START_ANIMATION_VALUE }

    val offset by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 2500,
                easing = LinearEasing
            )
        },
        label = "top offset animation"
    ) { if (it) END_ANIMATION_VALUE else START_ANIMATION_VALUE }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .layoutId(TOP_CONTAINER_ID)
            .background(PinkDarker)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "build",
                modifier = Modifier
                    .ifTrue(animate) { Modifier.rotate(rotation) }
                    .size(100.dp)
                    .alpha(alpha = animateMotionLayoutProgress)
                    .scale(scale = animateMotionLayoutProgress),
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "right arrow",
                modifier = Modifier
                    .size(100.dp)
                    .alpha(alpha = animateMotionLayoutProgress)
                    .scale(scale = animateMotionLayoutProgress)
                    .offset(x = if (animate) offset.dp else 0.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun BottomContainer(animateMotionLayoutProgress: Float, animate: Boolean) {
    val transition = rememberInfiniteTransition()
    val offsetAnimatable by transition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = 2500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .layoutId(BOTTOM_CONTAINER_ID)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(PinkLighter)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Let's go!",
                modifier = Modifier.alpha(alpha = 1f - animateMotionLayoutProgress),
                style = MaterialTheme.typography.h2,
                color = Color.White
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "swipe arrow",
                modifier = Modifier
                    .size(50.dp)
                    .alpha(alpha = 1f - animateMotionLayoutProgress)
                    .rotate(degrees = -180 * animateMotionLayoutProgress),
                tint = Color.White
            )
        }
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "favorite",
            modifier = Modifier
                .size(150.dp)
                .alpha(alpha = animateMotionLayoutProgress)
                .scale(scale = animateMotionLayoutProgress)
                .offset(
                    x = if (animate) -offsetAnimatable.dp else 0.dp,
                    y = if (animate) -offsetAnimatable.dp else 0.dp,
                ),
            tint = Color.White
        )
    }
}

//region ConstraintSet

private fun startConstraintSet() = ConstraintSet {
    val topContainer = createRefFor(TOP_CONTAINER_ID)
    val bottomContainer = createRefFor(BOTTOM_CONTAINER_ID)

    constrain(topContainer) {
        width = Dimension.fillToConstraints
        height = Dimension.value(56.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    bottomConstraintSet(selfRef = bottomContainer, topRef = topContainer)
}

private fun endConstraintSet() = ConstraintSet {
    val topContainer = createRefFor(TOP_CONTAINER_ID)
    val bottomContainer = createRefFor(BOTTOM_CONTAINER_ID)
    val verticalHalf = createGuidelineFromTop(0.5f)

    constrain(topContainer) {
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        bottom.linkTo(verticalHalf, (-80).dp)
    }

    bottomConstraintSet(selfRef = bottomContainer, topRef = topContainer)
}

private fun ConstraintSetScope.bottomConstraintSet(
    selfRef: ConstrainedLayoutReference,
    topRef: ConstrainedLayoutReference
) {
    constrain(selfRef) {
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(topRef.bottom)
        bottom.linkTo(parent.bottom)
    }
}
//endregion

@Preview(showBackground = true, device = Devices.PIXEL)
@Preview(showBackground = true, device = Devices.PIXEL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AnimationsScreenPreview() {
    ComposeDepartmentTheme {
        AnimationsScreen()
    }
}