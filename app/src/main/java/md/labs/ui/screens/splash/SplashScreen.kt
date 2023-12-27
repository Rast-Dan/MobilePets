package md.labs.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import md.labs.R
import md.labs.ui.Navigation

@Composable
fun SplashScreen (navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.animation_cat))
        // Creating state variable of animation, pass the animation to it
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition)
        // Display the animation, subscribing on it's progress
        LottieAnimation(
            composition = composition,
            progress = { logoAnimationState.progress }
        )
        // If animation ends, go to login screen
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            // Don't forgive to delete splash screen from stack, otherwise back button
            // returns us there again
            navController.navigate(Navigation.Login.route) {
                popUpTo(Navigation.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}