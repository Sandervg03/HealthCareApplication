package com.example.itm.ui.composables.Errors

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun ShakeEffect(content: @Composable () -> Unit) {
    val offsetX = remember { Animatable(0f) }

    // Trigger the animation
    LaunchedEffect(Unit) {
        offsetX.animateTo(
            targetValue = 10f,
            animationSpec = repeatable(
                iterations = 3,
                animation = tween(durationMillis = 100, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Box(
        modifier = Modifier.graphicsLayer(
            translationX = offsetX.value
        )
    ) {
        content()
    }
}
