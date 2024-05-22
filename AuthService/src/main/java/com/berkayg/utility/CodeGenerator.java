package com.berkayg.utility;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class CodeGenerator {
    public static String generateActivationCode() {

        String uuid = UUID.randomUUID().toString();
        String[] split = uuid.split("-");
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : split) {
            stringBuilder.append(s.charAt(0));
        }

        return stringBuilder.toString();
    }
}
