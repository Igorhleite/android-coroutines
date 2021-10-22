package igor.leite.coroutinesnotes.utils.extentions

import igor.leite.coroutinesnotes.utils.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T : Any> Response<T>.safeAwait(): Flow<RequestState<T>> = flow {
    this@safeAwait.run {
        emit(RequestState.Loading(true))
        kotlinx.coroutines.delay(2000)
        try {
            if (isSuccessful) {
                body()?.let { body ->
                    emit(RequestState.ResponseSuccess(body))
                } ?: throw Throwable("Unexpected body null!")
            } else {
                emit(RequestState.ResponseFailure(error = errorBody(), errorCode = code()))
            }
        } catch (e: Exception) {
            emit(RequestState.ResponseException(e))
        }.also {
            emit(RequestState.Loading(false))
        }
    }
}
