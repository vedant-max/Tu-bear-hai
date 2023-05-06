package co.ssup.tuBearHai.util

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