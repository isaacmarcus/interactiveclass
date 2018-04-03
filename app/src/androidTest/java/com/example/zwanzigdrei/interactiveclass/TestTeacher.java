package com.example.zwanzigdrei.interactiveclass;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.Assert.*;
public class TestTeacher {
    @Test
    public void TestTeacher() throws Exception {
        //Fail this on purpose
        boolean actual = LoginActivity.validateTest("1002034","12345"); // thing that will return the value inputed
        assertEquals(false, actual);
    }
}