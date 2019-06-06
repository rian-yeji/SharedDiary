package com.example.shareddiary;

/**
 * Created by HANEUL on 2017-07-29.
 */

import java.util.ArrayList;

/**
 * Created by HANEUL on 2017-05-03.
 */
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

    public class MyAdapter extends BaseAdapter {
        private Context context;
        private int resource;
        private ArrayList<BoardItem> contents = new ArrayList<BoardItem>();

        public MyAdapter(Context context, int resource, ArrayList<BoardItem> contents) {
            this.context = context;
            this.resource = resource;
            this.contents = contents;
        }

        public void addItem(BoardItem boardItem) {
            contents.add(boardItem);
        }


        @Override
        public int getCount() {
            return contents.size();
        }

        @Override
        public Object getItem(int position) {
            return contents.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView ==null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resource,parent,false);
            }
            TextView title = (TextView)convertView.findViewById(R.id.title);
            title.setText(contents.get(position).getTitle());

            TextView userID = (TextView)convertView.findViewById(R.id.userID);
            userID.setText(contents.get(position).getUserID());

            TextView date = (TextView)convertView.findViewById(R.id.date);
            date.setText(contents.get(position).getDate());

            TextView heart = (TextView)convertView.findViewById(R.id.heart);
            heart.setText(contents.get(position).getHeart());

            ImageView profile = (ImageView)convertView.findViewById(R.id.profile);
            profile.setImageResource(contents.get(position).getProfile());

            return convertView;
        }
    }