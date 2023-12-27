package md.labs.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import md.labs.R
import md.labs.model.petfinderdata.Pet
import md.labs.viewmodel.ListScreenViewModel

@Composable
fun DetailScreen(navController: NavHostController, id: Int?, viewModel: ListScreenViewModel = hiltViewModel()) {
    if(id == null) {
        Text(text = "null")
        return;
    }
    val pet: Pet = viewModel.getPetById(id)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "DetailScreen, id=${pet.id}",
            style = MaterialTheme.typography.headlineLarge
        )
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Pet: " + pet.subject)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Name: " + pet.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    val navController = rememberNavController()
    DetailScreen(navController, null)
}