package fasttrackjvm.httpserver

import ratpack.http.MediaType
import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification


/**
 * @author Schalk W. Cronjé
 */
class SimpleHttpServerSpec extends Specification {

    static final int PORT = System.getProperty('SIMPLEHTTPSERVER_PORT')?.toInteger() ?: 65000
    @Shared SimpleHttpServer server = new SimpleHttpServer(PORT,'/')

    void setupSpec() {
        server.start()
    }

    void cleanupSpec() {
        server.stop()
    }

    // tag::testhttpclient_setup[]
    ApplicationUnderTest app = new ApplicationUnderTest() {
        @Override
        URI getAddress() { "http://127.0.0.1:${PORT}".toURI() }
    }

    @Delegate TestHttpClient client = TestHttpClient.testHttpClient(app)
    // end::testhttpclient_setup[]

    // tag::testhttpclient_post[]
    def "The echo path should return what is send to it"() {
        given: "A simple text request"
        requestSpec { pay ->
            pay.body.type(MediaType.PLAIN_TEXT_UTF8).text('Hello, World')
        }

        when: "The data is posted"
        post '/'

        then: "It should be echoed back"
        response.statusCode == 200
        response.body.text == 'You said: Hello, World'
    }
    // end::testhttpclient_post[]

}