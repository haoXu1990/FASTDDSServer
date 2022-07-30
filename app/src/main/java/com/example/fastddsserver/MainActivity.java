package com.example.fastddsserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.example.fastddsserver.databinding.ActivityMainBinding;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    class MyThread implements Runnable {
        @Override
        public void run() {
            ddsSayHellow();
        }
    }

    // Used to load the 'fastddsserver' library on application startup.
    static {
        System.loadLibrary("fastddsserver");
    }

    private ActivityMainBinding binding;




    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());

//        Message message = mHandler.obtainMessage();

//        mHandler.sendEmptyMessage(0);

        MyThread myThread = new MyThread();

        Thread thread = new Thread(myThread);

        thread.run();
    }

    /**
     * A native method that is implemented by the 'fastddsserver' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void ddsSayHellow();

    public native void receviHello();
}