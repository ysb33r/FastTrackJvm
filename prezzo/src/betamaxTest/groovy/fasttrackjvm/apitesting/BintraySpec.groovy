package fasttrackjvm.apitesting

import org.junit.Rule
import software.betamax.ProxyConfiguration
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule
import spock.lang.Shared
import spock.lang.Specification


/**
 * @author Schalk W. Cronjé
 */
class BintraySpec extends Specification {

    static final String BINTRAY_USERNAME = System.getProperty('BINTRAY_USERNAME') ?: 'fakeBintrayUser'
    static final String BINTRAY_APIKEY   = System.getProperty('BINTRAY_APIKEY')   ?: 'fakeBintrayApiKey'

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
    @Betamax(tape='files')
    def "Source jar must be posted"() {
        given: "A publication has been made"
        assert client != null

        when: 'The list of files is requested from the repository'
        def list = get path : '/packages/ysb33r/grysb33r/bintray-gradle-plugin/files'

        then: 'The bintray source jar should be included'
        list?.find { it?.path == 'org.ysb33r.gradle/bintray/0.0.5/bintray-0.0.5-sources.jar'}
    }
}