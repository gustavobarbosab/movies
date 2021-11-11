package io.github.gustavobarbosab.core.network.coroutine

import io.gustavobarbosab.coroutinesresult.CoroutineResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class CoroutineResponseAdapter<S : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, S>
) : CallAdapter<S, Call<CoroutineResult<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<CoroutineResult<S>> {
        return CoroutineResultCall(call, errorBodyConverter)
    }
}