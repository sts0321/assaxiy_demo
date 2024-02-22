package uz.developers.asaxiybooks.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.data.sourse.PrefImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PrefModule {
    @[Binds Singleton]
    fun providePref(impl: PrefImpl): Pref

}