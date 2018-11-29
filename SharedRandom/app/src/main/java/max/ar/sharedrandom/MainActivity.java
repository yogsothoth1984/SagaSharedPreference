package max.ar.sharedrandom;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvPuntaje, tvAdivine, tvValor;
    private EditText etIngrese;
    private Button buttonVerificar;
    private int numeroRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vincular o Asociar
        tvPuntaje=findViewById(R.id.tvPuntaje);
        tvAdivine=findViewById(R.id.tvAdivine);
        tvValor=findViewById(R.id.tvValor);
        etIngrese=findViewById(R.id.etIngreses);
        buttonVerificar=findViewById(R.id.buttonVerificar);
        //SharedPreferences
        SharedPreferences shared=getSharedPreferences("SharedRandom", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=shared.edit();
        String v=String.valueOf(shared.getInt("Puntos",0));
        tvValor.setText(v);
        //Random
        numeroRandom = 1+(int)(Math.random()*50);

    }
    public void verificar(View vVerificar){
        Toast.makeText(this,"Comparando...",Toast.LENGTH_SHORT).show();
       int valor=Integer.parseInt(etIngrese.getText().toString());
       if (numeroRandom==valor){
            int puntoActual= Integer.parseInt(tvValor.getText().toString());
            puntoActual++;
            tvValor.setText(String.valueOf(puntoActual));
            tvAdivine.setText("Muy bien gano ahora piense otro numero");
            etIngrese.setText("");
            //SharedPreferences
           SharedPreferences preferences=getSharedPreferences("SharedRandom",Context.MODE_PRIVATE);
           SharedPreferences.Editor editor=preferences.edit();
           editor.putInt("Puntos",puntoActual);
           editor.commit();
           //Random
           numeroRandom = 1+(int)(Math.random()*50);
           Toast.makeText(this,numeroRandom,Toast.LENGTH_LONG).show();
       }else {
           if (valor > numeroRandom){
               tvAdivine.setText("Ingrese un numero mayor al que penso la maquina");
           }else {
               tvAdivine.setText("Ingrese un numero menor al que ingreso la maquina");
           }
       }
    }
}
