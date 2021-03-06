package com.matchandfind.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

public final class UIUtils {

    private static HashMap<String, Typeface> typefaces = new HashMap<>();

    /**
     * Method for getting typeface from font file
     *
     * @param typefaceName Path to specific font file in assets
     * @return specific typeface
     */
    public static Typeface getTypeface(Context context, String typefaceName) {
        Typeface requiredTypeface = typefaces.get(typefaceName);
        if (requiredTypeface == null) {
            requiredTypeface = Typeface.createFromAsset(context.getAssets(), typefaceName);
            typefaces.put(typefaceName, requiredTypeface);
        }
        return requiredTypeface;
    }
}
