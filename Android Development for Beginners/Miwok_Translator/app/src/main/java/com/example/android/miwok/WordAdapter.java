package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Word} objects.
 * */
public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColorID;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context           The current context. Used to inflate the layout file.
     * @param words             A List of {@link Word} objects to display in a list
     */
    public WordAdapter(@NonNull Activity context, ArrayList<Word> words, int backgroundColorID) {
    /*  Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        the second argument is used when the ArrayAdapter is populating a single TextView.
        Because this is a custom adapter for two TextViews, the adapter is not
        going to use this second argument, so it can be any value. Here, we used 0.*/
        super(context, 0, words);

        // Initialise the background color resource ID number
        mBackgroundColorID = backgroundColorID;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position          The position in the list of data that should be displayed in the
     *                          list item view.
     * @param convertView       The recycled view to populate.
     * @param parent            The parent ViewGroup that is used for inflation.
     * @return                  The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listViewItem = convertView;
        if(listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Set the background color of the text linear layout view
        LinearLayout textLinearLayout =
                (LinearLayout) listViewItem.findViewById(R.id.text_linear_layout);
        textLinearLayout.setBackgroundColor(mBackgroundColorID);

        // Find the ImageView in the list_item.xml layout containing our image
        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.image);
        // Check if there is an image attachment for the current word
        if (currentWord.hasImageID()) {
            // Get the imageID from the current Word object and set this onto ImageView
            imageView.setImageResource(currentWord.getImageID());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        }
        else
            // Remove the ImageView
            imageView.setVisibility(View.GONE);

        // Find the TextView in the list_item.xml layout containing our default_text
        TextView defaultTextView = (TextView) listViewItem.findViewById(R.id.default_text_view);
        // Get the default_text from the current Word object and set this onto TextView
        defaultTextView.setText(currentWord.getDefault_Text());

        // Find the TextView in the list_item.xml layout containing our miwok_text
        TextView miwokTextView = (TextView) listViewItem.findViewById(R.id.miwok_text_view);
        // Get the miwok_text from the current Word object and set this onto TextView
        miwokTextView.setText(currentWord.getMiwok_text());

        // Return the list item layout (containing 2 text views) to the ListView
        return listViewItem;
    }
}
