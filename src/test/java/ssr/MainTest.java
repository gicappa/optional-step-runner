package ssr;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class MainTest {

    private ByteArrayOutputStream stdout;

    @BeforeEach
    void beforeEach() {
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, MILLISECONDS);
        Awaitility.setDefaultPollInterval(3, SECONDS);
        Awaitility.setDefaultTimeout(6, SECONDS);

        stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));


    }

    @Test
    void it_executes_main_and_expects_output() {
        Main.main();

        var expected = """
                {"domainName":null,"deviceId":null,"interfaceId":null,"availableIps":null,"reservedIpAddress":null}
                {"domainName":"my.domain.com","deviceId":"en0","interfaceId":"int[en0]","availableIps":["192.168.1.1","192.168.1.2"],"reservedIpAddress":"192.168.1.2"}
                """;

        await().untilAsserted(() ->
                assertThat(stdout.toString()).isEqualTo(expected));

    }
}
