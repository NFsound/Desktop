package models.core

data class Account(
    val id: String,
    val name: String,
    val surname: String,
    val nickname: String,
    val status: String,
    val password: String
) {
}