package io.github.backjeff.chucknorrisjokes.data_remote.util


interface RequestWrapper {

    suspend fun <T> wrapperGenericResponse(
        call: suspend () -> T
    ): T

    suspend fun <D> wrapper(
        call: suspend () -> D
    ): D

}
