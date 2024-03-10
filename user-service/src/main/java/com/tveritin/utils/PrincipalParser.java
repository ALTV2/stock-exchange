package com.tveritin.utils;

import java.security.Principal;

public class PrincipalParser {
    public static String getUsername(Principal principal) {
        return new StringBuilder(principal.getName().substring(9))
                .deleteCharAt(principal.getName().length() - 10).toString();
    }
}
