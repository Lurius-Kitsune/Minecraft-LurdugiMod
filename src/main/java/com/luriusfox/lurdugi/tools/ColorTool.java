package com.luriusfox.lurdugi.tools;


import java.awt.Color;
import java.util.EnumSet;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;

// Create enum for flags
enum GradientFlags {
    NONE,
    OBFUSCATED,
    BOLD,
    ITALIC,
    UNDERLINE,
    STRIKETHROUGH
}

public class ColorTool
{

    public static MutableComponent ComputeStringComponent(String _string, final boolean _shouldBeObfuscated,final Color _gradientStart, final Color _gradientEnd) {
        MutableComponent _result = Component.empty();
        EnumSet<GradientFlags> _flags = EnumSet.noneOf(GradientFlags.class);
        final String _sanitizeString = _string.replace("{k}", "")
        .replace("{b}", "")
        .replace("{i}", "")
        .replace("{u}", "")
        .replace("{s}", "")
        .replace("{r}", "");

        final int _size = _sanitizeString.length();
        int _index = 0;
        int _skip = 0;
        int _skipCount = 0;
        for (char _c : _string.toCharArray())
        {
            if(CheckFlags(_flags, _string, _index + _skipCount, _shouldBeObfuscated) && _skip <= 0) _skipCount += _skip = 3;
            if(_skip > 0) {
                _skip--;
                continue; // Skip the flag characters
            }
            Color _color = ClampGradient(_index, _size, _gradientStart, _gradientEnd); 
            _result.append(Component.literal(String.valueOf(_c))
                .withStyle(_style -> _style.withColor(TextColor.fromRgb(_color.getRGB()))
                                    .withObfuscated(_flags.contains(GradientFlags.OBFUSCATED))
                                    .withBold(_flags.contains(GradientFlags.BOLD))
                                    .withItalic(_flags.contains(GradientFlags.ITALIC))
                                    .withUnderlined(_flags.contains(GradientFlags.UNDERLINE))
                                    .withStrikethrough(_flags.contains(GradientFlags.STRIKETHROUGH))));
            _index++;
        }
        return _result;
    }

    public static MutableComponent ComputeStringComponent(String _string, final boolean _shouldBeObfuscated) {
        MutableComponent _result = Component.empty();
        EnumSet<GradientFlags> _flags = EnumSet.noneOf(GradientFlags.class);

        int _index = 0;
        int _skip = 0;
        int _skipCount = 0;
        for (char _c : _string.toCharArray())
        {
            if(CheckFlags(_flags, _string, _index + _skipCount, _shouldBeObfuscated) && _skip <= 0) _skipCount += _skip = 3;
            if(_skip > 0) {
                _skip--;
                continue; // Skip the flag characters
            }
            _result.append(Component.literal(String.valueOf(_c))
                .withStyle(_style -> _style.withObfuscated(_flags.contains(GradientFlags.OBFUSCATED))
                                    .withBold(_flags.contains(GradientFlags.BOLD))
                                    .withItalic(_flags.contains(GradientFlags.ITALIC))
                                    .withUnderlined(_flags.contains(GradientFlags.UNDERLINE))
                                    .withStrikethrough(_flags.contains(GradientFlags.STRIKETHROUGH))));
            _index++;
        }
        return _result;
    }

    
    private static Color ClampGradient(final int _index, final int _maxCharacter, final Color _gradientStart, final Color _gradientEnd)
    {
        float _normalizer;
        float _valueRed = 0;
        float _valueGreen = 0;
        float _valueBlue = 0;

        float _rangeRed = _gradientEnd.getRed() - _gradientStart.getRed();
        _normalizer = _index * (_rangeRed / _maxCharacter);
        _valueRed += (_gradientStart.getRed() + _normalizer);

        float _rangeGreen = _gradientEnd.getGreen() - _gradientStart.getGreen();
        _normalizer = _index * (_rangeGreen / _maxCharacter);
        _valueGreen += (_gradientStart.getGreen() + _normalizer);

        float _rangeBlue = _gradientEnd.getBlue() - _gradientStart.getBlue();
        _normalizer = _index * (_rangeBlue / _maxCharacter);
        _valueBlue += (_gradientStart.getBlue() + _normalizer);

        return new Color((int)_valueRed, (int)_valueGreen, (int)_valueBlue);
    }

    // edit var by ref
    private static boolean CheckFlags(EnumSet<GradientFlags> _flags, final String _string, final int _index, final boolean _shouldBeObfuscated) {
        boolean _hasFlagUpdated = true;
        if(_string.startsWith("{k}", _index) ) {
            if(_shouldBeObfuscated) _flags.add(GradientFlags.OBFUSCATED);
        } else if(_string.startsWith("{b}", _index)) {
            _flags.add(GradientFlags.BOLD);
        } else if(_string.startsWith("{i}", _index)) {
            _flags.add(GradientFlags.ITALIC);
        } else if(_string.startsWith("{u}", _index)) {
            _flags.add(GradientFlags.UNDERLINE);
        } else if(_string.startsWith("{s}", _index)) {
            _flags.add(GradientFlags.STRIKETHROUGH);
        } else if(_string.startsWith("{r}", _index)) {
            _flags.clear();
        }
        else {
            _hasFlagUpdated = false;
        }

        return _hasFlagUpdated;
    }
}