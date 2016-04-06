package fasttrackjvm.httpserver

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import groovy.transform.CompileStatic

/**
 * @author Schalk W. Cronjé
 */
@CompileStatic
class Handler implements HttpHandler {
    @Override
    void handle(HttpExchange httpExchange) throws IOException {
        String response = "You said: ${httpExchange.requestBody}"
        httpExchange.sendResponseHeaders(200, response.size())

        httpExchange.responseBody.withWriter { w ->
            w << response
        }
    }
}
