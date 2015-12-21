package siva.borie.business.listmanager;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import siva.borie.R;
import siva.borie.business.Business;

/**
 * Created by Eungjun on 2015-09-16.
 */
public class RecommendListManager
{
    public final static String TAG = "RecommendListManager";

    private List<Business> mBusinessList;
    private static RecommendListManager sInstance;
    private Context mContext = null;

    private RecommendListManager()
    {
        mBusinessList = new ArrayList<Business>();

    }

    public void setContext(final Context context)
    {
        this.mContext = context;

    }

    public void initDataSet()
    {

        if(null == mContext)
        {
            Log.e(TAG, "Context is null");
            return;
        }

        Business item1 = new Business();
        item1.setName("test1");
        item1.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.ppong));
        mBusinessList.add(item1);

        Business item2 = new Business();
        item2.setName("test2");
        item2.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.budae));
        mBusinessList.add(item2);

        Business item3 = new Business();
        item3.setName("China");
        item3.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.chiyan));
        mBusinessList.add(item3);

    }

    public static RecommendListManager getInstance()
    {
        if(null ==  sInstance )
            sInstance = new RecommendListManager();

        return sInstance;
    }


    public List<Business> getBusinessList()
    {
        //Need to change this should not return list;
        return mBusinessList;
    }

}
