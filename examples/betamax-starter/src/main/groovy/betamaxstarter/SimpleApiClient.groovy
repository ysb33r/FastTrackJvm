package betamaxstarter

import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j
import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.TEXT

/**
 * @author Schalk W. Cronjé
 */
class SimpleApiClient extends RESTClient {

    SimpleApiClient(Map properties = [:]) {
        super(properties.url)

        properties.with {
            if(proxyHost && proxyPort) {
                this.setProxy(proxyHost, proxyPort, null)
            }
            if(ignoreSSLIssues) {
                this.ignoreSSLIssues()
            }
            if(userName && apiKey) {
                this.auth.basic userName, apiKey
                this.headers.Authorization = """Basic ${"${userName}:${apiKey}".toString().bytes.encodeBase64()}"""
            }
        }
    }

    def get( final Map query ) {
        RESTCall('get',query)
    }

    def post( final Map query ) {
        RESTCall('post',query)
    }

    private def RESTCall(
        final String method,
        final Map properties
    ) {
        assert properties.path?.size()
        Map requestArgs = [path: properties.path]
        requestArgs['contentType'] = properties.contentType ?: TEXT

        ['body','query','headers'].each {
            if(properties[it]) {
                requestArgs[it] = properties[it]
            }
        }

        try {
            HttpResponseDecorator response = super."$method"(requestArgs)
            response
//            response.data.with { r ->
//                String s = ''
//                s << r
//            }
        } catch (HttpResponseException e) {
            [message: e.message, code: e.statusCode]
        }
    }
}
