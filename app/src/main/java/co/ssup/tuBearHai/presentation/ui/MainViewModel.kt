package co.ssup.tuBearHai.presentation.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ssup.tuBearHai.data.repositories.MainRepository
import co.ssup.tuBearHai.data.response.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

const val WHATSAPP = "com.whatsapp"
private const val MIME_TYPE_TEXT_PLAIN = "text/plain"

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
        Log.d(TAG, "Vedant: ${beers.toList()}")
        beers.addAll(beerResponse)
        currentPage++
        isLoading = false
      }
      dataState.error?.let { error ->
        //Silent error
      }
    }.launchIn(viewModelScope)
  }

  fun onShareText(
    context: Context,
    packageName: String,
    text: String
  ) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.setPackage(packageName)
    shareIntent.type = MIME_TYPE_TEXT_PLAIN
    shareIntent.putExtra(Intent.EXTRA_TEXT, text)
    context.startActivity(shareIntent)
  }
}