package com.example.weatherapp.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class FocusUtils {

    public static void requestFocusWithSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
