package com.splichus.cleverlanceApp;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void sha1Encoder() {
        assertEquals("a1b0b9aadd2e96d8329757c0b2e3b8c1b234aeb6", Utils.sha1Encoder("poklop"));
    }

    @Test
    public void base64Decoder() {

    }
}
