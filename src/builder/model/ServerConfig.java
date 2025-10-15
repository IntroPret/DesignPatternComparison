package builder.model;

public class ServerConfig {
    private int port;
    private String host;
    private int timeout;
    private int maxConnections;

    // Constructor pribadi untuk digunakan oleh Builder
    private ServerConfig(Builder builder) {
        this.port = builder.port;
        this.host = builder.host;
        this.timeout = builder.timeout;
        this.maxConnections = builder.maxConnections;
    }

    // Getter methods
    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    // Builder class
    public static class Builder {
        private int port;
        private String host;
        private int timeout;
        private int maxConnections;

        public Builder(int port, String host) {
            this.port = port;
            this.host = host;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder maxConnections(int maxConnections) {
            this.maxConnections = maxConnections;
            return this;
        }

        public ServerConfig build() {
            return new ServerConfig(this);
        }
    }
}