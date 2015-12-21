package siva.borie.facebook.listener;

import siva.borie.facebook.FacebookUser;

/**
 * Created by Eungjun on 2015-09-20.
 */
public interface FacebookRequestListener
{
    void onFacebookRequestCompleted(final FacebookUser user);
}
