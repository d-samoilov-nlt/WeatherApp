package com.example.weatherapp.common.view;

import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;

public class NoConnectionSnackBarDialog extends BaseTransientBottomBar<NoConnectionSnackBarDialog> implements INoConnectionDialog {

    public NoConnectionSnackBarDialog(ViewGroup parent, View content, ContentViewCallback callback) {
        super(parent, content, callback);
    }

    @Override
    public void hide() {
        dismiss();
    }

    public static class ContentViewCallback implements BaseTransientBottomBar.ContentViewCallback {
        private View content;

        public ContentViewCallback(View content) {
            this.content = content;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            content.setScaleY(1f);
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            content.setScaleY(1f);
        }
    }
}
