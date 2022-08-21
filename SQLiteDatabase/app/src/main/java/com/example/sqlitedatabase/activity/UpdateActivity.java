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

public class UpdateActivity extends AppCompatActivity {

    private int curIdInf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        curIdInf = getIntent().getExtras().getInt("idInf");


        IInformation iInformation = new ImplInformationDAO(this);
        Information i = iInformation.detail(curIdInf);

        EditText editName = findViewById(R.id.formTxtName);
        EditText editNumber = findViewById(R.id.formTxtNumber);
        EditText editEmail = findViewById(R.id.formTxtEmail);

        editName.setText(i.getName());
        editNumber.setText(i.getNumber());
        editEmail.setText(i.getEmail());

        Button btnUpdate = findViewById(R.id.formBtnUpdate);

        btnUpdate.setOnClickListener(listenerUpdate);


        Button btnviewAll = findViewById(R.id.formBtnViewAll);
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }





    private View.OnClickListener listenerUpdate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String nameInf = ((EditText) findViewById(R.id.formTxtName)).getText().toString();
            String numberInf = ((EditText) findViewById(R.id.formTxtNumber)).getText().toString();
            String emailInf = ((EditText) findViewById(R.id.formTxtEmail)).getText().toString();

            Information inf = new Information(curIdInf, nameInf, numberInf, emailInf);

            IInformation iInformation = new ImplInformationDAO(UpdateActivity.this);

            boolean Yes = iInformation.update(inf);
            if (Yes) {
                Toast.makeText(UpdateActivity.this, "Cập nhật thành công! ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UpdateActivity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
            }

        }
    };


}