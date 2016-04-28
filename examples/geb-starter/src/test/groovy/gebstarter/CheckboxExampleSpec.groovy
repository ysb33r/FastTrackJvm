package gebstarter

import geb.spock.GebSpec
import org.openqa.selenium.By
import spock.lang.Ignore

class CheckboxExampleSpec extends GebSpec {

    static final String webroot = "http://the-internet.herokuapp.com/"

    @Ignore
    def "Learn about testing checkboxes"() {
        when: "I go to that the-internet site"
        go "${webroot}/checkboxes"

        then: 'I am expecting Checkbox page'
        $('h3').text() == 'Checkboxes'

        and: 'The checkbox states are no & yes'
        $(By.id('checkboxes')).$('input')*.@checked == ['', 'true']
    }

}
