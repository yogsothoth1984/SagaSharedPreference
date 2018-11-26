package max.ar.sharedpreferencebasico;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edIngreses;
    private Button buEjecutar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Referencia
        edIngreses=findViewById(R.id.editIngrese);
        buEjecutar=findViewById(R.id.bEjecutar);
        //Shared Preferences
        /**Obtenemos una referencia de un objeto de la clase SharedPreferences a través del método
         *  getSharedPreferences.*/
        /**El primer parámetro es el nombre del archivo de preferencias y el segundo la forma de creación del archivo
         *  (MODE_PRIVATE indica que solo esta aplicación puede consultar el archivo XML que se crea)*/
        SharedPreferences preferencias=getSharedPreferences("Datos", Context.MODE_PRIVATE);
        /**Para extraer los datos del archivo de preferencias debemos indicar el nombre a extraer y un valor de retorno
         * si dicho nombre no existe en el archivo de preferencias
         *(en nuestro ejemplo la primera vez que se ejecute nuestro programa como es lógico no existe el archivo
         * de preferencias lo que hace que Android lo cree, si tratamos de extraer el valor de Mail retornará el segundo parámetro es decir el String con una cadena vacía */
        edIngreses.setText(preferencias.getString("Mail",""));

    }
    public void ejecutar(View v){
        /**Cuando se presiona el botón "ejecutar" lo que hacemos es grabar en el archivo de preferencias
         *el contenido del EditText en una variable llamada "Mail" */
        /**Se crea el objeto SharedPreferences */
        SharedPreferences compartidas=getSharedPreferences("Datos",Context.MODE_PRIVATE);
        /**Debemos crear un objeto de la clase Editor y obtener la referencia del objeto de la clase SharedPreferences*/
        SharedPreferences.Editor shareEditor=compartidas.edit();
        shareEditor.putString("Mail",edIngreses.getText().toString());
        /**Mediante el método putString almacenamos en Mail el valor del String cargado en el EditText.*/
        shareEditor.commit();
        /**Luego debemos llamar al método commit de la clase Editor para que el dato quede almacenado en forma permanente
         * en el archivo de preferencias. Esto hace que cuando volvamos a arrancar la aplicación se recupere
         *el último Mail ingresado.*/
        finish();
        /**el método finish de la clase Activity finaliza la actividad actual */
    }
}
