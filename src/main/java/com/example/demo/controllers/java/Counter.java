package com.example.demo.controllers.java;

public class Counter
{
    private int count;

    public Counter()
    {
        count = 1;
    }

    public Counter(int init)
    {
        count = init;
    }

    public int get()
    {
        return count;
    }

    public void clear()
    {
        count = 0;
    }

    public int incrementAndGet()
    {
        return ++count;
    }

    public String toString()
    {
        return String.valueOf(count);
    }
}