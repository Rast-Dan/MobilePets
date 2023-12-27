package md.labs.ui.screens.login.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Oval(contentAlignment: Alignment, offX: Dp, offY: Dp,
         width: Dp, height: Dp, color: Color
) {
    Box (
        modifier = Modifier
            .wrapContentSize(contentAlignment)
            .offset(offX, offY),
        contentAlignment = contentAlignment
    ) {
        Canvas(
            modifier = Modifier
                .width(width)
                .height(height)
        ) {
            drawOval(
                color = color
            )
        }
    }
}

@Preview
@Composable
private fun OvalPreview() {
    Oval(
        contentAlignment = Alignment.TopStart,
        offX = 32.dp,
        offY = (-32).dp,
        width = 192.dp,
        height = 128.dp,
        color = Color.LightGray
    )
}