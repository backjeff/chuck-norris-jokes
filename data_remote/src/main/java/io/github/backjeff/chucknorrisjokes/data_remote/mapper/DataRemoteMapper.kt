package io.github.backjeff.chucknorrisjokes.data_remote.mapper

abstract class DataRemoteMapper<R, D> {
    abstract fun toDomain(data: R): D
    abstract fun fromDomain(data: D): R
}