package com.yajith.shopping.ui.gallery;

public class random {
    public int otp()
    {
        int min=1000;
        int max=9999;
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
}
