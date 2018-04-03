package com.example.zwanzigdrei.interactiveclass;

/**
 * Created by mengyuan on 3/16/2018.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.Assert.*;

public class TestInvalidUser {
    @Test
    public void TestInvalidUser() throws Exception {
        boolean actual = LoginActivity.validateTest("hi","1234");
        assertEquals(false, actual);
    }
}
