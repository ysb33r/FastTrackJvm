package fasttrackjvm.theinternet

import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import org.ysb33r.theinternet.TestableWebServer
import spock.lang.AutoCleanup
import spock.lang.Shared

// tag::gebspec[]
class CheckboxReportingSpec extends GebReportingSpec {
// end::gebspec[]

    static final String webroot = "http://the-internet.herokuapp.com"

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
