package io.github.gustavobarbosab.core.result

import io.github.gustavobarbosab.core.domain.Result

fun <IN, OUT> Result<IN>.mapTo(map: (IN) -> OUT): Result<OUT> {
    if (this is Result.Error)
        return this

    val resultData = (this as Result.Success).data
    val resultMapped = map(resultData)
    return Result.Success(resultMapped)
}