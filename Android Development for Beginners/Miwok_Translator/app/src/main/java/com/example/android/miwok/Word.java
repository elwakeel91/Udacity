package com.example.android.miwok;

public class Word
{
    private int mImageID;                       // The image resource ID number of the word
    private static final int NO_IMAGE = -1;     // Placeholder image resource ID number
    private String mDefault_text;               // The native translation of the word
    private String mMiwok_text;                 // The Miwok translation of the word

    /**
     * Creates a new {@link Word} object
     * @param default_text is the native translation
     * @param miwok_text is the Miwok translation
     */
    public Word(String default_text, String miwok_text) {
        // Set the image resource ID to NO_IMAGE
        mImageID = NO_IMAGE;

        // Initialise the native translation and capitalize the first letter
        mDefault_text = default_text;
        mDefault_text = Character.toUpperCase(mDefault_text.charAt(0)) +
                mDefault_text.substring(1, mDefault_text.length());

        // Initialise the Miwok translation and capitalize the first letter
        mMiwok_text = miwok_text;
        mMiwok_text = Character.toUpperCase(mMiwok_text.charAt(0)) +
                mMiwok_text.substring(1, mMiwok_text.length());
    }

    /**
     * Creates a new {@link Word} object with an attached image
     * @param imageID is the image resource ID number of the word
     * @param default_text is the native translation of the word
     * @param miwok_text is the Miwok translation of the word
     */
    public Word(int imageID, String default_text, String miwok_text)
    {
        // Initialise the image resource ID number
        mImageID = imageID;

        // Initialise the native translation and capitalize the first letter
        mDefault_text = default_text;
        mDefault_text = Character.toUpperCase(mDefault_text.charAt(0)) +
                        mDefault_text.substring(1, mDefault_text.length());

        // Initialise the Miwok translation and capitalize the first letter
        mMiwok_text = miwok_text;
        mMiwok_text = Character.toUpperCase(mMiwok_text.charAt(0)) +
                      mMiwok_text.substring(1, mMiwok_text.length());
    }

    // Get the image resource ID number
    public int getImageID() { return mImageID; }

    // Check if an image resource ID number was provided
    public boolean hasImageID() { return mImageID != NO_IMAGE; }

    // Get the native translation text
    public String getDefault_Text() { return mDefault_text; }

    // Get the Miwok translation text
    public String getMiwok_text() { return mMiwok_text; }
}
