package io.jmpalazzolo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testPushElement() {
        // Given
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();

        // when
        stack.push("Hola mundo!");

        // then
        Assert.assertEquals("Hola mundo!", stack.pop());
    }

    @Test
    public void testPopItem() {
        // Given
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        stack.push("Hello world!");
        stack.push("Goodbye World!");

        // when
        stack.pop();

        // then
        Assert.assertEquals("Hello world!", stack.pop());
    }

    @Test
    public void testIfEmpty() {
        // Given
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();

        // when

        // then
        Assert.assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testSize() {
        // Given
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        stack.push("Hello world!");

        // when

        // then
        Assert.assertEquals(1, stack.size());
    }

    @Test
    public void testIterator() {
        // Given
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        stack.push("Hello world! 1");
        stack.push("Hello world! 2");

        // when
        // then
        int i = 2;
        for (String s: stack) {
            Assert.assertEquals("Hello world! " + i--, s);
        }
    }
}