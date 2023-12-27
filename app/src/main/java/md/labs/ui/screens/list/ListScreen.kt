package md.labs.ui.screens.list

import android.widget.Toast
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

        val dataOffset = 0
        val elementsPerPage = 20

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Petfinder", style = MaterialTheme.typography.headlineMedium)
            if (!viewModel.isPetsListLoaded)
                CircularProgressIndicator()
            else if (viewModel.errorMessage !== null) {
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    textAlign = TextAlign.Center,
                    text = viewModel.errorMessage!!,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Red
                )
            }
            else {
                val pets by viewModel.pets.collectAsState(
                    initial = emptyList()
                )
                PetsList(pets = ArrayList(pets), originalDataOffset = dataOffset, navController = navController)
            }
        }
    }
}

@Composable
fun PetsList(pets: MutableList<Pet>, originalDataOffset: Int, navController: NavHostController) {
    val context = LocalContext.current

    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        content = {
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
                                navController.navigate(Navigation.Detail.route + "/${pet.id}")
                                Toast
                                    .makeText(context, pet.name, Toast.LENGTH_SHORT)
                                    .show()
                            }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()) {
                            PetPhotoCard(pet)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Pet: " + pet.subject)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Name: " + pet.name)
                        }
                    }
                }
            )
        }
    )
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