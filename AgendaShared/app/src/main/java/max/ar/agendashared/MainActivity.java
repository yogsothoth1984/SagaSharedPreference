package max.ar.agendashared;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editNombre, editDatos;
    private Button botGrabar, botRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vinculos
        editNombre=findViewById(R.id.edNombre);
        editDatos=findViewById(R.id.edDatos);
        botGrabar=findViewById(R.id.buttGrabar);
        botRecuperar=findViewById(R.id.buttRecuperar);
    }
    public void grabar(View vGrabar){
        String nombre=editNombre.getText().toString();
        String datos=editDatos.getText().toString();
        SharedPreferences ObjShaPref= getSharedPreferences("AgendaShaPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorShaPref= ObjShaPref.edit();
        editorShaPref.putString(nombre,datos);
        editorShaPref.commit();
        Toast.makeText(this,"Datos Grabados...",Toast.LENGTH_LONG).show();
        editNombre.setText("");
        editDatos.setText("");
    }
    public void recuperar(View vRecuperar){
        String nombre=editNombre.getText().toString();
        SharedPreferences prefencias=getSharedPreferences("AgendaShaPref",Context.MODE_PRIVATE);
        String d=prefencias.getString(nombre,"");
        if (d.length()==0){
            Toast.makeText(this,"No existe dicho nombre en la agenda...",Toast.LENGTH_LONG).show();
        } else { editDatos.setText(d);
                Toast.makeText(this,"Datos recuperados...",Toast.LENGTH_LONG).show();}
    }
}
