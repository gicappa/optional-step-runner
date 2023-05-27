package ssr;

import java.util.List;

public record Context(
        String domainName,
        String deviceId,
        String interfaceId,
        List<String> availableIps,
        String reservedIpAddress) {

    public Context() {
        this(null, null, null, null, null);
    }

    public Context domainName(String dn) {
        return new Context(dn, deviceId, interfaceId, availableIps, reservedIpAddress);
    }

    public Context deviceId(String di) {
        return new Context(domainName, di, interfaceId, availableIps, reservedIpAddress);
    }

    public Context interfaceId(String ii) {
        return new Context(domainName, deviceId, ii, availableIps, reservedIpAddress);
    }

    public Context availableIps(List<String> ai) {
        return new Context(domainName, deviceId, interfaceId, ai, reservedIpAddress);
    }

    public Context reserveIp(String ia) {
        return new Context(domainName, deviceId, interfaceId, availableIps, ia);
    }
}
