package io.gustavobarbosab.coroutinesresult

sealed class CoroutineResult<out T : Any> {

    /**
     * Como o próprio nome diz é usado em caso de sucesso na operação
     */
    data class Success<T : Any>(val data: T?) : CoroutineResult<T>()

    /**
     * Deve ser emitido em casos de erros que nao estejam ligados a aplicação
     * Ex: erro de servidor, erro na base de dados, etc
     */
    data class ExternalError(val error: ExternalErrorData) : CoroutineResult<Nothing>()

    /**
     * Deve ser emitido quando houver erros internos
     * Ex: Falha no parser de algum objeto, falta de internet, etc.
     */
    data class InternalError(val error: Exception) : CoroutineResult<Nothing>()

    /**
     * Usado quando o erro não se enquadra nos anteriores
     */
    data class UnknownError(val error: Throwable?) : CoroutineResult<Nothing>()

    fun <NT : Any> map(mapper: (T?) -> NT): CoroutineResult<NT> {
        return when (this) {
            is Success -> Success(mapper(data))
            is ExternalError -> this
            is InternalError -> this
            is UnknownError -> this
        }
    }
}