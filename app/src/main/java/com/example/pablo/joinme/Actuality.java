package com.example.pablo.joinme;

import android.app.LauncherActivity;
import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.support.v4.app.FragmentManager ;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.id.list;

/*
This fragment will display the actuality so we use a list fragment
*/

public class Actuality extends Fragment {

    private static final String URL = "https://joinme.000webhostapp.com/JoinMe-3.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    SimpleAdapter simpleAdapter;
    private String[] idevents, places, topics, levels, times, comments;
    ViewGroup viewGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_actuality, container, false);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        Afficher();
        setRetainInstance(true);

        return viewGroup;
    }

    public void Afficher() {
        request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    idevents = new String[jsonArray.length()];
                    places = new String[jsonArray.length()];
                    topics = new String[jsonArray.length()];
                    levels = new String[jsonArray.length()];
                    times = new String[jsonArray.length()];
                    comments = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        idevents[i] = jsonObject.getString("idevent");
                        places[i] = jsonObject.getString("place");
                        topics[i] = jsonObject.getString("topic");
                        levels[i] = jsonObject.getString("level");
                        times[i] = jsonObject.getString("time");
                        comments[i] = jsonObject.getString("comment");
                    }
                    // We have inverse our arrays :
                    reverse(idevents);
                    reverse(places);
                    reverse(topics);
                    reverse(levels);
                    reverse(times);
                    reverse(comments);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
                // We have to initialize the variable if there is no response :
                places = new String[]{"Error"};
                topics = new String[]{"Error"};
                levels = new String[]{"Error"};
                times = new String[]{"Error"};


            }
        });
        requestQueue.add(request);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_action, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:

                Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_SHORT).show();
                Afficher();
                //txView.setText(places[2].toString());
                ListView listView = (ListView) viewGroup.findViewById(R.id.list_fragment);
                ArrayList<List_actuality> pays = new ArrayList<List_actuality>();
                for (int i = 0; i < places.length; i++) {

                    pays.add(new List_actuality(places[i], times[i], topics[i],Integer.parseInt(levels[i]),comments[i]));
                }

                List_adapter list_adapter = new List_adapter(getContext(), pays);

                listView.setAdapter(list_adapter);

                return true;
            case R.id.settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    // function to inverse an array
    public void reverse(String[] s)
    {
        for(int i = 0; i < s.length / 2; i++)
        {

            String tableau = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = tableau;

        }
    }
}


