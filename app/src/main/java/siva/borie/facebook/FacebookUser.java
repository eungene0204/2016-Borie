package siva.borie.facebook;

import android.graphics.Bitmap;

/**
 * Created by Eungjun on 2015-07-21.
 */
public class FacebookUser
{
    private String mId;
    private String mName;
    private String mEmail;
    private Bitmap mProfilePicture;

    public FacebookUser()
    {
    }

    public String getId()
    {
        return mId;
    }

    public void setId(String id)
    {
        mId = id;
    }

    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    public String getEmail()
    {
        return mEmail;
    }

    public void setEmail(String email)
    {
        mEmail = email;
    }

    public Bitmap getProfilePicture()
    {
        return mProfilePicture;
    }

    public FacebookUser(String mId, String mName, String mEmail, Bitmap mProfilePicture)
    {
        this.mId = mId;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mProfilePicture = mProfilePicture;
    }

    public void setProfilePicture(final Bitmap picture)
    {
        this.mProfilePicture = picture;

    }

    public Bitmap getProfilePicure()
    {
        //ProfilePicture can be null
        //Need to deal with it
        return this.mProfilePicture;
    }
}
