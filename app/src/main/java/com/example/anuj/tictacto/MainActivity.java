package com.example.anuj.tictacto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    int tiles[]={2,2,2,2,2,2,2,2,2};
    boolean counter;
    int count;
    boolean inPlay;
    int winPos[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void clicked(View view){

        ImageView played= (ImageView) view;
        int c=Integer.parseInt(played.getTag().toString());
        if(tiles[c]==2 && inPlay) {

            count++;
            played.setAlpha(0f);
            if (counter) {

                counter = false;
                played.setImageResource(R.drawable.cross);
                played.animate().alpha(1f).setDuration(500);
                tiles[c] = 0;
                check();
            }
            else {

                played.setImageResource(R.drawable.circle);
                played.animate().alpha(1f).setDuration(500);
                counter = true;
                tiles[c] = 1;
                check();
            }

        }
    }

    public void check(){

        if(count>4) {

            for(int pos[] : winPos){

                if((tiles[pos[0]]==tiles[pos[1]]) && (tiles[pos[1]]==tiles[pos[2]]) && tiles[pos[0]]!=2) {

                    inPlay=false;
                    if(tiles[pos[1]]==0)
                        over("CROSS WON!!");
                    else
                        over("CIRCLE WON!!");
                }
            }


        }
        if(count==9 && inPlay) {
            inPlay = false;
            over("DRAW!!");
        }
    }

    public void over(String mess){

        GridLayout gridLay=findViewById(R.id.gridLay);
        gridLay.setAlpha(0.5f);
        TextView winMes=findViewById(R.id.winMes);
        winMes.setText(mess);
        LinearLayout infoLay=findViewById(R.id.infoLay);
        infoLay.setVisibility(View.VISIBLE);
    }

    public void invisible(View view){

        counter=true;
        inPlay=true;
        count=0;
        for(int i=0;i<tiles.length;i++)
            tiles[i]=2;
        LinearLayout infoLay=findViewById(R.id.infoLay);
        infoLay.setVisibility(View.INVISIBLE);
        GridLayout gridLay=findViewById(R.id.gridLay);
        gridLay.setAlpha(1f);
        for(int i=0;i<gridLay.getChildCount();i++)
            ((ImageView)gridLay.getChildAt(i)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter=true;
        inPlay=true;
        count=0;
    }
}
