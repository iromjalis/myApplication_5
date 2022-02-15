package fi.irinaromjalis.myapplication_5;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int creationsValue, valueIncrement, hitValue, hitIncrement;
    Button hitMe;
    TextView reset, hitCounter, visible, creations;
    Counter count = new Counter(0, 50, 0, 1);

    String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);

        Log.d(TAG, "onCreate: ");

        reset = findViewById(R.id.resetBtn);

        visible = findViewById(R.id.visible);
        visible.setText(sharedPreferences.getString("visible", "0"));

        hitMe = findViewById(R.id.hitMeButton);
        hitCounter = findViewById(R.id.hitCounter);
        hitCounter.setText(sharedPreferences.getString("hit", "0"));

        creations = findViewById(R.id.creations);
        creations.setText(sharedPreferences.getString("creations", "0"));
        creationsValue = Integer.parseInt(creations.getText().toString());

        valueIncrement = count.increment(creationsValue);
        String res = Integer.toString(valueIncrement);
        creations.setText(res);

        hitValue = Integer.parseInt(hitCounter.getText().toString());
        hitIncrement = count.increment(hitValue);
        String hitRes = Integer.toString(hitIncrement);
//        hitCounter.setText(hitRes);

        hitMe.setOnClickListener(view -> hitCounter.setText(hitRes));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = Integer.toString(count.reset());

                Log.i(TAG, res );
                hitCounter.setText(res);
                visible.setText(res);
                creations.setText(res);
            }
        });
    }
    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");

        int visibleValue=Integer.parseInt((visible.getText().toString())+"");
        visible.setText(count.increment(visibleValue));
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        //Save info
        SharedPreferences.Editor editor = getSharedPreferences("Shared", MODE_PRIVATE).edit();

        editor.putString("hit", hitCounter.getText().toString());
        editor.putString("visible", visible.getText().toString());
        editor.putString("creations", creations.getText().toString());

        editor.apply();
        super.onPause();
    }
    //fir log in LogCat
    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }
    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: ");
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
