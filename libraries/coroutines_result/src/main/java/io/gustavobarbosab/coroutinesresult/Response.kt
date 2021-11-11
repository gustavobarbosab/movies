package io.gustavobarbosab.coroutinesresult

sealed class Response<out T : Any, out U : Any> {

    /**
     * Como o próprio nome diz é usado em caso de sucesso na operação
     */
    data class Success<T : Any>(val data: T) : Response<T, Nothing>()

    /**
     * Deve ser emitido em casos de erros que nao estejam ligados a aplicação
     * Ex: erro de servidor, erro na base de dados, etc
     */
    data class ExternalError<U : Any>(val data: U, val code: Int) : Response<Nothing, U>()

    /**
     * Deve ser emitido quando houver erros internos
     * Ex: Falha no parser de algum objeto, falta de internet, etc.
     */
    data class InternalError(val error: Exception) : Response<Nothing, Nothing>()

    /**
     * Usado quando o erro não se enquadra nos anteriores
     */
    data class UnknownError(val error: Throwable) : Response<Nothing, Nothing>()


    fun <NT : Any, NU : Any> map(
        successMapper: (T) -> NT,
        externalErrorMapper: (U) -> NU
    ): Response<NT, NU> {
        return when (this) {
            is Success -> Success(successMapper(data))
            is ExternalError -> ExternalError(externalErrorMapper(data), code)
            is InternalError -> this
            is UnknownError -> this
        }
    }

    fun <NT : Any> map(
        successMapper: (T) -> NT
    ): Response<NT, Nothing> {
        return when (this) {
            is Success -> Success(successMapper(data))
            is InternalError -> this
            is UnknownError -> this
            is ExternalError -> UnknownError(Exception())
        }
    }
}