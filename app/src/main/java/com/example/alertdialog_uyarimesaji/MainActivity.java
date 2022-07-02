package com.example.alertdialog_uyarimesaji;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText editTextTextPersonName;
    EditText editTextNumber;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sharedPreferences metodunu çayırma
        sharedPreferences = this.getSharedPreferences("com.example.alertdialog_uyarimesaji" , Context.MODE_PRIVATE);
        //global değişkenleri onCreatMetodu altında çayırma
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextNumber = findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        //sharedPreferences if bloğu
        String name = sharedPreferences.getString("strogeName" , "");
        String age = sharedPreferences.getString("strogeAge" , "");
        if (name != "" || age != ""){
            textView.setText("Name: " + name);
            textView2.setText("Age: " + age);
        }
    }
    public void save(View view){
        if (!editTextTextPersonName.getText().toString().matches("") || !editTextNumber.getText().toString().matches("")){
            textView.setText("Name: " + editTextTextPersonName.getText());
            textView2.setText("Age: " + editTextNumber.getText());
            String name = editTextTextPersonName.getText().toString();
            String age = editTextNumber.getText().toString();
            sharedPreferences.edit().putString("strogeName" , name).apply();
            sharedPreferences.edit().putString("strogeAge" , age).apply();
        }
    }
    public void delete(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Are You Sure?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String strongName = sharedPreferences.getString("strogeName" , "");
                String strongAge = sharedPreferences.getString("strogeAge" , "");
                if(strongName != "" || strongAge != ""){
                    sharedPreferences.edit().remove("strogeName").apply();
                    sharedPreferences.edit().remove("strogeAge").apply();
                    textView.setText("Name:");
                    textView2.setText("Age:");
                    Toast.makeText(MainActivity.this , "Delete Date" , Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this , "Not Date" , Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.show();
    }
}