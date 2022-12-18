package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.proyecto.BD.DBFirebase;
import com.example.proyecto.BD.DBHepler;
import com.example.proyecto.Entidades.Producto;
import com.example.proyecto.Servicios.ProductService;

public class ProductForm extends AppCompatActivity {
    private DBHepler dbHepler;
    private DBFirebase dbFirebase;
    private ProductService productService;
    private Button btnProductForm;
    private ImageView imgProductForm;
    private EditText editNameProductForm,editDescriptionProductForm,editPriceProductForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        btnProductForm = (Button) findViewById(R.id.btnProductForm);
        imgProductForm = (ImageView) findViewById(R.id.imgProductForm);
        editNameProductForm = (EditText) findViewById(R.id.editNameProductForm);
        editDescriptionProductForm = (EditText) findViewById(R.id.editDescriptionProductForm);
        editPriceProductForm = (EditText) findViewById(R.id.editPriceProductForm);

        try{
            dbHepler = new DBHepler(this);
            dbFirebase = new DBFirebase();
        } catch (Exception e){
            Log.e("Error DB", e.toString());
        }


        productService = new ProductService();

        btnProductForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto producto = new Producto(
                        editNameProductForm.getText().toString(),
                        editDescriptionProductForm.getText().toString(),
                        Integer.parseInt(editPriceProductForm.getText().toString()),
                        ""
                );
                dbFirebase.insertData(producto);
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }
}