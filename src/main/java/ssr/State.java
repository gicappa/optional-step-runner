package ssr;

import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public class State {
    private final ConcurrentHashMap<String, String> output;

    public State() {
        this.output = new ConcurrentHashMap<>();
    }

    public String get(String name) {
        return output.get(name);
    }

    public State addStage(String name, String value) {
        output.put(name, value);
        return this;
    }

    public String toString() {
        return output.entrySet().stream()
                .map(e -> format("k:%s v:%s", e.getKey(),e.getValue()))
                .collect(joining("|"));
    }
}
