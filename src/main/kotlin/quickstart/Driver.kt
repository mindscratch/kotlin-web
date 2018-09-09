package quickstart

import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main(args: Array<String>) {

    val app = { request: Request ->
        val inputAsString = request.body.stream.bufferedReader().use { it.readText() }
        println(inputAsString)

        Response(OK).body("Hello, ${request.query("name")}!")
    }

    val jettyServer = app.asServer(Jetty(9000)).start()

//    val request = Request(Method.GET, "http://localhost:9000").query("name", "John Doe")
//
//    val client = ApacheClient()
//
//    println(client(request))
//
//    jettyServer.stop()
}