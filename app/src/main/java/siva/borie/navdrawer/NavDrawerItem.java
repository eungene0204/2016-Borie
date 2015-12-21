package siva.borie.navdrawer;

import android.graphics.drawable.Drawable;

import siva.borie.navdrawer.NavDrawerUtil.ItemId;
import siva.borie.navdrawer.NavDrawerUtil.ItemType;

/**
 * Created by Eungjun on 2015-09-20.
 */
public class NavDrawerItem
{

    private String mTitle;
    private ItemType mType;
    private ItemId mId;
    private Drawable mIconDrawble;

    public Drawable getIconDrawble()
    {
        return mIconDrawble;
    }

    public void setIconDrawble(Drawable iconDrawble)
    {
        mIconDrawble = iconDrawble;
    }

    public NavDrawerItem(final ItemType mType, final ItemId id)
    {
        this.mType = mType;
        this.mId = id;
    }

    public NavDrawerItem(final ItemId id)
    {
        this.mId = id;
    }

    public void setTitle(final String title)
    {
        mTitle = title;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setItemType(final ItemType type)
    {
        mType = type;
    }

    public ItemType getItemType()
    {
        return mType;

    }

    public void setItemId(ItemId id)
    {
        this.mId = id;
    }

    public ItemId getItemId()
    {
        return this.mId;
    }
}
