package com.example.crudapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText editTextName;
    //    private EditText editTextDesg;
//    private EditText editTextSal;

    private Button buttonAdd;
    private Button buttonView;

    private Spinner spinnerPosisi;
    ArrayList<String> posisiList = new ArrayList<>();
//    private ArrayList<Posisi> posisiList;
    ArrayList<String> gajihList = new ArrayList<>();
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi dari View
        editTextName = (EditText) findViewById(R.id.editTextName);
//        editTextDesg = (EditText) findViewById(R.id.editTextDesg);
//        editTextSal = (EditText) findViewById(R.id.editTextSalary);

        spinnerPosisi=(Spinner)findViewById(R.id.spinnerPosisi);
//        posisiList = new ArrayList<Posisi>();

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);


        new GetPosisiFromServer().execute();
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

        final String name = editTextName.getText().toString().trim();
//        final String aaaaaaa = editTextDesg.getText().toString().trim();
        final String desg = spinnerPosisi.getSelectedItem().toString().trim();
//        final String sal = editTextSal.getText().toString().trim();
        final String sal = gajihList.get((int) spinnerPosisi.getSelectedItemId()).trim();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA,name);
                params.put(konfigurasi.KEY_EMP_POSISI,desg);
                params.put(konfigurasi.KEY_EMP_GAJIH,sal);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,TampilSemuaPgw.class));
        }
        System.out.println("aaaaaaaaaaaa");
    }

    private class GetPosisiFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Fetching Data");
            pDialog.show();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
//            mobileName = spinnerMobile.getSelectedItem().toString();
            Handler jsonParser = new Handler();
            String json = jsonParser.makeServiceCall(konfigurasi.URL_GET_JABATAN, Handler.GET);

            Log.e("Response: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray model = jsonObj
                                .getJSONArray("result");

                        for (int i = 0; i < model.length(); i++) {
                            JSONObject modObj = (JSONObject) model.get(i);
                            String mod = new String(modObj.getString("posisi"));
                            String model_gaji = new String(modObj.getString("gajih"));
                            posisiList.add(mod);
                            gajihList.add(model_gaji);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            populateSpinnerModel();
        }
    }

    private void populateSpinnerModel() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < posisiList.size(); i++) {
            lables.add(posisiList.get(i));
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerPosisi.setAdapter(spinnerAdapter);

    }
}