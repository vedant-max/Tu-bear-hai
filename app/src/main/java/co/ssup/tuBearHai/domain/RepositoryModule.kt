package co.ssup.tuBearHai.domain

import co.ssup.tuBearHai.data.repositories.MainRepository
import co.ssup.tuBearHai.data.service.BartenderService
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun providesMainRepository(
    bartenderService: BartenderService
  ) = MainRepository(bartenderService)
}