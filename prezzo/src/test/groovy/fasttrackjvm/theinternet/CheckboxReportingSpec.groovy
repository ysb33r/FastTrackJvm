package fasttrackjvm.theinternet

import geb.spock.GebReportingSpec
import org.openqa.selenium.By

// tag::gebspec[]
class CheckboxReportingSpec extends GebReportingSpec {
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
    def 'Learn about testing checkboxes'() {
        when: 'I go to that the-internet site'
        go "${webroot}/checkboxes"
        report 'checkbox-screen'

        then: 'I am expecting Checkbox page'
        $('h3').text() == 'Checkboxes'

        and: 'The checkbox states are no & yes'
        $(By.id('checkboxes')).$('input')*.@checked == ['','true']
    }
    // end::gebspec_checkboxes[]
// tag::gebspec[]
}
// end::gebspec[]
