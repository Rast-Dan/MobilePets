package md.labs.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import md.labs.R

val Inter = FontFamily(
        listOf(
                Font(resId = R.font.inter_regular, weight = FontWeight.Normal),
                Font(resId = R.font.inter_bold, weight = FontWeight.Bold),
        )
)

// Set of Material typography styles to start with
val Typography = Typography(
        headlineLarge = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp
        ),
        headlineMedium = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 48.sp
        ),

        labelMedium = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 21.sp,
                color = Color(0xFF575757)
        ),

        bodyLarge = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp
        ),
        bodyMedium = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
)