package com.example.gymmf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FormularioPago extends AppCompatActivity {
    Button Pagar;
    TextView Total;
    EditText NumeroTarjeta, MM, YY, CVV;
    Intent DatosPlan;
    SharedPreferences settings, usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pago);

        Pagar = findViewById(R.id.btnPagarDeposito);
        Total = findViewById(R.id.txtTotal);
        NumeroTarjeta = findViewById(R.id.numNumeroTarjeta);
        MM = findViewById(R.id.numTarjetaMes);
        YY = findViewById(R.id.numTarjetaAno);
        CVV = findViewById(R.id.numCVV);

        DatosPlan = getIntent();
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        usuarios = getSharedPreferences("usuarios", 0);

        Total.setText("$" + DatosPlan.getStringExtra("CostoPlan"));



        Pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarTodo()) {
                    //actualizar usuario
                    if(actualizarEstadoPago()){
                        Intent menuPayed = new Intent(FormularioPago.this, MainActivity.class);
                        FormularioPago.this.startActivity(menuPayed);
                        finish();
                    } else {
                        //Deberia mandar error, pero por estilo software tipo Demo lo redireccionaremos
                        Intent menuPayed = new Intent(FormularioPago.this, MainActivity.class);
                        FormularioPago.this.startActivity(menuPayed);
                        finish();
                    }


                }
            }
        });
    }

    public boolean actualizarEstadoPago(){

        //IF Bloque con edicion de usuario actual
        if (!settings.getString("UsuarioActivo", "").equals("")) {
            String avatarActivo = settings.getString("AvatarActivo", "");
            if (!avatarActivo.isEmpty()) {
                try {
                    JSONObject AvatarObj = new JSONObject(avatarActivo);
                    if (AvatarObj.optString("PagoActivo").equals("true")) {
                        Intent intentMenuAutomatico = new Intent(this, MainActivity.class);
                        this.startActivity(intentMenuAutomatico);
                        finish();
                    } else {
                        //Cambiar  PagoActivo a verdadero
                        AvatarObj.put("PagoActivo", "true");
                        settings.edit().putString("AvatarActivo", AvatarObj.toString()).apply();
                        String BDUsuarios = usuarios.getString("BDUsuarios", "");

                        if (BDUsuarios.isEmpty()) {
                            Toast.makeText(this, "Error, falta implementar Bases de datos", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONArray jArrayUsuarios = new JSONArray(BDUsuarios);
                                String user = settings.getString("UsuarioActivo", "");
                                for (int i = 0; i < jArrayUsuarios.length(); i++) {
                                    JSONObject usuarioObj = new JSONObject(jArrayUsuarios.get(i).toString());
                                    if (user.equals(usuarioObj.getString("Usuario"))) {
                                        jArrayUsuarios.put(i, AvatarObj);
                                        usuarios.edit().putString("BDUsuarios", jArrayUsuarios.toString()).apply();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean validarTodo() {
        if (NumeroTarjeta.getText().toString().equals("")) {
            NumeroTarjeta.requestFocus();
            NumeroTarjeta.setError("Obligatorio");
            return false;
        }
        if (MM.getText().toString().equals("")) {
            MM.requestFocus();
            MM.setError("Obligatorio");
            return false;
        }
        if (YY.getText().toString().equals("")) {
            YY.requestFocus();
            YY.setError("Obligatorio");
            return false;
        }
        if (CVV.getText().toString().equals("")) {
            CVV.requestFocus();
            CVV.setError("Obligatorio");
            return false;
        }

        return true;
    }
}
