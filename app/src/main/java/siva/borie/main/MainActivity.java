package siva.borie.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.appevents.AppEventsLogger;

import siva.borie.R;
import siva.borie.auth.LoginActivity;
import siva.borie.facebook.FacebookHelper;
import siva.borie.facebook.listener.FacebookTokenListener;
import siva.borie.navdrawer.NavDrawerListManager;
import siva.borie.navdrawer.adapter.NavDrawerAdapter;
import siva.borie.viewpager.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity implements FacebookTokenListener
{

    public static final String TAG = "MainActivity";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private RecyclerView mNavRecyclerView;
    private RecyclerView.Adapter mNavDrawerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate");


        //Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //Set Drawer toggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar,
                R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);


         //Init ViewPager
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);


        //NavDrawer RecyclerView init
        mNavRecyclerView = (RecyclerView) findViewById(R.id.drawer_main_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mNavRecyclerView.setLayoutManager(mLayoutManager);
        mNavDrawerAdapter = new NavDrawerAdapter(NavDrawerListManager.
          getInstance(getApplicationContext()), this, mViewPager, mDrawerLayout);
        mNavRecyclerView.setAdapter(mNavDrawerAdapter);

        //Facebook stuff
        FacebookHelper.getInstance().setFBtokenListener(this);
        FacebookHelper.getInstance().addReadPermissions(this);
        FacebookHelper.getInstance().graphRequest();


    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);

        Log.i(TAG, "onPostCreate");

        //Set NavigationDrawer
        //mNavDrawer = new NavigationDrawer(this, mViewPager);

        mDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);

        Log.i(TAG, "onConfigurationChanged");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Log.i(TAG,"onOptionItemSelected");

        int id = item.getItemId();

        /*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
            return true; */

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    //    getMenuInflater().inflate(R.menu.menu_recommended_fragment, menu);

        return true;
    }

    private void startLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
        finish();
    }

    @Override
    public void onFBCurrentAccessTokenChanged(AccessToken oldToken, AccessToken currentToken)
    {
        Log.i(TAG, "onCurrentAcessTokenChanged");

        if(null == currentToken)
            startLoginActivity();

    }

}
