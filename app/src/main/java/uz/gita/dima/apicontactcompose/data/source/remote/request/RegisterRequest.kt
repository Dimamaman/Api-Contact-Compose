package uz.gita.dima.apicontactcompose.data.source.remote.request

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val password: String,
    val confirmPassword: String = "",
    val phone: String
)