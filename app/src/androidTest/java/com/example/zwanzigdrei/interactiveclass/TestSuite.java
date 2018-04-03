package com.example.zwanzigdrei.interactiveclass;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestInvalidUser.class,
        TestInvalidPass.class,
        TestStudent.class,
        TestTeacher.class

})
public class TestSuite {
}