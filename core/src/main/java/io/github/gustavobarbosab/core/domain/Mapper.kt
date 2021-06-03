package io.github.gustavobarbosab.core.domain

interface Mapper<IN, OUT> {
    fun map(input: IN): OUT
}