package com.example.qenawi.movieappnanophase1.jsonparsers;

import android.os.AsyncTask;

import com.example.qenawi.movieappnanophase1.interfaces.jsonparserlistner;
import com.example.qenawi.movieappnanophase1.items.IncomeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by QEnawi on 4/14/2016.
 */
public class MainGridParser extends AsyncTask<Object, Void, ArrayList<?>> {
    // Give me Json String
    int tag;
    private jsonparserlistner Call_back;
    public MainGridParser(jsonparserlistner cb)
    {
        this.Call_back=cb;
    }
    // connect liStnerS
//-------------------------------------------------------------------
    // iwill parse it using aSync task
    @Override
    protected ArrayList<?> doInBackground(Object... params)
    {
        ArrayList<?> ret = new ArrayList<>();// data holder
        tag=(int)params[1];
        try {
            ret = parse((String)params[0]);
            return ret;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //-------------------start parsing--------------
    ArrayList<?> parse(String Jsonx)
            throws JSONException {
            IncomeData data;// item to hold data only
if (Jsonx==null)return  null;
        ArrayList<IncomeData> res = new ArrayList<IncomeData>();
        String poster_path, overview, release_date, title, backdrop_path,id;
        String popularity, vote_count, vote_average;
        JSONObject all = new JSONObject(Jsonx);
        JSONArray Result = all.getJSONArray("results");
        for (int pos = 0; pos < Result.length(); pos++)
        {
            data = new IncomeData();
            JSONObject tmp = Result.getJSONObject(pos);
            title = tmp.getString("title");
            poster_path = tmp.getString("poster_path");
            backdrop_path = tmp.getString("backdrop_path");
            release_date = tmp.getString("release_date");
            id=tmp.getString("id");
            vote_count = tmp.getString("vote_count");
            popularity = tmp.getString("popularity");
            overview = tmp.getString("overview");
            vote_average = tmp.getString("vote_average");
            data.setBackdrop_path(backdrop_path);
            data.setID(id);
            data.setOverview(overview);
            data.setPopularity(popularity);
            data.setTitle(title);
            data.setPoster_path(poster_path);
            data.setRelease_date(release_date);
            data.setVote_count(vote_count);
            data.setVote_average(vote_average);
            //---------------------

            //----------------------
            res.add(data);
        }
        return res;

    }
   //---------------------------------------------

    // when i finish iwiil return to  U

    @Override
    protected void onPostExecute(ArrayList<?> objects) {
        super.onPostExecute(objects);
        try {
            Call_back.onPArsingComplte(objects,tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
