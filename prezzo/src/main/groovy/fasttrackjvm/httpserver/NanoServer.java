package fasttrackjvm.httpserver;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

/**
 * @author Schalk W. Cronjé
 */
public class NanoServer extends NanoHTTPD {
    /**
     * Constructs an HTTP server on given port.
     */
    public NanoServer(int port) {
        super(port);
    }

    @Override
    public void start() throws IOException {
        super.start(NanoHTTPD.SOCKET_READ_TIMEOUT, true);
    }

    public void close() {
        stop();
    }

    @Override
    public Response serve(final IHTTPSession session) {
        String response = "You said: " + session.getInputStream();
        return NanoHTTPD.newFixedLengthResponse(response);
    }

}
