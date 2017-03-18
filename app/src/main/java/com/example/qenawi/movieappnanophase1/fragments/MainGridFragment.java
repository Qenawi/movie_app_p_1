package com.example.qenawi.movieappnanophase1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qenawi.movieappnanophase1.R;
import com.example.qenawi.movieappnanophase1.adapters.RecyclerViewAdapterMainActivity;
import com.example.qenawi.movieappnanophase1.asynctask.GetJsonFromUrl;
import com.example.qenawi.movieappnanophase1.interfaces.asynctasklistner1;
import com.example.qenawi.movieappnanophase1.interfaces.jsonparserlistner;
import com.example.qenawi.movieappnanophase1.items.IncomeData;
import com.example.qenawi.movieappnanophase1.jsonparsers.MainGridParser;

import java.util.ArrayList;

public class MainGridFragment extends Fragment implements RecyclerViewAdapterMainActivity.onClickListner, asynctasklistner1, jsonparserlistner {
    //objects--
    RecyclerView mainGrid;
    RecyclerViewAdapterMainActivity adapter;
    Toast F;
    GetJsonFromUrl getJsonFromUrl;
    MainGridParser mainGridParser;
    ArrayList<IncomeData> allData;
    RecyclerView.LayoutManager layoutManager;


    private OnFragmentInteractionListener mListener;

    public MainGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {

        }
    }

    int screen()
    {
        int res=0;
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        if (width < height) {
            res=1;
        } else {
            res= 2;
        }
        return res;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root;
        root = inflater.inflate(R.layout.fragment_main__grid_fragment, container, false);
        mainGrid = (RecyclerView) root.findViewById(R.id.item_list);
        allData = new ArrayList<>();

        if (screen() == 2) {
            layoutManager = new GridLayoutManager(getContext(), 4);
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        mainGrid.setHasFixedSize(true);
        mainGrid.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterMainActivity(getContext(), this, allData, 0);
        mainGrid.setAdapter(adapter);
        load_Data("popular");
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(allData.get(uri));
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //-menue
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.popular:
                load_Data(getString(R.string.popular_TAG));
                return true;
            case R.id.top_rated:
                load_Data(getString(R.string.top_rated_TAG));
                return true;

            default:
        }

        return super.onOptionsItemSelected(item);
    }

    void load_Data(String search_type) {
        getActivity().setTitle(search_type);
        getJsonFromUrl = new GetJsonFromUrl(this);
        getJsonFromUrl.execute(getString(R.string.BASE_URL) + search_type + "?" + getString(R.string.API_KEy), screen());
    }

    //
    @Override
    public void onListItemClick(int Clickpos) {
        onButtonPressed(Clickpos);
    }

    @Override
    public void onTaskComplete(Object result, int tag) {
        mainGridParser = new MainGridParser(this);
        mainGridParser.execute((String) result, 0);
    }

    @Override
    public void onPArsingComplte(ArrayList<?> result, int Tag) {
        allData = (ArrayList<IncomeData>) result;
        adapter = new RecyclerViewAdapterMainActivity(getContext(), this, allData, screen());
        mainGrid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(IncomeData uri);
    }
}
