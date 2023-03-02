package com.example.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiHeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val newRequest = chain.request().newBuilder()
                .addHeader(COOKIE, "token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjQzMDE5LCJ0cyI6MTY0OTc0NjA1NTc0OCwib25seU9UUCI6ZmFsc2UsImlhdCI6MTY0OTc0NjA1NSwiZXhwIjozNTQxOTA2MDU1fQ.GsvdOULoekfDNx4Nv9mJC1QIiWDR4Lzowq1kbYlFBIo")
                .build()

            chain.proceed(newRequest)
        } catch (e: Throwable) {
            chain.proceed(chain.request())
        }
    }

    companion object {
        private const val COOKIE = "Cookie"
    }
}