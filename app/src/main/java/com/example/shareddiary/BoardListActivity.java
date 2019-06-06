package com.example.shareddiary;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BoardListActivity extends AppCompatActivity {

    static MyAdapter adapter;
    Button addboard;
    ArrayList<BoardItem> getJsonData;

    SimpleDateFormat today = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

        getIntent();
        getJsonData = new ArrayList<BoardItem>();

        ListDB BDB = new ListDB();
        BDB.execute();

        //어댑터 생성
        adapter = new MyAdapter(this, R.layout.board_item, getJsonData);
        //어댑터 연결
        final ListView listView = (ListView) findViewById(R.id.listView);
        adapter.addItem(new BoardItem("글제목", "Haneul", today.format(new Date()), "♡♡ 수", R.drawable.profile, "내용"));
        listView.setAdapter(adapter);

        addboard = (Button) findViewById(R.id.boardBtn);
        addboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("haneul", "버튼클릭");
                Intent intent = new Intent(getWindow().getContext(), BoardActivity.class);
                intent.putExtra("haneul", "인텐트");
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


    }

    class ListDB extends AsyncTask<String, String, String> {
        String data;
        String receiveMsg;

        BoardItem items;
        ArrayList<BoardItem> ArrItems;

        @Override
        protected String doInBackground(String... params) {
            try {
                //String data;
                URL url = new URL("http://10.57.177.97/AndroidProject_SharedDiary/board_loadDB.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Accept-Charset", "UTF-8");

                conn.setDoInput(true);
                conn.connect();

                Log.i("yeji", "boardList URL에 접속");

                if (conn.getResponseCode() == conn.HTTP_OK) {

                    InputStreamReader in = new InputStreamReader(conn.getInputStream(), "EUC-KR");
                    BufferedReader reader = new BufferedReader(in);
                    StringBuffer buffer = new StringBuffer();

                    while ((data = reader.readLine()) != null) {
                        buffer.append(data);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("yeji", "서버에서 안드로이드로 전달 됨");
                    Log.i("haneulhaneul", receiveMsg);

                    JSONObject json = new JSONObject(receiveMsg);
                    JSONArray jArr = json.getJSONArray("datasend");
                    Log.i("yeji","~"+jArr.length());

                    for (int i = 0; i < jArr.length(); i++) {
                        json = jArr.getJSONObject(i);
                        items =  new BoardItem();
                        items.setTitle(json.getString("bd_title"+i));
                        items.setUserID(json.getString("bd_user"+i));
                        items.setContents(json.getString("bd_contents"+i));
                        items.setDate(json.getString("bd_dates"+i));
                        getJsonData.add(items);
                    }
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return receiveMsg;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
