package rqk.footballapps.api

import java.net.URL
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ApiRepository {
    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}