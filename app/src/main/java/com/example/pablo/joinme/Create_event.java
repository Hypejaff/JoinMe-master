package com.example.pablo.joinme;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Create_event extends Fragment{


    private  static final String URL = "https://joinme.000webhostapp.com/JoinMe_send.php" ;
    private RequestQueue requestQueue ;
    private StringRequest request ;

    TextView textView ;
    private EditText place, topic, comment ;
    SeekBar seekBarLevel ;
    Button buttonTimeEvent;
    private Button buttonCreateEvent ;
    private TextView textViewHeure;

    private static int hour, minute ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);

        place = (EditText)view.findViewById(R.id.place);
        topic = (EditText)view.findViewById(R.id.topic);
        seekBarLevel = (SeekBar)view.findViewById(R.id.level) ;
        buttonTimeEvent = (Button)view.findViewById(R.id.timeEvent) ;
        textView = (TextView)view.findViewById(R.id.txtSeekBar) ;
        textView.setText(String.valueOf(seekBarLevel.getProgress()));
        comment = (EditText)view.findViewById(R.id.comment);
        buttonCreateEvent = (Button)view.findViewById(R.id.btnCreateEvent);
        textViewHeure = (TextView)view.findViewById(R.id.thetime) ;

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        buttonTimeEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(),"Time Picker");
            }
        });

        seekBarLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {textView.setText(String.valueOf(progress));}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        }) ;


        //----------------------------------------------------- Button Create Event ---------------------------------------------------------
        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity().getApplicationContext(),"Event Created",Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("comment",comment.getText().toString());
                        hashMap.put("time",textViewHeure.getText().toString());
                        hashMap.put("level",textView.getText().toString());
                        hashMap.put("topic",topic.getText().toString());
                        hashMap.put("place",place.getText().toString());
                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

        return view ;
    }

    // Setter
    public void setHour(int h){this.hour = h ;}
    public void setMinute(int m) {this.minute = m ;}
    // getter
    public int getMinute() {return this.minute ;}
    public int getHour() {return  this.hour ; }
}
