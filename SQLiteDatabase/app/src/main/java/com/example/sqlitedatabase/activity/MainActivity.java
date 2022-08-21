package com.example.sqlitedatabase.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlitedatabase.R;
import com.example.sqlitedatabase.adapter.AdapterInformation;
import com.example.sqlitedatabase.dao.IInformation;
import com.example.sqlitedatabase.dao.ImplInformationDAO;
import com.example.sqlitedatabase.entity.Information;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Information> mLst;
    AdapterInformation adapterInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadInformation();

        Button btnAdd = findViewById(R.id.itemBtnAddUser);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


        Button btnLoad = findViewById(R.id.itemBtnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInformation();
            }
        });


    }


    private void loadInformation() {
        IInformation iInformation = new ImplInformationDAO(this);
        mLst = iInformation.selectAll();

        AdapterInformation adapterInformation = new AdapterInformation(this, mLst);

        ListView listView = findViewById(R.id.listViewInformation);

        listView.setAdapter(adapterInformation);

        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        loadInformation();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAdd) {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        menu.setHeaderTitle("Lựa chọn: ");
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int idInf = mLst.get(info.position).getID();

        switch (id) {
            case R.id.menuUppdate:
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);

                intent.putExtra("idInf", idInf);

                startActivity(intent);
                break;
            case R.id.menuDelete:
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setMessage("Bạn có muốn xoá sản phẩm này");
                alertBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IInformation iInformation = new ImplInformationDAO(MainActivity.this);
                        boolean del = iInformation.delete(idInf);
                        if (del) {
                            Toast.makeText(MainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                            loadInformation();
                        } else {
                            Toast.makeText(MainActivity.this, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                alertBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
                alertBuilder.show();
                break;
        }
        return super.onContextItemSelected(item);
    }


}