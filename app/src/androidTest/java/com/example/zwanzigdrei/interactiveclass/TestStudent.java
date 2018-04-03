package com.example.zwanzigdrei.interactiveclass;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.Assert.*;

public class TestStudent {
    @Test
    public void TestStudent() throws Exception {
        boolean actual = LoginActivity.validateTest("1002336","1234");
        assertEquals(false, actual);
    }
}
