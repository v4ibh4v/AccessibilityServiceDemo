package com.vaibhav.accessibilityservicedemo.services;

import android.accessibilityservice.AccessibilityService;
import android.speech.tts.TextToSpeech;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.vaibhav.accessibilityservicedemo.utils.Log;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAccessibilityService extends AccessibilityService {
    private TextToSpeech textToSpeech;

    @Override
    public void onCreate() {
        super.onCreate();

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
        textToSpeech.setLanguage(Locale.ENGLISH);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        switch(eventType) {
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                ArrayList<AccessibilityNodeInfo> viewNodes = new ArrayList<>();
                String viewText = "";
                findChildViews(rootNode, viewNodes);
                for (AccessibilityNodeInfo mNode : viewNodes) {
                    if (mNode.getText() == null) {
                        return;
                    }

                    viewText += mNode.getText().toString() + "\t";

                }
                Log.d(viewText);
                speakOut(viewText);
                break;
        }
    }

    @Override
    public void onInterrupt() {
        if (textToSpeech!=null) textToSpeech.stop();
    }

    // You can choose to override this method. It is not required.
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    private void findChildViews(AccessibilityNodeInfo parentView, ArrayList<AccessibilityNodeInfo> viewNodes) {

        if (parentView == null || parentView.getClassName() == null) {
            return;
        }
        int childCount = parentView.getChildCount();
        if (childCount == 0 && (parentView.getClassName().toString().contentEquals("android.widget.TextView"))) {
            viewNodes.add(parentView);
        } else {
            for (int i = 0; i < childCount; i++) {
                findChildViews(parentView.getChild(i), viewNodes);
            }
        }
    }

    private void speakOut(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
