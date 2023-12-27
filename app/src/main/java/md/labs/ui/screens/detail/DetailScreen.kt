package md.labs.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import md.labs.R
import md.labs.model.petfinderdata.Pet
import md.labs.viewmodel.ListScreenViewModel
import md.labs.model.petfinderdata.Attributes
import md.labs.model.petfinderdata.Environment
import md.labs.model.petfinderdata.Pet
import md.labs.ui.theme.MobileDevelopmentLabsTheme

@Composable
fun DetailScreen(navController: NavHostController, id: Int?, viewModel: ListScreenViewModel = hiltViewModel()) {
    MobileDevelopmentLabsTheme {
        if (id == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "DetailScreen, pet=$pet",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            return@MobileDevelopmentLabsTheme
        }
        val pet: Pet = viewModel.getPetById(id)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ========== NAME ==========
                BodyTitle(
                    text = pet.name,
                    style = MaterialTheme.typography.headlineLarge,
                    spacingBefore = 32.dp
                )
                // ========== PHOTO ==========
                Spacer(Modifier.height(16.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pet.photosUrl.preview)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.pet_picture),
                    placeholder = painterResource(R.drawable.dummy_image),
                    error = painterResource(R.drawable.issue_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1.0F)
                        .clip(RoundedCornerShape(128.dp))
                )
                // ========== LOCATION ==========
                Spacer(Modifier.height(8.dp))
                with(pet.contact.address) {
                    BodyText("$city, $state")
                }
                // ========== ABOUT ==========
                BodyTitle(
                    "About",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp)
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    // ========== COMMON ==========
                    BodyTitle(text = "Common characteristics")
                    Spacer(Modifier.height(8.dp))
                    Row {
                        Spacer(Modifier.width(16.dp))
                        Column {
                            // age, gender, size
                            BodyText(text = "${pet.age} * ${pet.gender} * ${pet.size}")
                            // colors
                            Row {
                                if (pet.colors.primary != null) {
                                    BodyText(text = "${pet.colors.primary}")
                                    if (pet.colors.secondary != null)
                                        BodyText(text = " * ${pet.colors.secondary}")
                                    if (pet.colors.tertiary != null)
                                        BodyText(text = " * ${pet.colors.tertiary}")
                                }
                            }
                            // attributes
                            if (checkAttributes(pet.attributes)) {
                                Spacer(Modifier.height(8.dp))
                                Column {
                                    with(pet.attributes) {
                                        PetAttributeLine(shotsCurrent, "Shots current", "+", "-")
                                        PetAttributeLine(specialNeeds, "Special needs", "+", "-")
                                        PetAttributeLine(declawed, "Declawed", "+", "-")
                                        PetAttributeLine(spayedNeutered, "Spayed / Neutered", "+", "-")
                                        PetAttributeLine(houseTrained, "House trained", "+", "-")
                                    }
                                }
                            }
                        }
                    }
                    // ========== BREED ==========
                    BodyTitle(text = "Breed")
                    Spacer(Modifier.height(8.dp))
                    Row {
                        Spacer(Modifier.width(16.dp))
                        Column {
                            var primary = pet.breeds.primary
                            if (pet.breeds.unknown) primary = "Unknown"
                            BodyText(text = primary)

                            if (pet.breeds.mixed) {
                                val secondary = pet.breeds.secondary ?: "Mixed"
                                BodyText(text = secondary)
                            }
                        }
                    }
                    // ========== COAT ==========
                    if (pet.coat != null) {
                        BodyTitle(text = "Coat")
                        Spacer(Modifier.height(8.dp))
                        Row {
                            Spacer(Modifier.width(16.dp))
                            BodyText(text = pet.coat)
                        }
                    }
                    // ========== ENVIRONMENT ==========
                    if (checkEnvironment(pet.environment)) {
                        BodyTitle(text = "At home...")
                        Spacer(Modifier.height(8.dp))
                        Row {
                            Spacer(Modifier.width(16.dp))
                            Column {
                                with(pet.environment) {
                                    PetAttributeLine(cats, "with cats", "Good", "Bad")
                                    PetAttributeLine(children, "with children", "Good", "Bad")
                                    PetAttributeLine(dogs, "with dogs", "Good", "Bad")
                                }
                            }
                        }
                    }
                    // ========== DESCRIPTION ==========
                    BodyTitle(text = "Description")
                    Spacer(Modifier.height(8.dp))
                    Row {
                        Spacer(Modifier.width(16.dp))
                        if (pet.description != null) {
                            Spacer(Modifier.height(8.dp))
                            BodyText(text = pet.description)
                        }
                    }

                    Spacer(Modifier.height(32.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        HyperlinkText(
                            fullText = "For more details check the website.",
                            linkText = listOf("the website"),
                            hyperlinks = listOf("google.com") //pet.websiteLink
                        )
                    }

                    Spacer(Modifier.height(64.dp))
                }
            }
        }
    }
}

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    hyperlinks: List<String>,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    linkTextColor: Color = Color.Blue,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = textStyle.fontSize,
                    fontWeight = textStyle.fontWeight,
                    fontFamily = textStyle.fontFamily,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = textStyle.fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

private fun checkEnvironment(environment: Environment): Boolean {
    var flag = false
    with(environment) {
        if (cats != null) flag = true
        if (children != null) flag = true
        if (dogs != null) flag = true
    }
    return flag
}

@Composable
private fun PetAttributeLine(flag: Boolean?, text: String, ifTrue: String, ifFalse: String) {
    if (flag != null) {
        val beginning = if (flag) ifTrue else ifFalse
        BodyText(text = "$beginning $text")
    }
}

private fun checkAttributes(attributes: Attributes): Boolean {
    var flag = false
    with(attributes) {
        if (shotsCurrent != null) flag = true
        if (specialNeeds != null) flag = true
        if (declawed != null) flag = true
        if (spayedNeutered != null) flag = true
        if (houseTrained != null) flag = true
    }
    return flag
}

@Composable
private fun BodyText(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Text(
        text = text,
        style = style
    )
}

@Composable
private fun BodyTitle(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    spacingBefore: Dp = 24.dp
) {
    Spacer(Modifier.height(spacingBefore))
    Text(
        text = text,
        style = style
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    val navController = rememberNavController()
    DetailScreen(navController, null)
}