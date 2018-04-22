package com.example.android.miwok;

public class Word
{
    private String mDefault_text;
    private String mMiwok_text;

    public Word(String default_text, String miwok_text) {
        mDefault_text = default_text;
        mMiwok_text = miwok_text;
    }

    public String getDefault_Text() { return mDefault_text; }
    public String getMiwok_text() { return mMiwok_text; }
}
