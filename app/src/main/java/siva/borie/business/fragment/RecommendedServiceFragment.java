package siva.borie.business.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import siva.borie.R;
import siva.borie.business.Business;
import siva.borie.business.BusinessUtils;
import siva.borie.business.activity.GoogleMapActivity;
import siva.borie.business.adapter.RecommendedListViewAdapter;
import siva.borie.business.listmanager.RecommendListManager;
import siva.borie.location.geofence.GeonfenceController;

/**
 * Created by Eungjun on 2015-02-16.
 */
public class RecommendedServiceFragment extends Fragment
{
    public static final String TAG = RecommendedServiceFragment.class.getSimpleName();
    private final String URL = "http://192.168.0.1:8080/biz/recommendlist";

    private ListView mListView;
    private GeonfenceController mGeofenceController;

    private RecyclerView mRecylerView;
    private LinearLayoutManager mLayoutManager;
    private RecommendedListViewAdapter mAdapter;
    private RecommendListManager mManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);

        mManager = RecommendListManager.getInstance();
        mManager.setContext(getActivity());
        mManager.initDataSet();

       ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Recommend");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Log.i(TAG, "onCreateView");

        View root = inflater.inflate (
                R.layout.fragment_recommended_service, container,false );


        mRecylerView = (RecyclerView)
                root.findViewById(R.id.recommended_service_recylerview);
        mRecylerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity()) ;
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecylerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecommendedListViewAdapter(mManager.getBusinessList(), getActivity());
        mRecylerView.setAdapter(mAdapter);

        //updateUI();

        return root;
    }

    private void updateUI()
    {

        Log.i(TAG, "updateUI notify");
        mRecylerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(TAG, "onResume");
        updateUI();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        Log.i(TAG, "onAttach" );

        //Geofence Test remove after test
        Context context = activity.getApplicationContext();
        mGeofenceController = new GeonfenceController(getActivity().getApplicationContext());

    }

    @Override
    public void onStart()
    {
        super.onStart();
        //mGeofenceController.stopGeofences();
        //mGeofenceController.startGoogleApiClient();
    }


    @Override
    public void onStop()
    {
        super.onStop();

        Log.i(TAG, "onStop");
        //mGeofenceController.stopGoogleApiClient();
        //mGeofenceController.stopGeofences();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_recommended_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_map:
                startGoogleMapActivity();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    private void startGoogleMapActivity()
    {
        Intent intent = new Intent(getActivity(), GoogleMapActivity.class);
        startActivity(intent);
    }


    private void setListRequest()
    {
        Log.d(TAG, "setListRequest");
        JsonArrayRequest req = new JsonArrayRequest(URL, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray jsonArray)
            {
                Log.d(TAG, "onResponse");


                Log.d(TAG, "json result: " + jsonArray.toString());

                getResponse(jsonArray);
                //mAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                VolleyLog.d(TAG,"Error" + volleyError.getMessage());
            }
        });

//        NetworkController.getInstance(getActivity().getApplicationContext())
       //.addToRequestQueue(req);

    }

    private void getResponse(JSONArray _jsonArray)
    {
        JSONArray jsonArray = _jsonArray;

        int lenght = jsonArray.length();

       for(int i = 0; i < lenght; ++i)
       {
           Business biz  = new Business();

           try
           {
               JSONObject json = (JSONObject) jsonArray.get(i);

               biz.setName(json.getString(BusinessUtils.BIZ_NAME));
               biz.setAddress(json.getString(BusinessUtils.BIZ_ADDRESS));
               biz.setEmail(json.getString(BusinessUtils.OWNER_EMAIL));

               //mBusinessArrayList.add(biz);

           } catch (JSONException e)
           {
               Log.d(TAG, e.toString());
           }
       }
    }

}
