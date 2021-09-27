
package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
Button q1, q2, q3, submit;
CharSequence[] ans1 = {"Android", "Vue JS", "Both"};
CharSequence[] ans2 = {"Android", "Vue JS", "Quasar Framework", "Kotlin"};
boolean[] itemsChecked = {false, false, false, false};

int a1=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        q1 = findViewById(R.id.ques1);
        q2 = findViewById(R.id.ques2);
        q3 = findViewById(R.id.ques3);
        submit = findViewById(R.id.submit);
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
                                onclickofok(a1);
                                //Toast.makeText(getBaseContext(), Integer.toString(a1), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
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
                            }
                        })
                        .setMultiChoiceItems(ans2, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(getBaseContext(), ans2[which]+(isChecked ? " checked":" unchecked"), Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                dialog.show();
            }
        });

        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                showDailog();

            }
        });
    }

    public void onclickofok(int position){

    }
    public void showDailog(){
        
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(LayoutInflater.inflate( R.layout.text_input, null);
        Button ok, cancel;
        EditText input;
        ok=findViewById(R.id.ok_click);
        cancel = findViewById(R.id.cancel_click);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.ic_launcher_background);
        dialog.show();
    }
}