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
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepository
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepositoryImpl
import ru.clonsaldafon.shoppinglistapp.data.repository.ProductRepository
import ru.clonsaldafon.shoppinglistapp.data.repository.ProductRepositoryImpl
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepositoryImpl
import ru.clonsaldafon.shoppinglistapp.domain.group.CreateGroupUseCase
import ru.clonsaldafon.shoppinglistapp.domain.group.CreateGroupUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.group.GetMembersUseCase
import ru.clonsaldafon.shoppinglistapp.domain.group.GetMembersUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.group.GetProductsUseCase
import ru.clonsaldafon.shoppinglistapp.domain.group.GetProductsUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.group.JoinToGroupUseCase
import ru.clonsaldafon.shoppinglistapp.domain.group.JoinToGroupUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.product.AddProductUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.AddProductUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.product.GetCategoriesUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.GetCategoriesUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.product.GetProductsByCategoryUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.GetProductsByCategoryUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.user.GetGroupsUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetGroupsUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCaseImpl
import ru.clonsaldafon.shoppinglistapp.domain.user.SignUpUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.SignUpUseCaseImpl
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

    @Binds
    fun bindGetTokenUseCase(useCase: GetTokenUseCaseImpl): GetTokenUseCase

    @Binds
    fun bindGetGroupsUseCase(useCase: GetGroupsUseCaseImpl): GetGroupsUseCase

    @Binds
    fun bindGroupRepository(repository: GroupRepositoryImpl): GroupRepository

    @Binds
    fun bindGetProductsUseCase(useCase: GetProductsUseCaseImpl): GetProductsUseCase

    @Binds
    fun bindJoinToGroupUseCase(useCase: JoinToGroupUseCaseImpl): JoinToGroupUseCase

    @Binds
    fun bindCreateGroupUseCase(useCase: CreateGroupUseCaseImpl): CreateGroupUseCase

    @Binds
    fun bindGetMembersUseCase(useCase: GetMembersUseCaseImpl): GetMembersUseCase

    @Binds
    fun bindAddProductUseCase(useCase: AddProductUseCaseImpl): AddProductUseCase

    @Binds
    fun bindProductRepository(useCase: ProductRepositoryImpl): ProductRepository

    @Binds
    fun bindGetCategoriesUseCase(useCase: GetCategoriesUseCaseImpl): GetCategoriesUseCase

    @Binds
    fun bindGetProductByCategoryUseCase(
        useCase: GetProductsByCategoryUseCaseImpl
    ): GetProductsByCategoryUseCase

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