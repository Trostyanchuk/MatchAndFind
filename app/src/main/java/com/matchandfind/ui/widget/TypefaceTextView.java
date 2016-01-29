package com.matchandfind.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.matchandfind.R;
import com.matchandfind.utils.UIUtils;

public class TypefaceTextView extends TextView {

    public TypefaceTextView(Context context) {
        super(context);
        setTypefaceFromAttrs(context, null);
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypefaceFromAttrs(context, attrs);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypefaceFromAttrs(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTypefaceFromAttrs(context, attrs);
    }

    private void setTypefaceFromAttrs(Context context, AttributeSet attrs) {
        if (this.isInEditMode()) {
            return;
        }

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TypefaceTextView);
        if (array != null) {
            String typefacePath = array.getString(R.styleable.TypefaceTextView_customTypeface);
            if (typefacePath != null) {
                setTypeface(UIUtils.getTypeface(context, typefacePath));
            }
            array.recycle();
        }
    }
}
