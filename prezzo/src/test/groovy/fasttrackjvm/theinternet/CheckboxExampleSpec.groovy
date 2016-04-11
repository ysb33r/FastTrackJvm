package fasttrackjvm.theinternet

import geb.module.Checkbox
import geb.spock.GebSpec
import org.openqa.selenium.By
import org.ysb33r.theinternet.TestableWebServer
import spock.lang.AutoCleanup
import spock.lang.Shared

// tag::gebspec[]
class CheckboxExampleSpec extends GebSpec {
// end::gebspec[]

    static final String webroot = "http://the-internet.herokuapp.com/"
//    static final String webroot = "http://localhost:4567"

//    @Shared
//    @AutoCleanup("stop")
//    TestableWebServer server = new TestableWebServer()
//
//    void setupSpec() {
//        server.start()
//    }

    // tag::gebspec_checkboxes[]
    def "Learn about testing checkboxes"() {
        when: "I go to that the-internet site"
        go "${webroot}/checkboxes"

        then: 'I am expecting Checkbox page'
        $('h3').text() == 'Checkboxes'

        and: 'The checkbox states are no & yes'
        $(By.id('checkboxes')).$('input')*.@checked == ['','true']
    }
    // end::gebspec_checkboxes[]
// tag::gebspec[]
}
// end::gebspec[]
