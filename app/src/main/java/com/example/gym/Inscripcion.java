package com.example.gym;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inscripcion extends AppCompatActivity {
    EditText Nombre, Apellidos, Peso, Estatura, Telefono,
    Email, Usuario, Pass, ConfirmPass;
    Button Regresar, Registrar;
    SharedPreferences usuarios, settings;
    RadioButton GeneroH, GeneroM;
    Boolean emptyUsersDb;
    JSONArray jArrayUsuarios;
    RadioGroup RGGenero;
    Intent intentIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscripcion);

        Nombre = findViewById(R.id.txtNombre);
        Apellidos = findViewById(R.id.txtApellido);
        Peso = findViewById(R.id.txtPeso);
        Estatura = findViewById(R.id.txtEstatura);
        Telefono = findViewById(R.id.txtTelefono);
        Email = findViewById(R.id.txtEmail);
        Usuario = findViewById(R.id.txtUsuario);
        Pass = findViewById(R.id.txtContraseña);
        ConfirmPass = findViewById(R.id.txtConfirma);
        GeneroH = findViewById(R.id.rbHombre);
        GeneroM = findViewById(R.id.rbMujer);
        RGGenero = findViewById(R.id.grGenero);

        Regresar = findViewById(R.id.btnRegresar);
        //Regresar = findViewById(R.id.btnRegistro);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        usuarios = getSharedPreferences("usuarios", 0);
        System.out.println(usuarios.getAll().toString());
        String BDUsuarios = usuarios.getString("BDUsuarios", "");

        intentIns = getIntent();

        if(!settings.getString("UsuarioActivo","").equals("")){
            String avatar = settings.getString("AvatarActivo", "");
            if(!avatar.isEmpty()){
                try {
                    JSONObject AvatarObj = new JSONObject(avatar);
                    if(AvatarObj.optString("PagoActivo").equals("true")){
                        Intent intentMenuAutomatico = new Intent(Inscripcion.this, MainActivity.class);
                        Inscripcion.this.startActivity(intentMenuAutomatico);
                        finish();
                    } else if (AvatarObj.optString("DemoActivo").equals("true")){
                        Intent intentMenuAutomatico = new Intent(Inscripcion.this, MainActivity.class);
                        Inscripcion.this.startActivity(intentMenuAutomatico);
                        finish();
                    } else if (AvatarObj.optString("DemoTerminado").equals("false")) {
                        //Si no verificar si viene de pago redireccionarlo a metodo, si va a dia de prueba cambiar los settings de usuario y activar el demo day
                        //PERO primero hay que verificar que no se halla acabado el dia demo

                        if(intentIns.getBooleanExtra("DemoDay", false)){
                            //Cambiar a estado activo de dia de prueba
                            AvatarObj.put("DemoActivo", "true");
                            settings.edit().putString("AvatarActivo", AvatarObj.toString()).apply();
                            String BDUsuariosPosRegistro = usuarios.getString("BDUsuarios", "");

                            if (BDUsuariosPosRegistro.isEmpty()) {
                                Toast.makeText(this, "Error, falta implementar Bases de datos", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    JSONArray jArrayUsuarios = new JSONArray(BDUsuariosPosRegistro);
                                    //System.out.println("USUs: " + BDUsuarios + " L: " + jArrayUsuarios.length());
                                    String user = settings.getString("UsuarioActivo", "");
                                    for (int i = 0; i < jArrayUsuarios.length(); i++) {
                                        //System.out.println("Usuario#" + i + " " + jArrayUsuarios.get(i));
                                        JSONObject usuarioObj = new JSONObject(jArrayUsuarios.get(i).toString());
                                        if (user.equals(usuarioObj.getString("Usuario"))) {
                                            jArrayUsuarios.put(i,AvatarObj);
                                            usuarios.edit().putString("BDUsuarios", jArrayUsuarios.toString()).apply();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Intent cambioAprueba = new Intent(Inscripcion.this, MainActivity.class);
                            Inscripcion.this.startActivity(cambioAprueba);
                            finish();
                        } else {
                            Intent goToPay = new Intent(Inscripcion.this, Planes.class);
                            goToPay.putExtra("NombrePlan", intentIns.getStringExtra("NombrePlan"));
                            goToPay.putExtra("TiempoPlan", intentIns.getStringExtra("TiempoPlan"));
                            goToPay.putExtra("CostoPlan", intentIns.getStringExtra("CostoPlan"));
                            goToPay.putExtra("DescPlan", intentIns.getStringExtra("DescPlan"));
                            Inscripcion.this.startActivity(goToPay);
                        }

                    } else {
                        if(intentIns.getBooleanExtra("DemoDay", false)){
                            //Cambiar a estado activo de dia de prueba
                            Toast.makeText(this, "Tu día de prueba ha terminado", Toast.LENGTH_SHORT).show();
                            Intent TerminoPrueba = new Intent(Inscripcion.this, Planes.class);
                            Inscripcion.this.startActivity(TerminoPrueba);
                            finish();
                        } else {
                            Intent goToPay = new Intent(Inscripcion.this, Planes.class);
                            goToPay.putExtra("NombrePlan", intentIns.getStringExtra("NombrePlan"));
                            goToPay.putExtra("TiempoPlan", intentIns.getStringExtra("TiempoPlan"));
                            goToPay.putExtra("CostoPlan", intentIns.getStringExtra("CostoPlan"));
                            goToPay.putExtra("DescPlan", intentIns.getStringExtra("DescPlan"));
                            Inscripcion.this.startActivity(goToPay);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }


        emptyUsersDb = false;
        if(BDUsuarios.isEmpty()){
            emptyUsersDb = true;
            jArrayUsuarios = new JSONArray();
        } else {
            try {
                jArrayUsuarios = new JSONArray(BDUsuarios);
                System.out.println("USUs: " + BDUsuarios + " L: " + jArrayUsuarios.length());
                for(int i = 0; i< jArrayUsuarios.length(); i ++){
                    System.out.println("Usuario#" + i + " " + jArrayUsuarios.get(i));
                    JSONObject usuarioObj = new JSONObject(jArrayUsuarios.get(i).toString());
                    System.out.println("Usu obj: "  + usuarioObj.getString("Usuario"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Registrar= findViewById(R.id.btnRegistro);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validarTodo())
                    return;
                if(!validarGenero())
                    return;
                try {
                    if(!validarUsuario())
                        return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(!validarContra())
                    return;

                if(registrarUsuario()){

                    if(intentIns.getBooleanExtra("DemoDay", false)){
                        Intent pantallaExito = new Intent(Inscripcion.this, Exito.class);
                        Inscripcion.this.startActivity(pantallaExito);
                        finish();
                    } else{
                        Intent pantallaMetodo = new Intent(Inscripcion.this, Exito.class);
                        pantallaMetodo.putExtra("UserPays", true);
                        pantallaMetodo.putExtra("NombrePlan", "Nombre1");
                        pantallaMetodo.putExtra("TiempoPlan", "Tiempo1");
                        pantallaMetodo.putExtra("CostoPlan", "Costo1");
                        pantallaMetodo.putExtra("DescPlan", "Desc1");
                        Inscripcion.this.startActivity(pantallaMetodo);
                        finish();
                    }

                } else {
                    Toast.makeText(Inscripcion.this, "Ha ocurrido un error, por favor intentalo unos minutos más tarde.", Toast.LENGTH_SHORT).show();
                }

                //  jsonUsuario.get
            }
        });

    }
    public boolean validarGenero (){

        if(GeneroH.isChecked() || GeneroM.isChecked()){
            return true;
        } else {
            Toast.makeText(this, "Selecciona tu genero", Toast.LENGTH_SHORT).show();
            RGGenero.requestFocus();
        }
        return false;
    }

    public boolean validarContra (){
        String pass1 = Pass.getText().toString();
        String pass2 = ConfirmPass.getText().toString();
        if(pass1.equals(pass2)){
            return true;
        } else {
            Pass.setText("");
            ConfirmPass.setText("");
            Pass.requestFocus();
            Pass.setError("Las contraseñas no coinciden");
            return false;
        }
    }
    public boolean validarUsuario () throws JSONException {
        String intentoUsuario = Usuario.getText().toString();
        for(int i = 0; i< jArrayUsuarios.length(); i ++){
            //System.out.println("Usuario#" + i + " " + jArrayUsuarios.get(i));
            JSONObject usuarioObj = new JSONObject(jArrayUsuarios.get(i).toString());
            //System.out.println("Usu obj: "  + usuarioObj.getString("Usuario"));
            if(intentoUsuario.equals(usuarioObj.getString("Usuario"))){
                Usuario.requestFocus();
                Usuario.setError("Este usuario ya existe");
                return false;
            }
        }
        //System.out.println("Usuario sin repetir");
        return true;
    }

    public boolean validarTodo () {
        if(Nombre.getText().toString().equals("")){
            Nombre.requestFocus();
            Nombre.setError("Obligatorio");
            return false;
        }
        if(Apellidos.getText().toString().equals("")){
            Apellidos.requestFocus();
            Apellidos.setError("Obligatorio");
            return false;
        }if(Telefono.getText().toString().equals("")){
            Telefono.requestFocus();
            Telefono.setError("Obligatorio");
            return false;
        }if(Email.getText().toString().equals("")){
            Email.requestFocus();
            Email.setError("Obligatorio");
            return false;
        }if(Usuario.getText().toString().equals("")){
            Usuario.requestFocus();
            Usuario.setError("Obligatorio");
            return false;
        }if(Pass.getText().toString().equals("")){
            Pass.requestFocus();
            Pass.setError("Obligatorio");
            return false;
        }
        return true;
    }

    public boolean registrarUsuario(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("Nombre", Nombre.getText().toString());
        map.put("Apellidos", Apellidos.getText().toString());
        map.put("Genero", Nombre.getText().toString());
        if(GeneroH.isChecked()){
            map.put("Genero", "Hombre");
        } else {
            map.put("Genero", "Mujer");
        }
        map.put("Peso", Peso.getText().toString());
        map.put("Estatura", Estatura.getText().toString());
        map.put("Telefono", Telefono.getText().toString());
        map.put("Email", Email.getText().toString());
        map.put("Usuario", Usuario.getText().toString());
        map.put("Pass", Pass.getText().toString());
        map.put("Metodo", "");
        map.put("Tarjeta", "");
        map.put("PagoActivo", "false");
        map.put("DemoTerminado", "false");
        if(intentIns.getBooleanExtra("DemoDay", false)){
            map.put("DemoActivo", "true");
        } else{
            map.put("DemoActivo", "false");
        }


        //map.put("ConfirmPass", ConfirmPass.getText().toString());

        //System.out.println(usuvalues.toString());
        //JSONArray jsonArray = new JSONArray(usuvalues);
        JSONObject jsonUsuario = new JSONObject(map);
        System.out.println(jsonUsuario.toString());

        try {
            int arrayIndex = jArrayUsuarios.length();
            jArrayUsuarios.put(arrayIndex, jsonUsuario);
            System.out.print("Array1" + jArrayUsuarios.toString());
            usuarios.edit().putString("BDUsuarios", jArrayUsuarios.toString()).apply();
            System.out.print("Array2" + jArrayUsuarios.toString());

            settings.edit().putString("UsuarioActivo", jsonUsuario.getString("Usuario")).apply();
            settings.edit().putString("NombreActivo", jsonUsuario.getString("Nombre")).apply();
            settings.edit().putString("GeneroActivo", jsonUsuario.getString("Genero")).apply();
            settings.edit().putString("AvatarActivo", jsonUsuario.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
