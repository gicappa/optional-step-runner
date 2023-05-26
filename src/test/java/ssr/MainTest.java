package ssr;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class MainTest {

    private Main main;
    private ByteArrayOutputStream stdout;

    @BeforeEach
    void beforeEach() {
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, MILLISECONDS);
        Awaitility.setDefaultPollInterval(3, SECONDS);
        Awaitility.setDefaultTimeout(10, SECONDS);

        main = new Main();

        stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));


    }

    @Test
    void it_executes_main_and_expects_output() throws ExecutionException, InterruptedException {
        Main.main();
        await().untilAsserted(() -> assertThat(stdout.toString()).isEqualTo("k:domain-name v:my.domain.com|k:available-ips v:1.3.3.5,3.2.1.3|k:device-id v:device-123|k:interface-id v:interface-id[device-123]"));

    }
}
