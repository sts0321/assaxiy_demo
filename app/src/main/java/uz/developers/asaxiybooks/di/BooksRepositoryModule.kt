package uz.developers.asaxiybooks.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.developers.asaxiybooks.domain.AppRepository
import uz.developers.asaxiybooks.domain.impl.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface BooksRepositoryModule {

    @Binds
    fun getAppRepository(impl: AppRepositoryImpl) : AppRepository
}
