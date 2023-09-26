package com.dummyworks.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btn_add,btn_viewAll;
    public EditText et_name, et_age;
    public Switch sw_AtiveCustomer;
    public ListView lv_customerList;

    public  ArrayAdapter customerArrayAdapter;

    public DataBaseHelper dataBaseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewall);
        et_age = findViewById(R.id.et_date);
        et_name = findViewById(R.id.et_name);
        sw_AtiveCustomer = findViewById(R.id.sw_activeCustomer);
        lv_customerList =  findViewById(R.id.lv_customerList);


        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        ShowCustomersOnListView(dataBaseHelper);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomerModel customerModel ;
                try
                {
                    customerModel  = new CustomerModel(-1,et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()) ,sw_AtiveCustomer.isChecked());
                   Toast.makeText(MainActivity.this, customerModel.toString(),Toast.LENGTH_SHORT).show();
                  // Toast.makeText(MainActivity.this, toString(),Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Error creating Customer",Toast.LENGTH_SHORT).show();
                    customerModel =new CustomerModel (-1 , "error", 0 , false);

                }
                
       //         dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Success= "+success,Toast.LENGTH_SHORT).show();
                ShowCustomersOnListView(dataBaseHelper);
            }
        });
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   List<CustomerModel> everyone = dataBaseHelper.getEveryone();
              //  Toast.makeText(MainActivity.this, everyone.toString(),Toast.LENGTH_SHORT).show();
        //         dataBaseHelper = new DataBaseHelper(MainActivity.this);

                ShowCustomersOnListView(dataBaseHelper);
            }
        });

    lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CustomerModel ClickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
            dataBaseHelper.deleteOne(ClickedCustomer);
            ShowCustomersOnListView(dataBaseHelper);
            Toast.makeText(MainActivity.this, "Success= Deleted",Toast.LENGTH_SHORT).show();

        }
    });


    }
    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, this.dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);
    }
}