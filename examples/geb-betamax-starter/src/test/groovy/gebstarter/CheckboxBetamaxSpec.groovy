package gebstarter

import geb.spock.GebSpec
import org.junit.Rule
import org.openqa.selenium.By
import spock.lang.Ignore
import software.betamax.ProxyConfiguration
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule
import spock.lang.Shared

class CheckboxExampleSpec extends GebSpec {

    static final String webroot = "http://the-internet.herokuapp.com/"

    @Shared ProxyConfiguration configuration = ProxyConfiguration.builder().
        tapeRoot(new File('src/test/resources/betamax/tapes')).
        ignoreLocalhost(false).
        proxyPort( 15050 ).
        sslEnabled(true).
        build()

    @Rule RecorderRule recorder = new RecorderRule(configuration)


//    @Ignore
    @Betamax(tape='files',mode=TapeMode.READ_WRITE)
    def "Learn about testing checkboxes"() {
        when: "I go to that the-internet site"
        go "${webroot}/checkboxes"

        then: 'I am expecting Checkbox page'
        $('h3').text() == 'Checkboxes'

        and: 'The checkbox states are no & yes'
        $(By.id('checkboxes')).$('input')*.@checked == ['', 'true']
    }

}
