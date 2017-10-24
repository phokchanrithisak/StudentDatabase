package com.example.phokchanrithisak.studentdatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dh;
    StringBuffer viewInsertData;
    EditText editId, editName;
    Button buttonI, buttonV;
    TextView showData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dh = new DatabaseHelper(this);
        editId = (EditText) findViewById(R.id.editId);
        editName = (EditText) findViewById(R.id.editName);
        buttonI = (Button) findViewById(R.id.buttonInsert);
        buttonV = (Button) findViewById(R.id.buttonView);
        showData = (TextView) findViewById(R.id.viewData);

        addDataFunc();
        viewDataFunc();
    }
    public void addDataFunc(){
        buttonI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dh.insertDataMethod(   editId.getText().toString(),
                                                        editName.getText().toString());
                if(result)
                    Toast.makeText(MainActivity.this, "Data successfully inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data failed", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void viewDataFunc(){
        buttonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dh.viewDataMethod();
                viewInsertData = new StringBuffer();
                if(res.getCount() != 0){
                    while (res.moveToNext()){
                        viewInsertData.append("id: "+res.getString(0)+"\n");
                        viewInsertData.append("Name: "+res.getString(1)+"\n");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "No DATA show", Toast.LENGTH_LONG).show();
                }
                showData.setText(viewInsertData);
            }
        });
    }
}
