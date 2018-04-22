package com.example.android.miwok;

import android.media.Image;
import android.widget.ImageView;

public class Word
{
    private int mImageID;
    private String mDefault_text;
    private String mMiwok_text;

    public Word(String default_text, String miwok_text) {
        mDefault_text = default_text;
        mMiwok_text = miwok_text;
    }

    public Word(int imageID, String default_text, String miwok_text)
    {
        mImageID = imageID;
        mDefault_text = default_text;
        mMiwok_text = miwok_text;
    }

    public int getImageID() { return mImageID; }
    public String getDefault_Text() { return mDefault_text; }
    public String getMiwok_text() { return mMiwok_text; }
}
