package com.a18mahkh.projekt;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VoteFragment extends Fragment {

   /* private static final String vote_imgUrl = "arg_vote_imgUrl";
    private static final String title_text = "arg_title_text";

    private String voteImgUrl;
    private String titleText;


    public static VoteFragment newInstance(String voteImgUrl, String titleText){

        VoteFragment voteFragment = new VoteFragment();
        Bundle args = new Bundle();
        args.putString(vote_imgUrl, voteImgUrl);
        args.putString(title_text, titleText);

        voteFragment.setArguments(args);
        return  voteFragment;
    }*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.vote_fragment, container, false);


        /*TextView voteText = v.findViewById(R.id.voteText);


        if (getArguments() != null){
            voteImgUrl = getArguments().getString(vote_imgUrl);
            titleText= getArguments().getString(title_text);
        }

        voteText.setText(voteImgUrl + titleText);*/

        return v;



    }

}
