package com.example.qenawi.movieappnanophase1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qenawi.movieappnanophase1.R;
import com.example.qenawi.movieappnanophase1.items.IncomeData;


public class DetailActivityFragment extends Fragment {
//objects

    private OnFragmentInteractionListener mListener;
     IncomeData movieData;
     TextView mRelasedate,mRevew,mVoteavg,mFullname;
     ImageView mImageview;




    public DetailActivityFragment()
    {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
     movieData =new IncomeData();
      movieData =(IncomeData)getArguments().getSerializable(getString(R.string.mainFragment_To_detail_fragment_income_data));
            getActivity().setTitle("MovieDetails");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View Root =inflater.inflate(R.layout.fragment_detail_activity_fragment, container, false);
        mImageview=(ImageView)Root.findViewById(R.id.imageView);
        mRelasedate=(TextView) Root.findViewById(R.id.release_date);
        mRevew=(TextView) Root.findViewById(R.id.movieReview_text);
        mVoteavg=(TextView) Root.findViewById(R.id.vote_average);
        mFullname=(TextView) Root.findViewById(R.id.FullMoviename);
        //====
       Glide.with(getActivity()).load(getString(R.string.BASE_image_URL)+ movieData.getBackdrop_path()).centerCrop().into(mImageview);
        mRelasedate.setText(movieData.getRelease_date());
        mRevew.setText(movieData.getOverview());
        mVoteavg.setText(movieData.getVote_average());
        mFullname.setText(movieData.getTitle());
        /*/*/
        return  Root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onDetailFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onDetailFragmentInteraction(Uri uri);
    }
}
