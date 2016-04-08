// This code was adapted from the Bintray Gradle Plugin

package fasttrackjvm.apitesting


import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j
import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.JSON

/**
 * @author Schalk W. Cronjé
 */
class BintrayClient extends RESTClient {

    BintrayClient(Map properties = [:]) {
        super('https://api.bintray.com')

        properties.with {
            if(proxyHost && proxyPort) {
                this.setProxy(proxyHost, proxyPort, null)
            }
            if(ignoreSSLIssues) {
                this.ignoreSSLIssues()
            }
            this.auth.basic userName, apiKey
            this.headers.Authorization = """Basic ${"${userName}:${apiKey}".toString().bytes.encodeBase64()}"""
        }

    }

    def get( final Map query ) {
        RESTCall('get',query)
    }

    private def RESTCall(
        final String method,
        final Map properties
    ) {

        assert properties.path?.size()

        Map requestArgs = [path: properties.path]

        requestArgs['contentType'] = properties.contentType ?: JSON

        ['body','query','headers'].each {
            if(properties[it]) {
                requestArgs[it] = properties[it]
            }
        }

        try {
            HttpResponseDecorator response = super."$method"(requestArgs)
            response.data
        } catch (HttpResponseException e) {
            new JsonBuilder([message: e.message, code: e.statusCode])
        }
    }
}
