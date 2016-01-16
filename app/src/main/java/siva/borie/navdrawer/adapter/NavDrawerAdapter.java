package siva.borie.navdrawer.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import siva.borie.R;
import siva.borie.facebook.FacebookHelper;
import siva.borie.facebook.FacebookUser;
import siva.borie.facebook.NullFacebookUser;
import siva.borie.facebook.listener.FacebookRequestListener;
import siva.borie.navdrawer.NavDrawerItem;
import siva.borie.navdrawer.NavDrawerListManager;
import siva.borie.navdrawer.NavDrawerUtil;
import siva.borie.navdrawer.viewholder.NavDrawerViewHolder;

/**
 * Created by Eungjun on 2015-09-19.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerViewHolder>
    implements FacebookRequestListener, NavDrawerViewHolder.OnUserMenuClickListener
{
    public static final String TAG = "NavDrawerAdapter";

    private NavDrawerListManager mDrawerListManager;
    private Context mContext;

    private FacebookUser mFacebookUser;

    private final ViewPager mViewPager;
    private final DrawerLayout mDrawerLayout;

    private boolean mIsUserMenu;

    public NavDrawerAdapter(NavDrawerListManager drawerListManager, Context context,
                            ViewPager viewPager, DrawerLayout drawerLayout)
    {
        Log.i(TAG,"NavDraerAdapater cont");

        mContext = context;

        mDrawerListManager = drawerListManager;

        mViewPager = viewPager;
        mDrawerLayout = drawerLayout;

        FacebookHelper.getInstance().setFBRequestListener(this);

    }

    @Override
    public NavDrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.i(TAG, "onCreateViewHolder");


        View v = null;
        NavDrawerViewHolder viewHolder = null;

        if (NavDrawerUtil.ItemType.USER_INFO.ordinal() == viewType)
        {
            v = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.drawer_item_user_info, parent, false);

            viewHolder = new NavDrawerViewHolder(mContext, v, NavDrawerUtil.ItemType.USER_INFO,
                    mViewPager, mDrawerLayout);
        }
        else if (NavDrawerUtil.ItemType.SUB_HEADER.ordinal() == viewType)
        {

            v = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.drawer_item_subheader, parent, false);

            viewHolder = new NavDrawerViewHolder(mContext, v, NavDrawerUtil.ItemType.SUB_HEADER,
                    mViewPager, mDrawerLayout);

        }
        else if (NavDrawerUtil.ItemType.SWITCH.ordinal() == viewType)
        {

            Log.i(TAG, "SWITCH return viewholder");

              v = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.drawer_item_switch, parent, false);

            viewHolder = new NavDrawerViewHolder(mContext, v, NavDrawerUtil.ItemType.SWITCH,
                    mViewPager, mDrawerLayout);

        }
        else if(NavDrawerUtil.ItemType.MENU.ordinal() == viewType)
        {
            Log.i(TAG, "MENU return viewholder");
            v = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.drawer_item_menu, parent, false);

            viewHolder = new NavDrawerViewHolder(mContext, v, NavDrawerUtil.ItemType.MENU,
                    mViewPager, mDrawerLayout);

        }

        viewHolder.setOnUserMenuClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NavDrawerViewHolder holder, int position)
    {
        Log.i(TAG, "onBindViewHolder");

        NavDrawerItem item = mDrawerListManager.getItem(position);
        holder.bind( mFacebookUser, item);

    }


    @Override
    public int getItemCount()
    {
        return mDrawerListManager.ItemSize();

    }

    @Override
    public int getItemViewType(int position)
    {
        NavDrawerItem item = NavDrawerListManager.getInstance(mContext).getItem(position);

        return item.getItemType().ordinal();
    }


    @Override
    public void onFacebookRequestCompleted(FacebookUser facebookUser)
    {
        Log.i(TAG, "onFacebookRequestCompleted");

        if( null == facebookUser )
            mFacebookUser = new NullFacebookUser();
        else
            mFacebookUser = facebookUser;

        notifyDataSetChanged();

    }

    @Override
    public void onUserMenuClick(boolean isUserMenu)
    {
        mIsUserMenu = isUserMenu;

        if(false == mIsUserMenu)
            NavDrawerListManager.getInstance(mContext).UserMenuDatainit();
        else
            NavDrawerListManager.getInstance(mContext).initData();

        notifyDataSetChanged();

    }
}
