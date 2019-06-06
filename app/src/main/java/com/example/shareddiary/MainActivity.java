package com.example.shareddiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView home,chat,board,set,cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = (TextView)findViewById(R.id.board);
        board.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startOfActivity(3);
            }
        });

        cal = (TextView)findViewById(R.id.cal);
        cal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startOfActivity(4);
            }
        });

        set = (TextView)findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startOfActivity(5);
            }
        });
    }

    public void startOfActivity(int menu){
        Intent intent;
        switch (menu){
            case 1 :  break;
            case 2 :  break;
            case 3 :
                intent = new Intent(this, BoardListActivity.class);
                startActivity(intent);
                break;
            case 4 :
                intent = new Intent(this, CalActivity.class);
                startActivity(intent);
                break;
            case 5 :
                intent = new Intent(this, SetActivity.class);
                startActivity(intent);
                break;
            default: break;
        }
    }

}
