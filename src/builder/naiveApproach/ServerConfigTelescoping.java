package builder.naiveApproach;

public class ServerConfigTelescoping {
    private int port;
    private String host;
    private int timeout;
    private int maxConnections;

    public ServerConfigTelescoping(int port, String host) {
        this(port, host, 0, 0);
    }

    public ServerConfigTelescoping(int port, String host, int timeout) {
        this(port, host, timeout, 0);
    }

    public ServerConfigTelescoping(int port, String host, int timeout, int maxConnections) {
        this.port = port;
        this.host = host;
        this.timeout = timeout;
        this.maxConnections = maxConnections;
    }
}