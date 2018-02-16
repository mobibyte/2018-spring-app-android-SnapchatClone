package mobi.idappthat.snapchat_clone_android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobi.idappthat.snapchat_clone_android.R;

/**
 * Created by sarthakmajithia on 16/02/18.
 */

public class StoriesFragment extends Fragment {

    public StoriesFragment() {
    }

    /*
    * Returns a new instance for this fragment
    */
    public static StoriesFragment newInstance() {
        return new StoriesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View storiesView = inflater.inflate(R.layout.fragment_stories, container, false);
        return storiesView;
    }
}
