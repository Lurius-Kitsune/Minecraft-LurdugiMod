package com.luriusfox.lurdugi.tools;


import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;

public class Gradient
{

    public static MutableComponent GradientStringComponent(String _string, Color _start, Color _end) {
        MutableComponent _result = Component.empty();
        int _size = _string.length();
        int _index = 0;
        for (char _c : _string.toCharArray())
        {
            final Color _color = ClampGradient(_index, _size, _start, _end); 
            _result.append(Component.literal(String.valueOf(_c))
                .withStyle(style -> style.withColor(TextColor.fromRgb(_color.toHex()))));
            _index++;
        }
        return _result;
    }

    private static Color ClampGradient(int _index, int _maxCharacter, Color _start, Color _end)
    {
        float _normalizer;
        float _valueRed = 0;
        float _valueGreen = 0;
        float _valueBlue = 0;

        float _rangeRed = _end.r - _start.r;
        _normalizer = _index * (_rangeRed / _maxCharacter);
        _valueRed += (_start.r + _normalizer);

        float _rangeGreen = _end.g - _start.g;
        _normalizer = _index * (_rangeGreen / _maxCharacter);
        _valueGreen += (_start.g + _normalizer);

        float _rangeBlue = _end.b - _start.b;
        _normalizer = _index * (_rangeBlue / _maxCharacter);
        _valueBlue += (_start.b + _normalizer);

        return new Color((int)_valueRed, (int)_valueGreen, (int)_valueBlue);
    }
}