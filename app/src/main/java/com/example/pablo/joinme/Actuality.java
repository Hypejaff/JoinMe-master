package com.example.pablo.joinme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.*;

/*
This fragment will display the actuality so we use a list fragment
*/

public class Actuality extends Fragment {

    private static final String URL = "https://joinme.000webhostapp.com/JoinMe-3.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    SimpleAdapter simpleAdapter;
    private String[] idevents, places, topics, levels, times, comments;
    private ListView listView;
    ViewGroup viewGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_actuality, container, false);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        Afficher();

        //------------------

        //---------------
        setRetainInstance(true);
        //listView = (ListView) viewGroup.findViewById(R.id.list_fragment);
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
                    makeText(getContext(), e.toString(), LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                makeText(getContext(),"Error", LENGTH_LONG).show();
                // We have to initialize the variable if there is no response :
                places = new String[]{"Error"};
                topics = new String[]{"Error"};
                levels = new String[]{"Error"};
                times = new String[]{"Error"};
                comments = new String[]{"Error"};
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

    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                makeText(getActivity(), "Refresh", LENGTH_SHORT).show();

                Afficher();
                listView = (ListView) viewGroup.findViewById(R.id.list_fragment);
                ArrayList<List_actuality> pays = new ArrayList<List_actuality>();
                for (int i = 0; i < places.length; i++)
                {
                    pays.add(new List_actuality(places[i], times[i], topics[i],Integer.parseInt(levels[i]),comments[i]));
                }
                final List_adapter list_adapter = new List_adapter(getContext(), pays);

                // suppose to make the listView clikable
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(parent.getContext(),"coucou", Toast.LENGTH_SHORT).show();

                        makeText(getActivity(), "marche", LENGTH_SHORT).show();


                    }
                });

                listView.setAdapter(list_adapter);


                /*
listView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                */
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


