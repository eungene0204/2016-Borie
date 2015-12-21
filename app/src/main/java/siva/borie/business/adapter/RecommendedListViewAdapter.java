package siva.borie.business.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import siva.borie.R;
import siva.borie.business.Business;
import siva.borie.business.viewholder.RecommendListViewHolder;

/**
 * Created by Eungjun on 2015-08-18.
 */
public class RecommendedListViewAdapter extends
        RecyclerView.Adapter<RecommendListViewHolder>
{
    static public final String TAG = "RecommendAdapter";

    private final Context mContext;
    private ArrayList<Business> mList;

    public RecommendedListViewAdapter(List<Business> list, final Context context)
    {
        this.mContext = context;

        this.mList = (ArrayList<Business>) list;

        Log.i(TAG, "Adapater constructor");

    }

    @Override
    public RecommendListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent,false);

        Log.i(TAG, "onCreateViewHolder");

        return new RecommendListViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(RecommendListViewHolder holder, int position)
    {
        Business biz = mList.get(position);
        holder.bind(biz, position);

    }

    @Override
    public int getItemCount()
    {
        return mList.size();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);

    }


}
