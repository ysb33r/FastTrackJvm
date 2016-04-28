package betamaxstarter

import org.junit.Rule
import software.betamax.ProxyConfiguration
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule
import spock.lang.Shared
import spock.lang.Specification


class SimpleApiClientSpec extends Specification {

    @Shared ProxyConfiguration configuration = ProxyConfiguration.builder().
        tapeRoot(new File('src/test/resources/betamax/tapes')).
        ignoreLocalhost(false).
        proxyPort( 15050 ).
        sslEnabled(true).
        build()

    @Rule RecorderRule recorder = new RecorderRule(configuration)

    @Delegate
    SimpleApiClient client = new SimpleApiClient(
        url : 'http://127.0.0.1:15050',
        proxyHost : '127.0.0.1',
        proxyPort : configuration.proxyPort,
        ignoreSSLIssues : true
    )

    @Betamax(tape='files',mode=TapeMode.READ_WRITE)
    def "Learn to test with Betamax"() {

        given: 'We just access the API'
        def list = get path : "/"

        expect: 'The bintray source jar should be included'
        list == 'foo'
    }
}