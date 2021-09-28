
package com.example.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.*;
import android.content.*;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
Button q1, q2, q3, submit;
String[] ans1 = {"Vue Js", "Android", "Both"};
String[] ans2 = {"Android", "Vue JS", "Quasar Framework", "Kotlin"};
boolean[] itemsChecked = {false, false, false, false};

int a1=-1, a3=-1;
ArrayList<String> arr = new ArrayList();
String text_ans="";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        q1 = findViewById(R.id.ques1);
        q2 = findViewById(R.id.ques2);
        q3 = findViewById(R.id.ques3);
        submit = findViewById(R.id.submit);
        arr.clear();
        //input_text = findViewById(R.id.input);

        //cancel=findViewById(R.id.cancel_btn);
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_baseline_question_answer_24)
                        .setTitle("What is used in this course?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(), "OK Clicked", Toast.LENGTH_SHORT).show();
                                a1=((AlertDialog)dialog).getListView().getCheckedItemPosition();
//                                onclickofok(a1);
                                Toast.makeText(getBaseContext(), Integer.toString(a1), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
                                a1=-1;
                            }
                        })
                        .setSingleChoiceItems(ans1, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(), "Item selected :" + ans1[whichButton], Toast.LENGTH_SHORT).show();
                            }

                        }).create();
                dialog.show();
            }
        });

        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)

                        .setIcon(R.drawable.ic_baseline_question_answer_24)
                        .setTitle("What is used in this course?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(), "OK Clicked", Toast.LENGTH_SHORT).show();
//                                a1=((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                Toast.makeText(getBaseContext(), Arrays.toString(itemsChecked), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
                                arr.clear();
                            }
                        })
                        .setMultiChoiceItems(ans2, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(getBaseContext(), ans2[which]+(isChecked ? " checked":" unchecked"), Toast.LENGTH_SHORT).show();
                                if(!arr.contains(ans2[which])){
                                    arr.add(ans2[which]);
                                }else{
                                    arr.remove(ans2[which]);
                                }
                            }
                        }).create();
                dialog.show();
            }
        });

        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               showDailog();

            }
        });

        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel(notificationManager);

        submit.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Score_notification.class);
                    i.putExtra("ans1", a1);
                    i.putStringArrayListExtra("ans2", arr);
                    i.putExtra("ans3", a3);
                    PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, i, 0);

                    //Creation of Notification
                    NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(MainActivity.this, "mychannel.Apps");
                    Notification n = nBuilder
                            .setSmallIcon(R.drawable.ic_baseline_assignment_turned_in_24)
                            .setContentTitle("Click To See your Score")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pi)
                            .setAutoCancel(true)
                            .build();
                notificationManager.notify(11, n);
                    Log.d("VR", "notification Created");

                }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void createChannel(NotificationManager mgr){
        NotificationChannel appsChannel = new NotificationChannel("mychannel.Apps", "Apps", NotificationManager.IMPORTANCE_DEFAULT);
        appsChannel.setLightColor(Color.GRAY);
        mgr.createNotificationChannel(appsChannel);
    }

    public void showDailog(){

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.text_input);

        Button ok, cancel;
        EditText input=dialog.findViewById(R.id.input);
        ok=dialog.findViewById(R.id.ok_click);
        cancel = dialog.findViewById(R.id.cancel_click);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_ans = input.getText().toString();
                if(text_ans.equalsIgnoreCase("Vasundhara")){
                    a3=1;
                }
                Log.d(String.valueOf(this), "Answer   :"+text_ans);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //dialog.getWindow().setBackgroundDrawableResource(R.drawable.ic_launcher_background);
        dialog.show();
    }
}