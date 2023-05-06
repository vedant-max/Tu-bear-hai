package co.ssup.tuBearHai.presentation.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.ssup.tuBearHai.R
import co.ssup.tuBearHai.data.response.Beer
import co.ssup.tuBearHai.util.OnEndReached
import co.ssup.tuBearHai.util.RandomColors
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
  val selectedBeer = remember { mutableStateOf<Beer?>(null) }
  val scope = rememberCoroutineScope()
  val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
  val context = LocalContext.current

  ModalBottomSheetLayout(
    sheetContent = {
      BottomSheetItem(beer = selectedBeer.value)
    },
    sheetState = bottomSheetState,
  ) {
    Log.d(TAG, "MainScreen: ${viewModel.beers}")
    val state = rememberLazyListState()
    LazyColumn(
      state = state,
      modifier = Modifier
        .fillMaxSize(),
      verticalArrangement = Arrangement.Center
    ) {
      itemsIndexed(viewModel.beers.toList()) { index, beer ->
        BeerItem(
          beer = beer,
          color = RandomColors.colors[index.mod(12)],
          modifier = Modifier.combinedClickable(
            onLongClick = {
              selectedBeer.value = beer
              scope.launch {
                bottomSheetState.show()
              }
            }
          ) {
            //no-op
          },
          onClick = {
            viewModel.onShareText(
              context,
              WHATSAPP,
              "${beer.name}\n${beer.tagline}\n${beer.description}"
            )
          }
        )
        state.OnEndReached {
          viewModel.getBeers(page = viewModel.currentPage)
        }
      }
    }
  }
}

@Composable
fun BeerItem(modifier: Modifier, beer: Beer, color: Color, onClick: () -> Unit) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .background(color = color)
      .padding(vertical = 16.dp)
  ) {
    AsyncImage(model = beer.image_url, contentDescription = null, modifier = Modifier.size(100.dp))
    Spacer(modifier = Modifier.size(16.dp))
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = beer.name,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
      Spacer(modifier = Modifier.size(32.dp))
      Text(
        text = beer.tagline,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onSurface
      )
    }
    IconButton(onClick = {
      onClick()
    }) {
      Icon(
        painter = painterResource(id = R.drawable.ic_basic_share),
        contentDescription = null,
      )
    }
  }
}

@Composable
fun BottomSheetItem(beer: Beer?) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(color = Color.White)
      .padding(horizontal = 16.dp, vertical = 16.dp)
  ) {
    beer?.let {
      Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
          model = beer.image_url,
          contentDescription = null,
          modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = beer.name, style = MaterialTheme.typography.h4)
      }
      Text(text = beer.tagline, style = MaterialTheme.typography.h5)
      Text(text = beer.description, style = MaterialTheme.typography.subtitle1)
      Text(text = "Food Pairing - ", style = MaterialTheme.typography.subtitle2)
      beer.food_pairing.forEach {
        Text(text = it, style = MaterialTheme.typography.body2)
      }
      Text(text = "Brewers Tips", style = MaterialTheme.typography.subtitle2)
      Text(text = beer.brewers_tips, style = MaterialTheme.typography.body2)
    }
  }
}