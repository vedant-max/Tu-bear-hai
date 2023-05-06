package co.ssup.tuBearHai.data.service

import co.ssup.tuBearHai.data.response.Beer
import co.ssup.tuBearHai.data.response.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BartenderService {

  @GET("beers")
  suspend fun getBeers(
    @Query("page") page: Int = 1,
    @Query("per_page") perPage: Int = 30
  ): Response<BeerResponse>
}