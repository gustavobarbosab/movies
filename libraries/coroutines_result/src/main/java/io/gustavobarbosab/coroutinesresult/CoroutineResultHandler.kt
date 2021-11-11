package io.gustavobarbosab.coroutinesresult

interface CoroutineResultHandler {

    fun <T : Any> handleResult(
        result: Response<T, Nothing>,
        onSuccess: (T) -> Unit,
        onError: () -> Unit
    ) {
        when (result) {
            is Response.Success -> onSuccess(result.data)
            else -> onError()
        }
    }

    fun <T : Any, U : Any> handleResult(
        result: Response<T, U>,
        onSuccess: (T) -> Unit,
        onExternalError: (U, Int) -> Unit,
        onError: () -> Unit,
    ) {
        when (result) {
            is Response.Success -> onSuccess(result.data)
            is Response.ExternalError -> onExternalError(result.data, result.code)
            else -> onError()
        }
    }
}