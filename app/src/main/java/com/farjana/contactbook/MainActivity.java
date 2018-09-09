package com.farjana.contactbook;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnView, btnUpdate, btnDelete;

    EditText txtId, txtName, txtCell;

    MySqliteDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        btnView = findViewById(R.id.btn_view);

        txtId = findViewById(R.id.txt_id);
        txtCell = findViewById(R.id.txt_cell);
        txtName = findViewById(R.id.txt_name);

        myDB = new MySqliteDB(MainActivity.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txtId.getText().toString();
                String name = txtName.getText().toString();
                String cell = txtCell.getText().toString();

                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "ID Can't be empty", Toast.LENGTH_SHORT).show();
                } else if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Name can't be empty", Toast.LENGTH_SHORT).show();
                } else if (cell.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Cell number can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = myDB.addToTable(id, name, cell);

                    if (check == true) {
                        Toast.makeText(MainActivity.this, "Data successfully saved.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not saved", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });


    }
        public void viewData(View v){
            Cursor result = myDB.ViewData();
                    if(result.getCount()==0)
                    {
                        Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        StringBuffer buffer=new StringBuffer();
                        result.moveToFirst();//for pointing frist row of table
                        do {
                            buffer.append("ID:"+result.getString(0)+"\n");
                            buffer.append("NAME:"+result.getString(1)+"\n");
                            buffer.append("CELL:"+result.getString(2)+"\n");
                        }while (result.moveToNext());
                        showData("Contact Info",buffer.toString());
                    }
        }
        public void showData(String title,String data){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(title);
            dialog.setIcon(R.mipmap.ic_launcher);
            dialog.setCancelable(true);
            dialog.setMessage(data);
            dialog.show();
        }

        public void updateData(View v){
            String id = txtId.getText().toString();
            String name = txtName.getText().toString();
            String cell = txtCell.getText().toString();

            if (id.isEmpty()) {
                Toast.makeText(MainActivity.this, "ID Can't be empty", Toast.LENGTH_SHORT).show();
            } else if (name.isEmpty()) {
                Toast.makeText(MainActivity.this, "Name can't be empty", Toast.LENGTH_SHORT).show();
            } else if (cell.isEmpty()) {
                Toast.makeText(MainActivity.this, "Cell number can't be empty", Toast.LENGTH_SHORT).show();
            } else {
                boolean check = myDB.updateData(id, name, cell);

                if (check == true) {
                    Toast.makeText(MainActivity.this, "Data successfully saved.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not saved", Toast.LENGTH_SHORT).show();
                }
            }
        }
        public void deleteData(View v)
        {
            String id = txtId.getText().toString();
            int cheak = myDB.deleteData(id);
            if (cheak>0){
                Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
            }
        }

    }

