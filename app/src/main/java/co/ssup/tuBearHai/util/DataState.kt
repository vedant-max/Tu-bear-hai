package co.ssup.tuBearHai.util

data class DataState<out T>(
  val data: T? = null,
  val error: String? = null,
  val isLoading: Boolean = false
) {
  companion object {
    fun <T> success(data: T): DataState<T> = DataState(data = data)
    fun <T> error(message: String): DataState<T> = DataState(error = message)
    fun <T> loading(): DataState<T> = DataState(isLoading = true)
  }
}
