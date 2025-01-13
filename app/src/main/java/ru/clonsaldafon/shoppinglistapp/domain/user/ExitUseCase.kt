package ru.clonsaldafon.shoppinglistapp.domain.user

interface ExitUseCase {

    suspend operator fun invoke(): Result<String?>

}