package md.labs.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import md.labs.R
import md.labs.viewmodel.LoginScreenViewModel
import md.labs.ui.Navigation
import md.labs.ui.screens.login.components.LoginTextField
import md.labs.ui.screens.login.components.Oval
import md.labs.ui.theme.MobileDevelopmentLabsTheme

@Composable
fun LoginScreen(navController: NavHostController) {
    MobileDevelopmentLabsTheme {
        val focusManager = LocalFocusManager.current
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
        ) {
            BackgroundSection()
            val colorHeadline = Color.Black
            val fontSizeTextFields = 24.sp
            val colorButtonBackground = Color.Black
            val colorButtonText = Color.White

            Box(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopCenter)
                    .offset(y = 226.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleSection(color = colorHeadline)
                    Spacer(modifier = Modifier.height(82.dp))
                    InputFieldsSection(fontSize = fontSizeTextFields)
                    Spacer(modifier = Modifier.height(28.dp))
                    ButtonSection(colorButtonBackground, colorButtonText, navController)
                }
            }
        }
    }
}

@Composable
private fun ButtonSection(
    colorButtonBackground: Color,
    colorButtonText: Color,
    navController: NavHostController
) {
    val focusManager = LocalFocusManager.current
    val viewModel = viewModel<LoginScreenViewModel>()
    val context = LocalContext.current

    Button(
        modifier = Modifier.size(width = 153.dp, height = 41.dp),
        onClick = {
            focusManager.clearFocus()

            //navController.navigate(Navigation.List.route)
            if (viewModel.username.trim().isNotEmpty() && viewModel.password.trim().isNotEmpty()
                && viewModel.email.trim().isNotEmpty())
            {
                Toast.makeText(context, "Completed", Toast.LENGTH_SHORT).show()
                navController.navigate(Navigation.List.route)
            }
            else
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        },
        colors = ButtonDefaults.buttonColors(containerColor = colorButtonBackground),
        contentPadding = PaddingValues()
    ) {
        Text(
            text = stringResource(R.string.login_button_join_us),
            style = MaterialTheme.typography.labelMedium.copy(
                color = colorButtonText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
private fun InputFieldsSection(fontSize: TextUnit) {
    val viewModel = viewModel<LoginScreenViewModel>()

    Column(
        modifier = Modifier.padding(horizontal = 52.dp),
        verticalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        LoginTextField(
            text = viewModel.username,
            onValueChange = { viewModel.updateUsername(it) },
            fontSize = fontSize,
            placeholder = stringResource(R.string.login_placeholder_username),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
        LoginTextField(
            text = viewModel.password,
            onValueChange = { viewModel.updatePassword(it) },
            fontSize = fontSize,
            placeholder = stringResource(R.string.login_placeholder_password),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            forPassword = true
        )
        LoginTextField(
            text = viewModel.email,
            onValueChange = { viewModel.updateEmail(it) },
            fontSize = fontSize,
            placeholder = stringResource(R.string.login_placeholder_email),
            keyboardType = KeyboardType.Email
        )
    }
}

@Composable
private fun BackgroundSection() {
    Oval(Alignment.TopStart, (-64).dp, (-93).dp, 234.dp, 220.dp, Color.Black)
    Oval(Alignment.CenterEnd, 75.dp, 0.dp, 232.dp, 232.dp, Color(0xFFD9D9D9))
    Oval(Alignment.BottomEnd, 60.dp, 90.dp, 220.dp, 220.dp, Color.Black)
    Box (
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .width(60.dp)
            .offset(41.dp, 24.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.app_logo)
        )
    }
}

@Composable
private fun TitleSection(color: Color) {
    Row {
        Text(
            text = stringResource(id = R.string.title_part1),
            style = MaterialTheme.typography.headlineLarge,
            color = color
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = stringResource(id = R.string.title_part2),
            style = MaterialTheme.typography.headlineMedium,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}