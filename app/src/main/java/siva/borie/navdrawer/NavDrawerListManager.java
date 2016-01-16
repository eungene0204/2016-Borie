package siva.borie.navdrawer;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import siva.borie.R;
import siva.borie.navdrawer.NavDrawerUtil.ItemId;
import siva.borie.navdrawer.NavDrawerUtil.ItemType;

/**
 * Created by Eungjun on 2015-09-19.
 */
public class NavDrawerListManager
{

    private static NavDrawerListManager sInstance;

    private List<NavDrawerItem> mItemList;

    private final Context mContext;


    private NavDrawerListManager(final Context context)
    {
        mContext = context;
        initData();
    }


    public static NavDrawerListManager getInstance(Context context)
    {
        if(null == sInstance)
            sInstance = new NavDrawerListManager(context);

        return sInstance;
    }


    public void initData()
    {
         mItemList = new ArrayList<NavDrawerItem>();

        //Add User info item
        NavDrawerItem userInfo = new NavDrawerItem(ItemType.USER_INFO, ItemId.USER_INFO);
        userInfo.setTitle("USER");
        mItemList.add(userInfo);

        //Add Sub Header
        NavDrawerItem sub_header= new NavDrawerItem(ItemType.SUB_HEADER, ItemId.SUB_HEADER);
        sub_header.setTitle("Services");
        mItemList.add(sub_header);

        //Add location based recommended service
        NavDrawerItem lbsServicve = new NavDrawerItem(ItemType.MENU, ItemId.LBS_SERVICE);
        lbsServicve.setTitle("LBS");
        lbsServicve.setIconDrawble(ContextCompat.getDrawable(
                mContext, R.drawable.ic_room_black_24dp));
        mItemList.add(lbsServicve) ;

        //Add All Service
        NavDrawerItem allService = new NavDrawerItem(ItemType.MENU, ItemId.ALL_SERVICE);
        allService.setTitle("All Service");
        allService.setIconDrawble(ContextCompat.getDrawable(
               mContext, R.drawable.ic_home_black_24dp ));
        mItemList.add(allService);

        //Add Visited Service
        NavDrawerItem visitedService = new NavDrawerItem(ItemType.MENU, ItemId.VISITED_SERVICE);
        visitedService.setTitle("Visited Service");
        visitedService.setIconDrawble(ContextCompat.getDrawable(
                mContext, R.drawable.ic_explore_black_24dp));
        mItemList.add(visitedService);

        //Add Tool Title
        NavDrawerItem toolTitle= new NavDrawerItem(ItemType.SUB_HEADER, ItemId.SUB_HEADER);
        toolTitle.setTitle("Tools");
        mItemList.add(toolTitle);

        //Add Setting
        NavDrawerItem setting = new NavDrawerItem(ItemType.MENU, ItemId.SETTING);
        setting.setTitle("Setting");
        setting.setIconDrawble(ContextCompat.getDrawable(
                mContext, R.drawable.ic_settings_black_24dp));
        mItemList.add(setting);

        //Add FeedBack
        NavDrawerItem feedBack = new NavDrawerItem(ItemType.MENU, ItemId.FEED_BACK);
        feedBack.setIconDrawble(ContextCompat.getDrawable(
                mContext, R.drawable.ic_feedback_black_24dp));
        feedBack.setTitle("Feedback");
        mItemList.add(feedBack);

        //Add Share
        NavDrawerItem share = new NavDrawerItem(ItemType.MENU, ItemId.SHARE);
        share.setTitle("Share");
        share.setIconDrawble(ContextCompat.getDrawable(mContext, R.drawable.ic_share_black_24dp));
        mItemList.add(share);

    }

    public void UserMenuDatainit()
    {
        mItemList = new ArrayList<NavDrawerItem>();

        //Add User info item
        NavDrawerItem userInfo = new NavDrawerItem(ItemType.USER_INFO,ItemId.USER_INFO);
        userInfo.setTitle("USER");
        mItemList.add(userInfo);

        //Add Account
        NavDrawerItem account_sub = new NavDrawerItem(ItemType.SUB_HEADER, ItemId.SUB_HEADER);
        account_sub.setTitle("Add Account");
        //account.setIconDrawble(ContextCompat.getDrawable( mContext, R.drawable.ic_add_black_24dp));
        mItemList.add(account_sub) ;

        //Add Twitter Account
        NavDrawerItem twitter_acct = new NavDrawerItem(ItemType.SWITCH, ItemId.TWITTER_ACCOUNT);
        twitter_acct.setTitle("Twitter");
         twitter_acct.setIconDrawble(ContextCompat.getDrawable( mContext, R.drawable.twitterlogo));
        mItemList.add(twitter_acct);


        //Add Instagram Acctount
        NavDrawerItem instaAcct = new NavDrawerItem(ItemType.SWITCH, ItemId.INSTAGRAM_ACCOUNT);
        instaAcct.setTitle("Instagram");
        instaAcct.setIconDrawble(ContextCompat.getDrawable(
                mContext,R.drawable.instagram_icon_large));
        mItemList.add(instaAcct);


        /*
        //Add account setting
        NavDrawerItem acctSetting= new NavDrawerItem(ItemType.MENU, ItemId.ACCOUNT_SETTING);
        acctSetting.setTitle("Account Setting");
        acctSetting.setIconDrawble(ContextCompat.getDrawable(
                mContext, R.drawable.ic_settings_black_24dp));
        mItemList.add(acctSetting); */

    }

    public int ItemSize()
    {
       return mItemList.size();

    }

    public NavDrawerItem getItem(int position)
    {
        return mItemList.get(position);

    }
}
