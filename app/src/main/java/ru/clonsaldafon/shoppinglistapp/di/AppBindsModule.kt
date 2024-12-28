package ru.clonsaldafon.shoppinglistapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepositoryImpl
import ru.clonsaldafon.shoppinglistapp.domain.SignUpUseCase
import ru.clonsaldafon.shoppinglistapp.domain.SignUpUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface AppBindsModule {

    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun bindSignUpUseCase(useCase: SignUpUseCaseImpl): SignUpUseCase

}