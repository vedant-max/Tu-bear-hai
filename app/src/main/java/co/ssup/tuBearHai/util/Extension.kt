package co.ssup.tuBearHai.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> Response<T>.parse(
  onSuccess: suspend (T) -> Unit
) {
  if (isSuccessful) {
    val responseBody = body()
    if (responseBody == null) {
      onSuccess(Unit as T)
    } else {
      onSuccess(responseBody)
    }
  } else {
    throw HttpException(this)
  }
}

@Composable
fun LazyListState.OnEndReached(onLoadMore: () -> Unit) {
  val shouldLoadMore = remember {
    derivedStateOf {
      val totalItemsNumber = layoutInfo.totalItemsCount
      val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
      lastVisibleItemIndex > totalItemsNumber - 2
    }
  }

  LaunchedEffect(Unit) {
    snapshotFlow { shouldLoadMore.value to layoutInfo.totalItemsCount }
      .collect { pair ->
        if (pair.first) {
          onLoadMore()
        }
      }
  }
}

fun <T> MutableList<T>.addPageData(list: List<T>) {
  this.addAll(list)
  val distinctList = this.distinctBy { it.hashCode() }
  this.clear()
  this.addAll(distinctList)
}