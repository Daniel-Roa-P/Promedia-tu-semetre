package com.example.calculatusemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class secondActivity extends AppCompatActivity {

    private Button valor;
    private ListView listaNotas;
    private CustomeAdapter customeAdapter;
    public ArrayList<EditModel> editModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundo_layout);

        listaNotas =(ListView) findViewById(R.id.lista);
        valor = (Button) findViewById(R.id.button2);

        editModelArrayList = populateList();
        customeAdapter = new CustomeAdapter(this,editModelArrayList);
        listaNotas.setAdapter(customeAdapter);

        String idCantidad = getIntent().getStringExtra("cantidad");

        valor.setText(idCantidad);

    }

    private ArrayList<EditModel> populateList(){

        ArrayList<EditModel> list = new ArrayList<>();

        for(int i = 0; i < 16; i++){
            EditModel editModel = new EditModel();
            editModel.setEditTextValue(String.valueOf(i));
            list.add(editModel);
        }

        return list;
    }


    public void cambiar(View view){

        Intent cambio = new Intent(this,MainActivity.class);
        startActivity(cambio);

    }

}
