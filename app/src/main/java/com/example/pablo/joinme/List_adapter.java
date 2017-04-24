package com.example.pablo.joinme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.makeText;
import static java.security.AccessController.getContext;

public class List_adapter extends BaseAdapter {

    private Context context ;
    private ArrayList<List_actuality> mylist = new ArrayList<List_actuality>();

    public List_adapter(Context context, ArrayList<List_actuality> arrayList)
    {

        this.mylist = arrayList ;
        this.context = context ;

    }
    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.mylist.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder mViewHolder = null;

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.content_list, parent, false);

            // nous plaçons dans notre MyViewHolder les vues de notre layout

            mViewHolder = new MyViewHolder();
            mViewHolder.textViewPlace= (TextView) convertView
                    .findViewById(R.id.placeEvent);
            mViewHolder.textViewTopic = (TextView) convertView
                    .findViewById(R.id.topicEvent);
            /*
            mViewHolder.textViewTime = (TextView) convertView
                    .findViewById(R.id.timeEvent);

            mViewHolder.textViewLevel = (SeekBar) convertView
                    .findViewById(R.id.levelEvent);
            mViewHolder.textViewLevelstring = (TextView) convertView
                    .findViewById(R.id.levelTextEvent) ;
            mViewHolder.textViewComment = (TextView) convertView
                    .findViewById(R.id.commentTxt) ;
            */
            // Pour ajouter nos deux bouttons
            mViewHolder.btnInfo = (Button) convertView
                    .findViewById(R.id.btnInfo) ;
            mViewHolder.btnParticip = (Button) convertView
                    .findViewById(R.id.btnInfo) ;

            // nous attribuons comme tag notre MyViewHolder à convertView
            convertView.setTag(mViewHolder);
        } else {
            // convertView n'est pas null, nous récupérons notre objet MyViewHolder
            // et évitons ainsi de devoir retrouver les vues à chaque appel de getView
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        // nous récupérons l'item de la liste demandé par getView
        final List_actuality listItem = (List_actuality) getItem(position);

        // nous pouvons attribuer à nos vues les valeurs de l'élément de la liste
        mViewHolder.textViewPlace.setText(listItem.getPlace());
        //mViewHolder.textViewTime.setText(String.valueOf(listItem.getTime()));
        mViewHolder.textViewTopic.setText(listItem.getTopic());
        //mViewHolder.textViewLevelstring.setText(" : " + listItem.getLeveltxt());
        //mViewHolder.textViewLevel.setProgress(listItem.getLevel());
        //mViewHolder.textViewComment.setText(listItem.getComment());

        // Put a max vdefault value and disable the user to change the value of the seekbar
        /*
        mViewHolder.textViewLevel.setMax(8);

        mViewHolder.textViewLevel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        */
        mViewHolder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dialog dialog = new Dialog(v.getContext()) ;
                dialog.setContentView(R.layout.fragment_my_dialog);
                dialog.setTitle("Informations");

                // Associate to the textView and seekBar on the layout
                TextView textViewTopic1 = (TextView)dialog.findViewById(R.id.topic1) ;
                TextView textViewPlace1 = (TextView)dialog.findViewById(R.id.place1) ;
                TextView textViewHeure1 = (TextView)dialog.findViewById(R.id.time1) ;
                TextView textViewLevelString1 = (TextView)dialog.findViewById(R.id.levelTextEvent1) ;
                TextView textViewComment = (TextView)dialog.findViewById(R.id.commentTxt1) ;
                SeekBar seekBarLevel1 = (SeekBar)dialog.findViewById(R.id.level1) ;

                // Put the value inside
                textViewTopic1.setText(listItem.getTopic());
                textViewPlace1.setText(listItem.getPlace());
                textViewHeure1.setText(listItem.getTime());
                seekBarLevel1.setProgress(listItem.getLevel());
                textViewLevelString1.setText(listItem.getLeveltxt());
                textViewComment.setText(listItem.getComment());

                // The user can not change the value of the seekBar
                seekBarLevel1.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });

                // Display the Diaplog
                dialog.show();

            }
        });

        // nous retournos la vue de l'item demandé
        return convertView;

    }
    private class MyViewHolder {
        TextView textViewPlace, textViewTime, textViewTopic, textViewLevelstring, textViewComment ;
        SeekBar textViewLevel ;
        Button btnParticip, btnInfo ;

    }

    /*
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast toast = Toast.makeText(context, "Item " + (position + 1) + ": "
                + this.myList.get(position), Toast.LENGTH_SHORT);
        toast.show();

        //Toast.makeText(context,"coucou",Toast.LENGTH_SHORT).show();

    }
    */




}


