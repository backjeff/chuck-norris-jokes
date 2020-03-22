package io.github.backjeff.chucknorrisjokes.data_remote.util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor() : Interceptor {

    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().also {
            it.addingHeaders()
        }

        return chain.proceed(request.build())
    }

    @Synchronized
    private fun Request.Builder.addingHeaders() {
        header(HEADER_X_RAPIDAPI_HOST, "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
        header(HEADER_X_RAPIDAPI_KEY, "2316b6b1c9mshef0dffc3a660c05p16c288jsn4b2257ca8d95")
        header("accept", "application/json")
    }

}
