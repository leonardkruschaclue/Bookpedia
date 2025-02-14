package com.plcoding.bookpedia.core.domain

import kotlin.reflect.typeOf

sealed interface Optional<out T> {
    data class Just<out T>(val value: T) : Optional<T>
    data object None : Optional<Nothing>
}

inline fun <T, R> Optional<T>.map(f: (T) -> R): Optional<R> {
    return when (this) {
        is Optional.Just<T> -> Optional.Just(f(this.value))
        is Optional.None -> Optional.None
    }
}

fun <T> Optional<T>.orElse(alternative: T): T {
    return when (this) {
        is Optional.Just<T> -> this.value
        is Optional.None -> alternative
    }
}

inline fun <reified T : Any?, reified R : Any> T.toOptional(): Optional<R> {
    return when (this) {
        null -> Optional.None
        else -> Optional.Just<R>(if (this is R) this else throw Exception("Type ${typeOf<T>()} could not be converted to Optional"))
    }
}