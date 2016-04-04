package fasttrackjvm.calculator

class Calculator {
    Number plus(Number... args) {
        args.sum()
    }

    Number multiply(Number... args) {
        args.inject 1, { total,value ->
            total * value
        }
    }

    Number divide(Number... args) {
        args[1..-1].inject args[0], { total,value ->
            total / value
        }
    }
}
