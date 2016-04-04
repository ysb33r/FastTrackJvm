package fasttrackjvm.calculator;

// tag::spock_interface[]
public interface RemoteCalculator {
    public Number plus(Number... args);
}
// end::spock_interface[]
