package com.example.focuslense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.MediaController;
import android.widget.VideoView;
import android.net.Uri;
import android.databinding.DataBindingUtil;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;


import com.example.focuslense.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    AppCompatActivity context;
    //permission handling
    private String phone_number = "";
    // control commands as strings
    private static final String ACTIVATE_MSG = "$SDIG,1,1";
    private static final String DEACTIVATE_MSG = "$SDIG,1,0";
    private static final String FRONT_MSG = "$SDIG,3,0$SDIG,4,1";
    private static final String LEFT_MSG = "$SDIG,3,0$SDIG,4,0";
    private static final String RIGHT_MSG = "$SDIG,3,1$SDIG,4,0";
    private static final String REAR_MSG = "$SDIG,3,1$SDIG,4,1";

    private static final String base_url = "rtsp://10.8.0.138:9001/stream";

    private static String camera_url = "";


    SmsManager smsManager = SmsManager.getDefault();

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // setContentView(R.layout.activity_main);
       // videoView = (VideoView)this.findViewById(R.id.videoview);
        //add controls to a MediaPlayer like play, pause.
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        //Set the path of Video or URI
        videoView.setVideoURI(Uri.parse(base_url));
        //

        //Set the focus
        videoView.requestFocus();



        binding.on_off_switch.setOnClickListener(v -> {
            try{
                SmsManager smgr = SmsManager.getDefault();
                smgr.sendTextMessage(phone_number,null,ACTIVATE_MSG,null,null);
                Toast.makeText(context,"Device Activated successfully ",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(context,"Sms Failed .Device not activated ",Toast.LENGTH_SHORT).show();

            }

            //activate_system();
        });

        //deactivate the system
        binding.buttonDeactivate.setOnClickListener(v -> deactivate_system());

        // switch rear camera
        binding.rear_button.setOnClickListener(v -> activate_rear());

        // switch front camera
        binding.front_button.setOnClickListener(v -> activate_front());

        // switch forward camera
        binding.right_button.setOnClickListener(v -> activate_right());

        // switch left camera
        binding.left_button.setOnClickListener(v -> activate_left());



    }


    private void activate_system(){
        //  Log.i("my Tag","successfully activated system");

        smsManager.sendTextMessage(binding.editTextPhoneNumber.getText().toString(),null,ACTIVATE_MSG,null,null);

    }
    private void deactivate_system(){
        smsManager.sendTextMessage(binding.editTextPhoneNumber.getText().toString(),null,DEACTIVATE_MSG,null,null);

    }

    private void activate_front(){
        smsManager.sendTextMessage(binding.editTextPhoneNumber.getText().toString(),null, FRONT_MSG,null,null);

    }

    private void activate_rear(){
        smsManager.sendTextMessage(binding.editTextPhoneNumber.getText().toString(),null, FRONT_MSG,null,null);

    }


    private void activate_left(){
        smsManager.sendTextMessage(binding.editTextPhoneNumber.getText().toString(),null, LEFT_MSG,null,null);

    }

    private void activate_right(){
        smsManager.sendTextMessage(binding.editTextPhoneNumber.getText().toString(),null, RIGHT_MSG,null,null);

    }
}
