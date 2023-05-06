package co.ssup.tuBearHai.domain

import co.ssup.tuBearHai.BuildConfig
import co.ssup.tuBearHai.data.service.BartenderService


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideRetrofit() : Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(logging).build()
    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideBartenderService(
    retrofit: Retrofit
  ): BartenderService =
    retrofit.create(BartenderService::class.java)

}