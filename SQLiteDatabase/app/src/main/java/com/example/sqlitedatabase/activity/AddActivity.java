package com.example.sqlitedatabase.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitedatabase.R;
import com.example.sqlitedatabase.dao.IInformation;
import com.example.sqlitedatabase.dao.ImplInformationDAO;
import com.example.sqlitedatabase.entity.Information;

public class AddActivity extends AppCompatActivity {



    private View.OnClickListener listenerAdd = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nameInf = ((EditText) findViewById(R.id.formTxtName)).getText().toString();
            String numberInf = ((EditText) findViewById(R.id.formTxtNumber)).getText().toString();
            String emailInf = ((EditText) findViewById(R.id.formTxtEmail)).getText().toString();

            Information inf = new Information(nameInf,numberInf,emailInf);
            IInformation iInformation = new ImplInformationDAO(AddActivity.this);
            boolean Yes = iInformation.insert(inf);
            if (Yes){
                Toast.makeText(AddActivity.this, "Thêm thành công! ", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(AddActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button btnAdd = findViewById(R.id.formBtnAdd);
        btnAdd.setOnClickListener(listenerAdd);

        Button btnviewAll = findViewById(R.id.formBtnViewAll);
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}