package quickstart

import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.routing.path

fun main(args: Array<String>) {

    val app = routes(
            "/{bucket}/{name}" bind  GET to { req: Request ->
                val bucket = req.path("bucket")
                val name = req.path("name")
                Response(OK).body("GET $name in $bucket\n")
            },
            "/{bucket}/{name}" bind POST to { req: Request ->
                val bucket = req.path("bucket")
                val name = req.path("name")
                val inputAsString = req.body.stream.bufferedReader().use { it.readText() }
                println(inputAsString)
                Response(OK).body("POST $name in $bucket\n")
            },
            "/{bucket}/{name}" bind DELETE to { req: Request ->
                val bucket = req.path("bucket")
                val name = req.path("name")
                Response(OK).body("DELETE $name in $bucket\n")
            }
    )

    val jettyServer = app.asServer(Jetty(9000)).start()

//    val request = Request(Method.GET, "http://localhost:9000").query("name", "John Doe")
//
//    val client = ApacheClient()
//
//    println(client(request))
//
//    jettyServer.stop()
}