package md.labs.ui.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import md.labs.R
import md.labs.model.petfinderdata.Pet
import md.labs.ui.Navigation
import md.labs.ui.theme.MobileDevelopmentLabsTheme
import md.labs.viewmodel.ListScreenViewModel
import java.net.URLEncoder

@Composable
fun ListScreen(navController: NavHostController) {
    MobileDevelopmentLabsTheme {

        val viewModel = viewModel<ListScreenViewModel>()
        val context = LocalContext.current
        LaunchedEffect(context) {
            viewModel.loadPetsList(context)
        }

        val dataOffset = 14
        val elementsPerPage = 20

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Petfinder", style = MaterialTheme.typography.headlineMedium)
            if (!viewModel.isPetsListLoaded)
                Text(text = "parsing data...", style = MaterialTheme.typography.bodyMedium)
            else {
                val pets = viewModel.getPets(dataOffset, elementsPerPage)
                if (pets != null)
                    PetsList(
                        pets = pets,
                        originalDataOffset = dataOffset,
                        navController = navController
                    )
            }
            Button(
                onClick = { navController.navigate(Navigation.Detail.route + "/47") }
            ) {
                Text(text = "Go Detail")
            }
        }
    }
}

@Composable
fun PetsList(pets: MutableList<Pet>, originalDataOffset: Int, navController: NavHostController) {
    LazyColumn {
        items(
            count = pets.size,
            key = {
                pets[it].id
            },
            itemContent = { index ->
                val pet = pets[index]
                Card(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .clickable {
                            val petJson = URLEncoder.encode(
                                Gson().toJson(pet),
                                "utf-8")
                            navController.navigate(Navigation.Detail.route + "?petJson=$petJson")
                        }
                ) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = (originalDataOffset + index + 1).toString(), style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.width(16.dp))

                            PetPhotoCard(pet)
                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(text = "Animal: " + pet.subject, style = MaterialTheme.typography.bodyMedium)
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "Name: " + pet.name, style = MaterialTheme.typography.bodyMedium)
                                Spacer(modifier = Modifier.height(8.dp))

                                Row {
                                    Text(text = "Breed: ", style = MaterialTheme.typography.bodyMedium)
                                    Spacer(modifier = Modifier.width(8.dp))

                                    Column {
                                        var primary = pet.breeds.primary
                                        if (pet.breeds.unknown) primary = "Unknown"
                                        Text(text = primary, style = MaterialTheme.typography.bodyMedium)
                                        if (pet.breeds.mixed) {
                                            Spacer(modifier = Modifier.height(4.dp))

                                            val secondary = pet.breeds.secondary ?: "Mixed"
                                            Text(text = secondary, style = MaterialTheme.typography.bodyMedium)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun PetPhotoCard(
    pet: Pet
) {
    Surface(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pet.photosUrl.preview)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.pet_preview),
            placeholder = painterResource(R.drawable.dummy_image),
            error = painterResource(R.drawable.issue_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview() {
    val navController = rememberNavController()
    ListScreen(navController)
}