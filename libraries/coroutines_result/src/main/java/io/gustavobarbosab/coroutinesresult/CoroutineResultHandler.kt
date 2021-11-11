package io.gustavobarbosab.coroutinesresult

interface CoroutineResultHandler {

    fun <T : Any> handleResult(
        result: CoroutineResult<T>,
        onSuccess: (T?) -> Unit,
        onError: () -> Unit
    ) {
        when (result) {
            is CoroutineResult.Success -> onSuccess(result.data)
            else -> onError()
        }
    }

    fun <T : Any> handleResult(
        result: CoroutineResult<T>,
        onSuccess: (T?) -> Unit,
        onExternalError: (T?, Int) -> Unit,
        onError: () -> Unit,
    ) {
        when (result) {
            is CoroutineResult.Success -> onSuccess(result.data)
            is CoroutineResult.ExternalError -> onExternalError(result.data, result.code)
            else -> onError()
        }
    }
}