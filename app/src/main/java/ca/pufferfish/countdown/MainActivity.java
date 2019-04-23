package ca.pufferfish.countdown;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CountDownTimer cdTimer;
    private TextView textView;
    private Button  button;
    private long timeLeftInMilliseconds;
    private boolean timerRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeLeftInMilliseconds = 6000; // 600000 = 10 min
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        timerRunning = false;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning == false) {
                    timerRunning = true;
                    button.setText("Pause");
                    Log.d("AA","in click");
                    startTimer();
                }else{
                    timerRunning = false;
                    button.setText("Start");
                    pauseTimer();
                }
            }
        });
    }
    private void startTimer(){
        cdTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long muFinished) {
                timeLeftInMilliseconds = muFinished;
                updateFormate();
            }

            @Override
            public void onFinish() {
                button.setText("Restart");
                textView.setText("10:00");
                timeLeftInMilliseconds = 600000;
            }
        };
        cdTimer.start();
    }
    private void pauseTimer(){
            cdTimer.cancel();
    }

    private void updateFormate(){
        int min = (int) (timeLeftInMilliseconds /1000 )/60;
        int sec = (int) ( timeLeftInMilliseconds /1000 ) % 60;

        String timeFormated = String.format("%02d:%02d",min,sec);
        textView.setText(timeFormated);
    }
}
