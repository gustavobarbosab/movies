package io.gustavobarbosab.coroutinesresult

import io.gustavobarbosab.coroutinesresult.model.CoroutineResult
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult.Success
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class SafelyCoroutineCall {

    suspend fun <T> safeCallIo(call: suspend () -> T): CoroutineResult<T> =
        withContext(Dispatchers.IO) {
            return@withContext handleCall(call)
        }

    suspend fun <T> safeCallMain(call: suspend () -> T): CoroutineResult<T> =
        withContext(Dispatchers.Main) {
            return@withContext handleCall(call)
        }

    private suspend fun <T> handleCall(call: suspend () -> T): CoroutineResult<T> =
        try {
            Success(call())
        } catch (e: Exception) {
            Error(e)
        }
}
