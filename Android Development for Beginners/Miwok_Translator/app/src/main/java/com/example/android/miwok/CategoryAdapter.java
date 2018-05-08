package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Custom {@link CategoryAdapter} class that handles the category fragments
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /**
     * Constructor
     */
    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Gets the category fragment from the adapter
     * @param position is the category index
     * @return the category fragment
     */
    @Override
    public Fragment getItem(int position) {

        // Set the Numbers category to position 0
        if (position == 0) { return new NumbersFragment(); }

        // Set the Family category to position 1
        else if (position == 1) { return new FamilyFragment(); }

        // Set the Colors category to position 2
        else if (position == 2) { return new ColorsFragment(); }

        // Set the Phrases category to position 3
        else { return new PhrasesFragment(); }
    }

    /**
     * The number of fragments in the adapter
     * @return
     */
    @Override
    public int getCount() {

        // We want 4 fragments in our adapter
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Numbers";
        } else if (position == 1) {
            return "Family";
        } else if (position == 2) {
            return "Colors";
        } else {
            return "Phrases";
        }
    }
}
