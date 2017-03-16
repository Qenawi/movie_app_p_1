package com.example.qenawi.movieappnanno_phase1.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qenawi.movieappnanno_phase1.R;
import com.example.qenawi.movieappnanno_phase1.adapters.RECYCLER_VIEW_ADAPTER_MAIN_ACTIVITY;
import com.example.qenawi.movieappnanno_phase1.async_task.GET_JSON_FROM_URL;
import com.example.qenawi.movieappnanno_phase1.interfaces.ASync_TASK_LisTner1;
import com.example.qenawi.movieappnanno_phase1.interfaces.JSon_parser_listner;
import com.example.qenawi.movieappnanno_phase1.items.income_data;
import com.example.qenawi.movieappnanno_phase1.jsonParSers.Main_grid_parser;

import java.util.ArrayList;

public class main_Grid_fragment extends Fragment implements RECYCLER_VIEW_ADAPTER_MAIN_ACTIVITY.onClickListner,ASync_TASK_LisTner1,JSon_parser_listner
{
    //objects--
    RecyclerView main_grid;
    RECYCLER_VIEW_ADAPTER_MAIN_ACTIVITY adapter;
    Toast F;
    GET_JSON_FROM_URL A;
    Main_grid_parser B;
    ArrayList<income_data> All_data;
    RecyclerView.LayoutManager layoutManager;


    private OnFragmentInteractionListener mListener;
    public main_Grid_fragment()
    {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null)
        {

        }
    }

int screen()
{
    int res;
    Display display = getActivity().getWindowManager().getDefaultDisplay();
    int width = display.getWidth();
    int height = display.getHeight();
    if (width < height)
    {
return 1;
    }
    else
    {
return 2;
    }
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Log.v("hollow","oncreate");
        // Inflate the layout for this fragment
        View root;
        root= inflater.inflate(R.layout.fragment_main__grid_fragment, container, false);
        main_grid=(RecyclerView)root.findViewById(R.id.item_list);
        All_data =new ArrayList<>();
        if(screen()==2)
        {
            layoutManager=new GridLayoutManager(getContext(),4);
        }
        else
        {
             layoutManager=new GridLayoutManager(getContext(),2);
        }



        main_grid.setHasFixedSize(true);
        main_grid.setLayoutManager(layoutManager);
        adapter=new RECYCLER_VIEW_ADAPTER_MAIN_ACTIVITY(getContext(),this,All_data,0);
        main_grid.setAdapter(adapter);
        load_Data("popular");
        return  root;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int uri)
    {
        if (mListener != null) {
            mListener.onFragmentInteraction(All_data.get(uri));
        }
    }
    @Override
    public void onAttach(Context context)
    {
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
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
{
    inflater.inflate(R.menu.main_menu,menu);
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
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


//-menue
    //
void load_Data(String search_type)
{
    getActivity().setTitle(search_type);
    A = new GET_JSON_FROM_URL(this);
    A.execute(getString(R.string.BASE_URL) + search_type + "?" + getString(R.string.API_KEy), screen());
}

    //
    @Override
    public void onListItemClick(int Clickpos)
    {
        onButtonPressed(Clickpos);
    }
    @Override
    public void onTaskComplete(Object result, int tag)
    {
        B=new Main_grid_parser(this);
        B.execute((String)result,0);
    }

    @Override
    public void onPArsingComplte(ArrayList<?> result, int Tag)
    {
     All_data=(ArrayList<income_data>) result;
      adapter =new RECYCLER_VIEW_ADAPTER_MAIN_ACTIVITY(getContext(),this,All_data,screen());
        main_grid.setAdapter(adapter);
      adapter.notifyDataSetChanged();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(income_data uri);
    }
}
