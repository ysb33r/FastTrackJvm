package fasttrackjvm.calculator

import fasttrackjvm.calculator.Calculator
import spock.lang.Specification
import spock.lang.Unroll

// tag::basic_spock[]
class CalculatorSpec extends Specification {
// end::basic_spock[]

    // tag::basic_spock[]
    def "A calculator must be able to add numbers"() {

        given: 'That I have a calculator'
        def calc = new Calculator()

        when: "I add 2, 3 and -20"
        def answer = calc.plus 2,3,-20

        then: "The answer should be -15"
        answer == -15
    }
    // end::basic_spock[]

    // tag::data_driven_spock_1[]
    @Unroll
    def "A calculator must be able to multiply"() {

        given: 'That I have a multiplying calculator'
        def calc = new Calculator()

        expect: "The answer to be #answer"
        answer == calc.multiply (a,b,c)

        where: "The operation is #a * #b * #c"
        a | b | c || answer
        3 | 7 | 5 || 105
        1 | 3 | 0 || 0
        2 | -1| 1 || -2
    }
    // end::data_driven_spock_1[]

    // tag::exception_spock[]
    def "Dividing by zero should throw an exception"() {
        given: 'That I have a dividing calculator'
        def calc = new Calculator()

        when: "I divide by zero"
        calc.divide 1,0

        then: "I expect an error"
        thrown(ArithmeticException)
    }
    // end::exception_spock[]

    // tag::interface_spock[]
    def "Remote calculator"() {
        given: "A remote calculator is available"
        def calc = Mock(RemoteCalculator)

        when: "Multiple items are sent for addition"
        calc.plus 1,2,3,4,5

        then: "The calculator is only called once"
        1 * calc.plus(_)
    }
    // end::interface_spock[]

// tag::basic_spock[]
}
// end::basic_spock[]
