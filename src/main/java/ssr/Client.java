package ssr;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Client {
    public String getDomainName() {
        return runAfterDelay(1, "my.domain.com");
    }

    public String createDevice() {
        return runAfterDelay(2, "device-123");
    }

    public List<String> getAvalablesIps() {
        return runAfterDelay(2, List.of("1.3.3.5", "3.2.1.3"));
    }

    public String getInterfaceId(String deviceId) {
        return runAfterDelay(1, "interface-id[%s]".formatted(deviceId));
    }

    private static <T> T runAfterDelay(int seconds, T x) {
        try {
            sleep(Duration.ofSeconds(seconds));
            return x;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
