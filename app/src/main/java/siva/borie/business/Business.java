package siva.borie.business;

import android.graphics.drawable.Drawable;

/**
 * Created by Eungjun on 2015-03-10.
 */
public class Business
{
    private String mName;
    private String mAddress;
    private String mCategory;
    private String mEmail;

    private Drawable mDrawable;

    public void setDrawable(Drawable drawable)
    {
        this.mDrawable = drawable;

    }

    public Drawable getDrawable()
    {
        return mDrawable;

    }

    public String getCategory()
    {
        return mCategory;
    }

    public void setCategory(String category)
    {
        mCategory = category;
    }

    public String getName()
    {
        return mName;

    }

    public void setName(String name)
    {
        mName = name;
    }

    public String getAddress()
    {
        return mAddress;
    }

    public void setAddress(String address)
    {
        mAddress = address;
    }

    public String getEmail()
    {
        return mEmail;
    }

    public void setEmail(String email)
    {
        mEmail = email;
    }

}
