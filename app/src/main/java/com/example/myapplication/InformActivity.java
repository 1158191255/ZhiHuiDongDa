package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InformActivity extends AppCompatActivity {

    Button btn;
    EditText ett1,ett2,ett3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputinform);

        ett1=findViewById(R.id.editText);
        ett2=findViewById(R.id.editText2);
        ett3=findViewById(R.id.editText3);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(InformActivity.this,MainActivity.class);
                intent.putExtra("number",ett1.getText().toString());
                intent.putExtra("stuname",ett2.getText().toString());
                intent.putExtra("distress",ett3.getText().toString());
                startActivity(intent);
                Toast.makeText(InformActivity.this, "修改成功", Toast.LENGTH_LONG).show();
            }
        });
    }
}
