package ssr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    private final Client client;
    private final Gson gson;

    public Main() {
        client = new Client();
        gson = new GsonBuilder().serializeNulls().create();
    }

    public static void main() {
        new Main().run();
    }

    public void run() {
        Context context = loadContext();
        printContextInJson(context);
        context = getDomainName(context);
        context = getDeviceId(context);
        context = getInterfaceId(context);
        context = getAvailableIps(context);
        context = reserveIpAddress(context);
        printContextInJson(context);
    }

    private Context getDomainName(Context ctx) {
        if (ctx.domainName() != null)
            return ctx;

        return ctx.domainName(client.getDomainName());
    }

    private Context getDeviceId(Context ctx) {
        if (ctx.deviceId() != null) {
            return ctx;
        }

        return ctx.deviceId(client.createDevice());
    }

    public Context getInterfaceId(Context ctx) {
        if (ctx.interfaceId() != null) {
            return ctx;
        }

        return ctx.interfaceId(client.getInterfaceId(ctx.deviceId()));
    }

    public Context getAvailableIps(Context ctx) {
        if (ctx.availableIps() != null) {
            return ctx;
        }

        return ctx.availableIps(client.getAvailableIps());
    }

    public Context reserveIpAddress(Context ctx) {
        if (ctx.reservedIpAddress() != null) {
            return ctx;
        }
        return ctx.reserveIp(
                client.reserveIp(
                        new ReserveIpRequest(ctx.interfaceId(), ctx.availableIps())));
    }


    private void printContextInJson(Context context) {
        System.out.println(gson.toJson(context));
    }

    private Context loadContext() {
        return new Context();
    }
}