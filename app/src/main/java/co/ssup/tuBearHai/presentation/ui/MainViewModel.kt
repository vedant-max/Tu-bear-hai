package co.ssup.tuBearHai.presentation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ssup.tuBearHai.data.repositories.MainRepository
import co.ssup.tuBearHai.data.response.Beer
import co.ssup.tuBearHai.data.response.BeerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
  val beers = mutableStateListOf<Beer>()
  var currentPage by mutableStateOf(1)
  var isLoading by mutableStateOf(false)

  init {
    getBeers(page = 1)
  }

  fun getBeers(page: Int, perPage: Int = 30) {
    repository.getBeers(page = page, perPage = perPage).onEach { dataState ->
      isLoading = dataState.isLoading
      dataState.data?.let { beerResponse ->
        beers.addAll(beerResponse.response)
        currentPage++
        isLoading = false
      }
      dataState.error?.let { error ->
        //Silent error
      }
    }.launchIn(viewModelScope)
  }
}