package fi.irinaromjalis.myapplication_5;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    public int creationsValue, valueIncrement, hitValue, hitIncrement;
    Button hitMe;
    TextView reset;
    TextView hitCounter, visible, creations;
    Counter count = new Counter(0, 50, 0, 1);

    String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);

        Log.d(TAG, "onCreate method: ");

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

        hitIncrement = count.increment(Integer.parseInt(hitCounter.getText().toString()));
        String hitRes = Integer.toString(hitIncrement);

        hitMe.setOnClickListener(view -> hitCounter.setText(hitRes));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = count.reset();
                String resetValue = Integer.toString(value);

                hitCounter.setText(resetValue);
                visible.setText(resetValue);
                creations.setText(resetValue);
            }
        });
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart method: ");
        int startValue = count.increment(Integer.parseInt(visible.getText().toString()));
        String stringValue = Integer.toString(startValue);
        visible.setText(stringValue);
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause method: ");

        SharedPreferences.Editor editor = getSharedPreferences("Shared", MODE_PRIVATE).edit();
        editor.putString("creations", creations.getText().toString());
        editor.putString("visible", visible.getText().toString());
        editor.putString("hit", hitCounter.getText().toString());

        editor.apply();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume method: ");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop method: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy method: ");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart method: ");
        super.onRestart();
    }
}