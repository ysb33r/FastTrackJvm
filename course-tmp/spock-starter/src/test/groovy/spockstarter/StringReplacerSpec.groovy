package spockstarter

import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll


class StringReplacerSpec extends Specification {

    void setup() {}
    void cleanup() {}
    void setupSpec() {}
    void cleanupSpec() {}


    def "We want to test our string replacement"() {
        given: "I have a string"
        StringReplacer replacer = new StringReplacer()

        expect: "Pairs to be replaced by underscores"
        replacer.replace( 'bbaaa') == '__aaa'
    }

    def "Let's run our test differently"() {
        given: "I have a replacer"
        StringReplacer replacer = new StringReplacer()

        when: "I send it 'bbaaa'"
        String result = replacer.replace( 'bbaaa')

        then: "'bb' should become '__'"
        result.size() == 5
        result == '__aaa'

    }

    @Unroll
    def "Let's test a selection of strings (#input1,#input2,#output)"() {
        given: "I have a string"
        StringReplacer replacer = new StringReplacer()
        String str = input1 + input2

        expect: "Pairs (#input1,#input2) to be replaced by underscores"
        replacer.replace( str ) == output

        where:
        input1 | input2  || output
        'bba'  | 'aa'    || '__aaa'
        'aaabb'| 'cccaadddde' || 'aaa__ccc__dddde'
        'aaa'  | 'bb'    || 'aaa__'
    }
}