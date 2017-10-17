package com.sunchangpeng.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sunchangpeng
 */
public class StringFormatTest {
    @Test
    public void test_null() {
        String message = StringFormat.format(null, "world");
        Assert.assertNull(message);

        message = StringFormat.format("Hi {}.", new Object[]{null});
        Assert.assertEquals("Hi null.", message);

        message = StringFormat.format("Hi {}.");
        Assert.assertEquals("Hi {}.", message);
    }

    @Test
    public void test_arg() {
        String message = StringFormat.format("Hi {}.", "world");
        Assert.assertEquals("Hi world.", message);

        message = StringFormat.format("Hi {}.", "world", "sun");
        Assert.assertEquals("Hi world.", message);

        message = StringFormat.format("Hi {} {}.", "world");
        Assert.assertEquals("Hi world {}.", message);
    }

    @Test
    public void test_array() {
        String message = StringFormat.format("Hi {}.", new boolean[]{true, false, true});
        Assert.assertEquals("Hi [true, false, true].", message);

        message = StringFormat.format("Hi {}.", new byte[]{1, 2, 3});
        Assert.assertEquals("Hi [1, 2, 3].", message);

        message = StringFormat.format("Hi {}.", new char[]{'1', '2', '3'});
        Assert.assertEquals("Hi [1, 2, 3].", message);

        message = StringFormat.format("Hi {}.", new short[]{1, 2, 3});
        Assert.assertEquals("Hi [1, 2, 3].", message);

        message = StringFormat.format("Hi {}.", new int[]{1, 2, 3});
        Assert.assertEquals("Hi [1, 2, 3].", message);

        message = StringFormat.format("Hi {}.", new long[]{1L, 2L, 3L});
        Assert.assertEquals("Hi [1, 2, 3].", message);

        message = StringFormat.format("Hi {}.", new float[]{1F, 2F, 3F});
        Assert.assertEquals("Hi [1.0, 2.0, 3.0].", message);

        message = StringFormat.format("Hi {}.", new double[]{1D, 2D, 3D});
        Assert.assertEquals("Hi [1.0, 2.0, 3.0].", message);

        message = StringFormat.format("Hi {}.", new Object[]{new String[]{"1", "2", "3"}});
        Assert.assertEquals("Hi [1, 2, 3].", message);
    }

    @Test
    public void test_delim_str() {
        String message = StringFormat.format("Hi world.", "world");
        Assert.assertEquals("Hi world.", message);

        message = StringFormat.format("Set {1,2,3} is not equal to {}.", "1,2");
        Assert.assertEquals("Set {1,2,3} is not equal to 1,2.", message);
    }


    @Test
    public void test_escape_char() {
        String message = StringFormat.format("Set \\{} is not equal to {}.", "1,2");
        Assert.assertEquals("Set {} is not equal to 1,2.", message);

        message = StringFormat.format("File name is C:\\\\{}.", "file.zip");
        Assert.assertEquals("File name is C:\\file.zip.", message);
    }

    @Test
    public void testCyclicArrays() {
        {
            Object[] cyclicA = new Object[1];
            cyclicA[0] = cyclicA;
            Assert.assertEquals("[[...]]", StringFormat.format("{}", new Object[]{cyclicA}));
        }
        {
            Integer i1 = new Integer(1);
            Integer i2 = new Integer(2);
            Integer i3 = new Integer(3);

            Object[] a = new Object[2];
            a[0] = i1;
            Object[] c = new Object[]{i3, a};
            Object[] b = new Object[]{i2, c};
            a[1] = b;
            Assert.assertEquals("1[2, [3, [1, [...]]]]", StringFormat.format("{}{}", a));
        }
    }

}