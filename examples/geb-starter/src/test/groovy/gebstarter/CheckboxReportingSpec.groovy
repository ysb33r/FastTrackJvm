package gebstarter

import geb.spock.GebReportingSpec
import spock.lang.Ignore
import org.openqa.selenium.By

class CheckboxReportingSpec extends GebReportingSpec {

    static final String webroot = "http://the-internet.herokuapp.com"

    @Ignore
    def 'Learn about testing checkboxes'() {
        when: 'I go to that the-internet site'
        go "${webroot}/checkboxes"
        report 'checkbox-screen'

        then: 'I am expecting Checkbox page'
        $('h3').text() == 'Checkboxes'

        and: 'The checkbox states are no & yes'
        $(By.id('checkboxes')).$('input')*.@checked == ['', 'true']
    }
}
