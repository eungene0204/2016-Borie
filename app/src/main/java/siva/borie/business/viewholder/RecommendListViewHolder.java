package siva.borie.business.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import siva.borie.R;
import siva.borie.business.Business;

/**
 * Created by Eungjun on 2015-09-16.
 */

public class RecommendListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    static public String TAG = "RecommendListViewHolder";

    private final Context mContext;

    private TextView mName;
    private TextView mDesc;
    private ImageView mImageView;


    private int mPos = -1;

    public RecommendListViewHolder(View itemView,final Context context )
    {
        super(itemView);

        Log.i(TAG, "ViewHolder");
        itemView.setOnClickListener(this);

        mContext = context;

        mName = (TextView) itemView.findViewById(R.id.cardview_biz_name);
        mDesc = (TextView) itemView.findViewById(R.id.cardview_biz_desc);
        mImageView = (ImageView) itemView.findViewById(R.id.cardview_circleimg);


    }

    public void bind(Business biz, int position)
    {
        mPos = position;

        mImageView.setImageDrawable(biz.getDrawable());

        mName.setText("Name: " + biz.getName());
        mDesc.setText("Desc:");

    }

    @Override
    public void onClick(View v)
    {
        Log.i(TAG, "ViewHolder Click!!" + "pos: " + mPos);

    }
}

