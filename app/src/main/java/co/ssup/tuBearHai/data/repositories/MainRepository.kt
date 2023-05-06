package co.ssup.tuBearHai.data.repositories

import co.ssup.tuBearHai.data.service.BartenderService
import co.ssup.tuBearHai.util.DataState
import co.ssup.tuBearHai.util.parse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val service: BartenderService) {
  fun getBeers(page: Int, perPage: Int) =
    flow {
      service.getBeers(page, perPage).parse {
        emit(DataState.success(it))
      }
    }.catch {
      emit(DataState.error(it.message ?: "Unknown error"))
    }
}
