package ssr;

import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.completedStage;

public class Main {
    private final Client client;

    public Main() {
        client = new Client();
    }

    public State execute(State state) throws ExecutionException, InterruptedException {
        return completedStage(state)
                .thenApplyAsync(this::getDomainName)
                .thenApplyAsync(this::createDevice)
                .thenApplyAsync(this::getAvailableIpPrefixes)
                .thenApplyAsync(this::getInterfaceId)
                .thenApplyAsync(this::reserveIpAddressRequest)
                .toCompletableFuture().get();
    }

    public State getDomainName(State state) {
        return state.addStage("domain-name", client.getDomainName());
    }

    public State createDevice(State state) {
        return state.addStage("device-id", client.createDevice());

    }

    public State getAvailableIpPrefixes(State state) {
        var availableIps = client.getAvalablesIps();

        return state.addStage("available-ips", String.join(",", availableIps));
    }

    private State getInterfaceId(State state) {
        return state.addStage("interface-id", client.getInterfaceId(state.get("device-id")));
    }

    private State reserveIpAddressRequest(State state) {
        return state;
    }

    public static void main() throws ExecutionException, InterruptedException {
        System.out.println(new Main().execute(new State()));
    }

}