package com.example.qenawi.movieappnanno_phase1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qenawi.movieappnanno_phase1.R;
import com.example.qenawi.movieappnanno_phase1.items.income_data;

import java.util.ArrayList;

/**
 * Created by QEnawi on 3/11/2017.
 */

public class RECYCLER_VIEW_ADAPTER_MAIN_ACTIVITY extends RecyclerView.Adapter<RECYCLER_VIEW_ADAPTER_MAIN_ACTIVITY.Main_VIew_HOlder> {
    //objects needed from activity
    Context context;
    int rotate;
    onClickListner mOnClickListener;
    ArrayList<income_data>img_links;
   //const
    public  RECYCLER_VIEW_ADAPTER_MAIN_ACTIVITY(Context C,onClickListner L,ArrayList<income_data>D,int Rotate)
    {
        context=C;
        mOnClickListener=L;
        img_links=D;
        rotate=Rotate;
    }
    //basic Fn
    @Override
    public Main_VIew_HOlder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context parent_C=parent.getContext();
       int Layoutidforitem=R.layout.list_item;
        LayoutInflater inflater=LayoutInflater.from(parent_C);
        boolean shouldAttachToParentImmediately = false;
        View view=inflater.inflate(Layoutidforitem,parent,shouldAttachToParentImmediately);
       Main_VIew_HOlder hOlder=new Main_VIew_HOlder(view);
        return  hOlder;
    }

    @Override
    public void onBindViewHolder(Main_VIew_HOlder holder, int position)
    {
        Log.v("adaPTERXXX","on binnd vh- adp");

       holder.bind(img_links.get(position).getPoster_path(),rotate);
    }

    @Override
    public int getItemCount()
    {
        return img_links.size();
    }

    @Override
    public long getItemId(int position)
    {
        return  position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    //view Holder------------------------------------------------------------------------
    class Main_VIew_HOlder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //objects in item
        ImageView movie_img;

        //constructor
        public Main_VIew_HOlder(View itemView)
        {
            super(itemView);
            movie_img = (ImageView)itemView.findViewById(R.id.list_item_img);
            itemView.setOnClickListener(this);
        }

        void bind(String img_src,int rotate)
        {
//--------
         final String base=context.getString(R.string.BASE_image_URL)+img_src;
            if(rotate==1)
            {
                Glide.with(context).load(base).centerCrop().into(movie_img);
            }
            else
            {
                Glide.with(context).load(base).fitCenter().into(movie_img);
            }
        }

        @Override
        public void onClick(View view) {
            int Clickpos = getAdapterPosition();
            mOnClickListener.onListItemClick(Clickpos);
        }
    }

    //listner inter face
    public interface onClickListner {
        void onListItemClick(int Clickpos);
    }
}
