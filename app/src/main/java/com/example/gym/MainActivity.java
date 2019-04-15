package com.example.gym;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String NombreUsuario;
    SharedPreferences settings;
    Button Logout;
    TextView Bienvenida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getPreferences(MODE_PRIVATE);
        Bienvenida = findViewById(R.id.txtMainWelcome);

        NombreUsuario = settings.getString("NombreActivo", "");
        Bienvenida.setText("Bienvenido " + NombreUsuario);

        Logout = findViewById(R.id.btnLogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().remove("NombreActivo").apply();
                settings.edit().remove("UsuarioActivo").apply();
                settings.edit().remove("GeneroActivo").apply();
                settings.edit().remove("AvatarActivo").apply();
                Intent logout = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(logout);
                finish();
            }
        });
    }
}
