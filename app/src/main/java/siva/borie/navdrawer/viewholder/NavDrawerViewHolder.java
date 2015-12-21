package siva.borie.navdrawer.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import siva.borie.R;
import siva.borie.facebook.FacebookUser;
import siva.borie.navdrawer.NavDrawerItem;
import siva.borie.navdrawer.NavDrawerUtil.ItemId;
import siva.borie.navdrawer.NavDrawerUtil.ItemType;
import siva.borie.navdrawer.fragment.AccountAddAlertFragment;
import siva.borie.viewpager.ViewPagerAdapter;

/**
 * Created by Eungjun on 2015-09-19.
 */
public class NavDrawerViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener
{
    public static final String TAG = "NavDrawerViewHolder";

    private TextView mTitle;
    private TextView mName;
    private TextView mEmail;
    private ImageView mPofileImg;
    private ImageView mSecondProfileImg;
    private ImageView mThirdProfileImg;
    private ImageView mArrowImg;
    private ImageView mIconImg;
    private SwitchCompat mSwitch;

    private ItemType mType;
    private ItemId mItemId;
    private Context mContext;

    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;



    private OnUserMenuClickListener mOnUserMenuClickListener;

    private boolean isUserMenu;

    public NavDrawerViewHolder(Context context, View itemView, ItemType viewType,
                               ViewPager viewPager, DrawerLayout drawerLayout)
    {
        super(itemView);

        mType = viewType;
        mContext = context;
        mViewPager = viewPager;
        mDrawerLayout = drawerLayout;

        itemView.setOnClickListener(this);

        switch (viewType)
        {

            case USER_INFO:

                mPofileImg = (ImageView) itemView.findViewById(R.id.user_info_main_circleimgView);
                mSecondProfileImg = (ImageView)
                        itemView.findViewById(R.id.user_info_second_circleimgView);
                mThirdProfileImg = (ImageView)
                        itemView.findViewById(R.id.user_info_third_circleimgView);

                mName = (TextView) itemView.findViewById(R.id.user_info_user_name_tv);
                mEmail = (TextView) itemView.findViewById(R.id.user_info_user_email_tv);
                mArrowImg = (ImageView) itemView.findViewById(R.id.user_info_arrow_img);

                break;

            case SUB_HEADER:
                mTitle = (TextView) itemView.findViewById(R.id.drawer_item_sub_header_title);

                break;

            case SWITCH:
                mIconImg = (ImageView) itemView.findViewById(R.id.drawer_swtich_icon_img);
                mTitle = (TextView) itemView.findViewById(R.id.drawer_item_switch_text);
                mSwitch = (SwitchCompat) itemView.findViewById(R.id.drawer_item_switch);

                mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        if(isChecked)
                        {
                            Log.i(TAG, "Switch on");

                            AppCompatActivity activity = (AppCompatActivity) mContext;

                            FragmentManager manager = activity.getSupportFragmentManager();
                            AccountAddAlertFragment dialog = new AccountAddAlertFragment();
                            dialog.show(manager,TAG);

                        }
                        else
                        {
                            Log.i(TAG, "Switch off");
                        }

                    }
                });
                break;

            case MENU:

                mIconImg = (ImageView) itemView.findViewById(R.id.drawer_icon_img);
                mTitle = (TextView) itemView.findViewById(R.id.drawer_item_title_tv);

                break;

            default:
                break;
        }

    }

    public void setOnUserMenuClickListener(OnUserMenuClickListener listener)
    {
        this.mOnUserMenuClickListener = listener;

    }




    private FacebookUser mFacebookUser;
    public void bind(final FacebookUser user, final NavDrawerItem item)
    {
        Log.i(TAG,"bind()");

        mFacebookUser = user;
        mItemId = item.getItemId();

        switch (mType)
        {
            case USER_INFO:

                if(null == mFacebookUser) //Default User
                {
                    Drawable pic = ContextCompat.getDrawable(mContext, R.drawable.ic_action_person);
                    mPofileImg.setImageDrawable(pic);
                    mSecondProfileImg.setImageDrawable(pic);
                    mThirdProfileImg.setImageDrawable(pic);

                    mName.setText(mContext.getString(R.string.user_name));
                    mEmail.setText(mContext.getString(R.string.user_email));

                }
                else //Facebook User
                {

                    Bitmap profile = mFacebookUser.getProfilePicure();

                    mPofileImg.setImageBitmap(profile);
                    mSecondProfileImg.setImageBitmap(profile);
                    mThirdProfileImg.setImageBitmap(profile);

                    mName.setText(mFacebookUser.getName().toUpperCase());
                    mEmail.setText(mFacebookUser.getEmail());

                }

                //Arrow direction
                if(false == isUserMenu)
                {
                    mArrowImg.setImageDrawable(ContextCompat.
                            getDrawable(mContext, R.drawable.ic_arrow_drop_down_black_24dp));
                }
                else
                {
                    mArrowImg.setImageDrawable(ContextCompat.
                            getDrawable(mContext, R.drawable.ic_arrow_drop_up_black_24dp));

                }

                break;

            case SUB_HEADER:
                 mTitle.setText(item.getTitle());

                break;

            case SWITCH:
                mTitle.setText(item.getTitle());
                mIconImg.setImageDrawable(item.getIconDrawble());

                break;

            case MENU:
                mIconImg.setImageDrawable(item.getIconDrawble());
                mTitle.setText(item.getTitle());

                break;

            default:
                break;
        }


    }

    @Override
    public void onClick(View v)
    {
        switch(mItemId)
        {
            case USER_INFO:
                viewUserMenu();
                break;

            case LBS_SERVICE:
                mViewPager.setCurrentItem(ViewPagerAdapter.RCMD_SERVICE_FRGMT);
                mDrawerLayout.closeDrawers();
                break;

            case ALL_SERVICE:
                mViewPager.setCurrentItem(ViewPagerAdapter.ALL_SERVICE_FRGMT);
                mDrawerLayout.closeDrawers();
                break;

            case VISITED_SERVICE:
                mViewPager.setCurrentItem(ViewPagerAdapter.VISITED_SERVICE_FRGMT);
                mDrawerLayout.closeDrawers();
                break;

            default:
                break;

        }

    }

    private void viewUserMenu()
    {
        if(false == isUserMenu)
        {
            mOnUserMenuClickListener.onUserMenuClick(isUserMenu);

            isUserMenu = true;
        }
        else
        {
             mOnUserMenuClickListener.onUserMenuClick(isUserMenu);

            isUserMenu = false;

        }
    }

    public interface OnUserMenuClickListener
    {
        void onUserMenuClick(boolean isUserMenu);
    }
}
