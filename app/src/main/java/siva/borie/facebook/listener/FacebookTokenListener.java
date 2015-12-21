package siva.borie.facebook.listener;

import com.facebook.AccessToken;

/**
 * Created by Eungjun on 2015-09-20.
 */
public interface FacebookTokenListener
{
    void onFBCurrentAccessTokenChanged(final AccessToken oldToken, final AccessToken currentToken);

}
