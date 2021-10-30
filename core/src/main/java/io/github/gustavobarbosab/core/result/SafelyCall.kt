package io.github.gustavobarbosab.core.result

import io.github.gustavobarbosab.core.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SafelyCall {

    suspend fun <T> safeCallOnIo(call: suspend () -> T): Result<T> =
        withContext(Dispatchers.IO) {
            return@withContext handleCall(call)
        }

    suspend fun <T> safeCallOnMain(call: suspend () -> T): Result<T> =
        withContext(Dispatchers.Main) {
            return@withContext handleCall(call)
        }

    private suspend fun <T> handleCall(call: suspend () -> T) =
        try {
            Result.Success(call())
        } catch (e: Exception) {
            Result.Error(e)
        }
}
