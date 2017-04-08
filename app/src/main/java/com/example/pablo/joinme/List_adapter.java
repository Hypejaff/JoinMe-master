package com.example.pablo.joinme;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

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
            mViewHolder.textViewTime = (TextView) convertView
                    .findViewById(R.id.timeEvent);
            mViewHolder.textViewTopic = (TextView) convertView
                    .findViewById(R.id.topicEvent);
            mViewHolder.textViewLevel = (SeekBar) convertView
                    .findViewById(R.id.levelEvent);
            mViewHolder.textViewLevelstring = (TextView) convertView
                    .findViewById(R.id.levelTextEvent) ;
            mViewHolder.textViewComment = (TextView) convertView
                    .findViewById(R.id.commentTxt) ;

            // nous attribuons comme tag notre MyViewHolder à convertView
            convertView.setTag(mViewHolder);
        } else {
            // convertView n'est pas null, nous récupérons notre objet MyViewHolder
            // et évitons ainsi de devoir retrouver les vues à chaque appel de getView
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        // nous récupérons l'item de la liste demandé par getView
        List_actuality listItem = (List_actuality) getItem(position);

        // nous pouvons attribuer à nos vues les valeurs de l'élément de la liste
        mViewHolder.textViewPlace.setText(listItem.getPlace());
        mViewHolder.textViewTime.setText(String.valueOf(listItem.getTime()));
        mViewHolder.textViewTopic.setText(listItem.getTopic());
        mViewHolder.textViewLevelstring.setText(" : " + listItem.getLeveltxt());
        mViewHolder.textViewLevel.setProgress(listItem.getLevel());
        mViewHolder.textViewComment.setText(listItem.getComment());

        // Put a max vdefault value and disable the user to change the value of the seekbar
        mViewHolder.textViewLevel.setMax(8);

        mViewHolder.textViewLevel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


        // nous retournos la vue de l'item demandé
        return convertView;

    }
    private class MyViewHolder {
        TextView textViewPlace, textViewTime, textViewTopic, textViewLevelstring, textViewComment ;
        SeekBar textViewLevel ;

    }
}
/*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(context, "Item " + (position + 1) + ": "
                + this.myList.get(position), Toast.LENGTH_SHORT);
        toast.show();

    }
 */