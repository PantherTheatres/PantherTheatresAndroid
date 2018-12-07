package team383.edu.panthertheatres;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class FeedbackFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onClick (View view){

    }
}