package com.example.rtdataassetcoord.common;

import java.util.UUID;

public class UUIDGenerator {

    public static String generate32BitUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
