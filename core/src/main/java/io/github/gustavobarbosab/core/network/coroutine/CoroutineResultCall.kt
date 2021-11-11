package io.github.gustavobarbosab.core.network.coroutine

import io.gustavobarbosab.coroutinesresult.CoroutineResult
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

internal class CoroutineResultCall<S : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, S>
) : Call<CoroutineResult<S>> {

    override fun enqueue(callback: Callback<CoroutineResult<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@CoroutineResultCall,
                            Response.success(CoroutineResult.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@CoroutineResultCall,
                            Response.success(CoroutineResult.Success(null))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@CoroutineResultCall,
                            Response.success(CoroutineResult.ExternalError(errorBody, code))
                        )
                    } else {
                        callback.onResponse(
                            this@CoroutineResultCall,
                            Response.success(CoroutineResult.UnknownError(null))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> CoroutineResult.InternalError(throwable)
                    else -> CoroutineResult.UnknownError(throwable)
                }
                callback.onResponse(this@CoroutineResultCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = CoroutineResultCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<CoroutineResult<S>> {
        throw UnsupportedOperationException("CoroutineResult doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}