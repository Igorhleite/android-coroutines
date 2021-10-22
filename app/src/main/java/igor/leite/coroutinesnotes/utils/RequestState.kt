package igor.leite.coroutinesnotes.utils

import okhttp3.ResponseBody

sealed class RequestState<out T> {
    data class ResponseSuccess<out R>(val value: R) : RequestState<R>()
    data class ResponseFailure(val error: ResponseBody?, val errorCode: Int?) :
        RequestState<Nothing>()
    data class ResponseException(val exception: Exception) : RequestState<Nothing>()
    data class Loading(val status: Boolean) : RequestState<Nothing>()
}