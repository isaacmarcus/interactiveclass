package com.example.zwanzigdrei.interactiveclass;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.Assert.*;

public class TestInvalidPass {
    @Test
    public void TestInvalidPass() throws Exception {
        boolean actual = LoginActivity.validateTest("1002336","asd");
        assertEquals(false, actual);
    }
}