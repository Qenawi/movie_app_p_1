package com.example.qenawi.movieappnanno_phase1;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.qenawi.movieappnanno_phase1.Fragments.Detail_activity_fragment;
import com.example.qenawi.movieappnanno_phase1.Fragments.main_Grid_fragment;
import com.example.qenawi.movieappnanno_phase1.items.income_data;

public class MainActivity extends AppCompatActivity implements main_Grid_fragment.OnFragmentInteractionListener,Detail_activity_fragment.OnFragmentInteractionListener
{
 Boolean Fragment1,Fragment2,Fragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState)
        {
            //
            Fragment1 = Fragment2 = Fragment3 = false;
            //
            Call_MAIN();
            //
        }

    }
    @Override
    public void onFragmentInteraction(income_data uri)
    {
     Call_DEtail_FRAG(uri);
    }//main Fragment
    void Call_DEtail_FRAG(income_data packge)
    {
        Fragment2=true;
        Fragment1=false;
        Fragment3=false;
        //
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.mainFragment_To_detail_fragment_income_data), packge);
        //
        Detail_activity_fragment fragment = new Detail_activity_fragment();
        fragment.setArguments(bundle);//set data
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.contentFragment, fragment, "detail_frag"); //Container -> R.id.contentFragment
        transaction.commit();
    }
      void Call_MAIN()
       {
           Fragment2=false;
           Fragment1=true;
           Fragment3=false;
           main_Grid_fragment fragment = new main_Grid_fragment();
           FragmentManager fm = getSupportFragmentManager();
           FragmentTransaction transaction = fm.beginTransaction();
           transaction.replace(R.id.contentFragment, fragment, "main_frag"); //Container -> R.id.contentFragment
           transaction.commit();
       }

    @Override
    public void onDetailFragmentInteraction(Uri uri)
    {

    }//Detail frag
//----------------------------------------
    @Override
    public void onBackPressed()
    {
        if (Fragment2==null&&Fragment1==null){Call_MAIN();}
        else if (Fragment2!=null&&Fragment2){Call_MAIN();}
        else
        super.onBackPressed();
    }

    private int _getScreenOrientation()
    {
        if( getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT)return 0;
        else  return 1;
    }
}
