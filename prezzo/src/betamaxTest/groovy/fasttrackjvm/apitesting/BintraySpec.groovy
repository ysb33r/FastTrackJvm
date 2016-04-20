package fasttrackjvm.apitesting

import org.junit.Rule
import software.betamax.ProxyConfiguration
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule
import spock.lang.Shared
import spock.lang.Specification

class BintraySpec extends Specification {

    static final String BINTRAY_USERNAME = System.getProperty('BINTRAY_USERNAME') ?: 'fakeBintrayUser'
    static final String BINTRAY_APIKEY   = System.getProperty('BINTRAY_APIKEY')   ?: 'fakeBintrayApiKey'
    static final String REPO_ROOT = '/packages/ysb33r/grysb33r'

    @Shared ProxyConfiguration configuration = ProxyConfiguration.builder().
        tapeRoot(new File(System.getProperty('BETAMAX_TAPEDIR') ?: 'src/betamaxTest/resources/betmax/tapes')).
        ignoreLocalhost(false).
        defaultMode( System.getProperty('BETAMAX_MAKETAPES') ? TapeMode.READ_WRITE : TapeMode.READ_ONLY  ).
        proxyPort( System.getProperty('BETAMAX_PROXYPORT')?.toInteger() ?: ProxyConfiguration.DEFAULT_PROXY_PORT ).
        sslEnabled(true).
        build()

    @Rule RecorderRule recorder = new RecorderRule(configuration)

    @Delegate
    BintrayClient client = new BintrayClient(
        userName : BINTRAY_USERNAME,
        apiKey : BINTRAY_APIKEY,
        proxyHost : '127.0.0.1',
        proxyPort : configuration.proxyPort,
        ignoreSSLIssues : true
    )

//    @Betamax(tape='files',mode=TapeMode.READ_WRITE)
    // tag::betamax_test[]
    @Betamax(tape='files')
    def "Source jar must be posted"() {

        given: 'The list of files is requested from the repository'
        def list =
            get path : "${REPO_ROOT}/bintray-gradle-plugin/files"

        expect: 'The bintray source jar should be included'
        list?.find {
            it?.path ==
                'org.ysb33r.gradle/bintray/0.0.5/bintray-0.0.5-sources.jar'
        }

    }
    // end::betamax_test[]
}