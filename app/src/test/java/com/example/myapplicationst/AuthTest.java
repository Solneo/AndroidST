package com.example.myapplicationst;

/**
 * Created by Ыщвф on 04.12.2018.
 */


import com.example.myapplicationst.LayoutActivity.AuthInstrument.AuthValidate;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AuthTest {
    @Test
    public void passwordStrongTest() throws Exception {
        assertFalse(AuthValidate.isPasswordStrong("A1"));
        assertFalse(AuthValidate.isPasswordStrong("Mole12"));

        assertTrue(AuthValidate.isPasswordStrong("Molesinki123"));
    }
}
