package io.github.backjeff.chucknorrisjokes.data_remote.util

import org.koin.core.KoinComponent
import retrofit2.HttpException
import java.io.IOException

class RequestWrapperImpl : RequestWrapper, KoinComponent {

    @Synchronized
    override suspend fun <T> wrapperGenericResponse(
        call: suspend () -> T
    ): T = wrapper(call)

    @Synchronized
    override suspend fun <D> wrapper(
        call: suspend () -> D
    ): D {
        return try {
            call()
        } catch (httpException: HttpException) {
            return handleHttpException(httpException, call)
        } catch (ioException: IOException) {
            throw ServerError()
        } catch (stateException: IllegalStateException) {
            throw ServerError()
        }
    }

    @Synchronized
    private suspend fun <D> handleHttpException(
        httpException: HttpException,
        call: suspend () -> D
    ): D = throw httpException.parseError()

    private fun HttpException.parseError() =
        mapException(message(), code().toString())

    private fun mapException(message: String?, code: String?) =
        getDataSourceException(message = message, code = code)

    private fun getDataSourceException(message: String?, code: String?): DataSourceException =
        DataSourceException(
            message = message ?: "",
            code = code ?: ""
        )

}