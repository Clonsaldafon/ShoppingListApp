package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.Category

interface GetCategoriesUseCase {

    suspend operator fun invoke(): Result<List<Category>?>

}