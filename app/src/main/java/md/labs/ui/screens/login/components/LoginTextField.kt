package md.labs.ui.screens.login.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import md.labs.ui.theme.TextFieldBorderColor

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = 21.sp,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    forPassword: Boolean = false
) {
    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.labelMedium.copy(
            fontSize = fontSize,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            autoCorrect = keyboardType == KeyboardType.Email,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = if (forPassword) PasswordVisualTransformation()
        /*                  */ else /*       */ VisualTransformation.None,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = TextFieldBorderColor,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = fontSize,
                            textAlign = TextAlign.Center
                        )
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
private fun LoginTextFieldPreview() {
    LoginTextField(
        modifier = Modifier,
        text = "",
        onValueChange = {},
        placeholder = "A text-field placeholder example",
        forPassword = true
    )
}


