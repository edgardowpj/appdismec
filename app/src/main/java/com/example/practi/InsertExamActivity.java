package com.example.practi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class InsertExamActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubir;
    private ImageView imageView;
    private EditText editTextTexto,a,b,c,d,CorrectAnswer;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST=1;
    private String url="http://192.168.1.66/proyecto/InsertExam.php";

    private String Keytexto="textexam";
    private String KeytextoA = "a";
    private String KeytextoB = "b";
    private String KeytextoC = "c";
    private String KeytextoD = "d";
    private String KeyCorrectAnswer="correctAnswer";
    private String KeyImg="img";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_exam);

        btnSubir = (Button) findViewById(R.id.btnInsert);
        editTextTexto=findViewById(R.id.editText);
        a=findViewById(R.id.editText2);
        b=findViewById(R.id.editText3);
        c=findViewById(R.id.editText4);
        d=findViewById(R.id.editText5);
        CorrectAnswer=findViewById(R.id.editText6);
        imageView  = (ImageView) findViewById(R.id.imageView);
        btnSubir.setOnClickListener(this);

    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this,"Saving...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(InsertExamActivity.this, s , Toast.LENGTH_LONG).show();
                        //cambio de pagina
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(InsertExamActivity.this, "You must fill all the fields".toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //Obtener el nombre de la imagen
                String nombre = editTextTexto.getText().toString().trim();
                String A=a.getText().toString().trim();
                String B=b.getText().toString().trim();
                String C=c.getText().toString().trim();
                String D=d.getText().toString().trim();
                String correctAnswer=CorrectAnswer.getText().toString().trim();


                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(KeyImg, imagen);
                params.put(Keytexto, nombre);
                params.put(KeytextoA,A);
                params.put(KeytextoB,B);
                params.put(KeytextoC,C);
                params.put(KeytextoD,D);
                params.put(KeyCorrectAnswer,correctAnswer);

                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void LoadImg(View view){
        showFileChooser();
    }

    @Override
    public void onClick(View v) {
        if(v == btnSubir){
            uploadImage();
        }
    }
}