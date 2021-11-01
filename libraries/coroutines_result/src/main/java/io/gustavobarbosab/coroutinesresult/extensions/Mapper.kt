package io.gustavobarbosab.coroutinesresult.extensions

import io.gustavobarbosab.coroutinesresult.model.CoroutineResult

fun <IN, OUT> CoroutineResult<IN>.mapCoroutineResult(map: (IN) -> OUT): CoroutineResult<OUT> {
    if (this is CoroutineResult.Error)
        return this

    val resultData = (this as CoroutineResult.Success).data
    val resultMapped = map(resultData)
    return CoroutineResult.Success(resultMapped)
}