package uz.gita.dima.apicontactcompose.data.source.remote.request

data class VerifyCodeRequest(
    val phone: String,
    val code: String
)