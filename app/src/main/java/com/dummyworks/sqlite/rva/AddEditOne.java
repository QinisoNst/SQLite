package com.dummyworks.sqlite.rva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dummyworks.sqlite.R;

import java.util.List;

public class AddEditOne extends AppCompatActivity {

    Button btn_cancel, btn_ok;
    List<President> presidentList;

    int id;
    EditText et_presidentName,et_dateElection,et_imageURL;

    TextView tv_presId;
    MyApplication myApplication= (MyApplication) this.getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_one);

        presidentList = myApplication.getPresidentList();

        btn_cancel = findViewById(R.id.btn_Cancel);
        btn_ok = findViewById(R.id.btn_ok);


        tv_presId = findViewById(R.id.tv_presidentIdNumber);

        et_presidentName = findViewById(R.id.et_presidentName);
        et_dateElection = findViewById(R.id.et_dateElection);
        et_imageURL = findViewById(R.id.et_imageURL);

        Intent intent = getIntent();

        id = intent.getIntExtra("id",-1);
        President president = null;

        if(id >=0)
        {
            for (President p: presidentList)
            {
                if(p.getId()==id)
                {
                    president =p;
                    et_presidentName.setText(president.getName());
                    et_dateElection.setText(String.valueOf(president.getDateOfElection()));
                    et_imageURL.setText(president.getImageURL());
                    tv_presId.setText(String.valueOf(id));

                }
            }
        }else
        {

        }
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id >=0 )
                {
                    President newPresident = new President(id, et_presidentName.getText().toString(),Integer.parseInt(et_dateElection.getText().toString()),et_imageURL.getText().toString());
                    presidentList.set(id,newPresident);

                }else{
                    int nextId = myApplication.getNextId();

                    President newPresident = new President(nextId, et_presidentName.getText().toString(),Integer.parseInt(et_dateElection.getText().toString()),et_imageURL.getText().toString());
                    presidentList.add(newPresident);
                    myApplication.setNextId(nextId++);
                }


                Intent intent = new Intent(AddEditOne.this, MainActivity1.class);
                startActivity(intent);

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddEditOne.this, MainActivity1.class);
                startActivity(intent);


            }
        });

    }
}