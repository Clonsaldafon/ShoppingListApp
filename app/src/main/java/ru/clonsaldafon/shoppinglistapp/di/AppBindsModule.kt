package ru.clonsaldafon.shoppinglistapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.clonsaldafon.shoppinglistapp.data.db.UserDAO
import ru.clonsaldafon.shoppinglistapp.data.db.UserDatabase
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepositoryImpl
import ru.clonsaldafon.shoppinglistapp.domain.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.domain.LogInUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.SignUpUseCase
import ru.clonsaldafon.shoppinglistapp.domain.SignUpUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppBindsModule {

    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun bindSignUpUseCase(useCase: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    fun bindLogInUseCase(useCase: LogInUseCaseImpl): LogInUseCase

    companion object {

        @Provides
        fun provideContext(app: Application): Context = app.applicationContext

        @Provides
        @Singleton
        fun provideDb(context: Context): UserDatabase =
            Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                "user.db"
            ).build()

        @Provides
        @Singleton
        fun provideUserDao(db: UserDatabase): UserDAO =
            db.userDao

    }

}