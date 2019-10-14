package com.auth;

import com.auth.Auth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestAuth {

    @Test
    void TestAuthObject() {
        Auth auth = new Auth();
        Assertions.assertNotNull(auth.getClientId());
        Assertions.assertNotNull(auth.getAccessToken());
        Assertions.assertNotNull(auth.getRefreshToken());
        Assertions.assertNotNull(auth.getTokenType());
        Assertions.assertNotNull(auth.getClientId(), "");
        Assertions.assertEquals(auth.getaExpiresIn(), 1800);
        Assertions.assertEquals(auth.getrExpiresIn(), 7776000);
        Assertions.assertEquals(auth.getTokenType(), "Bearer");
    }
}
