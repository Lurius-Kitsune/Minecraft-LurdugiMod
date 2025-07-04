package com.luriusfox.lurdugi.tools;

public class Color {
    
    int r, g, b;
    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    //To hexadecimal
    public int toHex() {
        return (r << 16) | (g << 8) | b;
    }

    public static Color fromHex(int hex) {
        int r = (hex >> 16) & 0xFF;
        int g = (hex >> 8) & 0xFF;
        int b = hex & 0xFF;
        return new Color(r, g, b);
    }
}

