package com.techery.spares.networking;

/**
 * Created by zen on 11/15/13.
 */
public class NetworkingModuleConfig {
    private final String serverAddress;

    public NetworkingModuleConfig(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServerAddress() {
        return serverAddress;
    }
}
