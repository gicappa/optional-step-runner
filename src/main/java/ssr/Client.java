package ssr;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Client {
    public String getDomainName() {
        return runAfterDelay(1, "my.domain.com");
    }

    public String createDevice() {
        return runAfterDelay(1, "en0");
    }

    public List<String> getAvailableIps() {
        return runAfterDelay(1, List.of("192.168.1.1", "192.168.1.2"));
    }

    public String getInterfaceId(String deviceId) {
        return runAfterDelay(1, "int[%s]".formatted(deviceId));
    }

    @SuppressWarnings("all")
    public String reserveIp(ReserveIpRequest request) {
        return "192.168.1.2";
    }

    @SuppressWarnings("all")
    private static <T> T runAfterDelay(int seconds, T x) {
        try {
            sleep(Duration.ofSeconds(seconds));
            return x;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
