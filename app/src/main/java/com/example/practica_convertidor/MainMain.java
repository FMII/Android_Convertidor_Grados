package com.example.practica_convertidor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Modals.Farenheit;
import Modals.Kelvin;
import Modals.Celsius;

public class MainMain extends AppCompatActivity {
    private Celsius valorCelsius;
    private Kelvin valorKelvin;
    private Farenheit valorFahrenheit;
    private EditText editTextKelvin;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        // Enlaza elementos de la interfaz con variables en Java
        editTextKelvin = findViewById(R.id.celsc);
        Spinner spinnerOpciones = findViewById(R.id.spinnerOpciones);
        textViewResultado = findViewById(R.id.farc);

        valorCelsius = new Celsius(0.0);
        valorFahrenheit = new Farenheit(0.0);
        valorKelvin = new Kelvin(0.0);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.opciones_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOpciones.setAdapter(adapter);

        spinnerOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Acciones a realizar cuando se selecciona un elemento
                String selectedItem = parentView.getItemAtPosition(position).toString();
                Toast.makeText(MainMain.this, "Seleccionado: " + selectedItem, Toast.LENGTH_SHORT).show();

                try {
                    // Intenta obtener el valor del EditText y convertirlo a double
                    String kelvinInput = editTextKelvin.getText().toString();
                    if (!kelvinInput.isEmpty()) {
                        double kelvinValue = Double.parseDouble(kelvinInput);

                        switch (position) {
                            case 0:
                                // De Kelvin a Celsius
                                Celsius resultCelsiusK = valorCelsius.parse(new Kelvin(kelvinValue));
                                textViewResultado.setText("Resultado en Celsius: " + resultCelsiusK.getValor());
                                break;
                            case 1:
                                Celsius resultCelsiusF = valorCelsius.parse(new Farenheit(kelvinValue));
                                textViewResultado.setText("Resultado en Celsius: " + resultCelsiusF.getValor());
                                break;
                            case 2:
                                Farenheit resultFarC = valorFahrenheit.parse(new Celsius(kelvinValue));
                                textViewResultado.setText("Resultado en Fahrenheit: " + resultFarC.getValor());
                                break;
                            case 3:
                                Farenheit resultFarK = valorFahrenheit.parse(new Kelvin(kelvinValue));
                                textViewResultado.setText("Resultado en Fahrenheit: " + resultFarK.getValor());
                                break;
                            case 4:
                                Kelvin resultKelvC = valorKelvin.parse(new Celsius(kelvinValue));
                                textViewResultado.setText("Resultado en Kelvin: " + resultKelvC.getValor());
                                break;
                            case 5:
                                Kelvin resultKelvF = valorKelvin.parse(new Farenheit(kelvinValue));
                                textViewResultado.setText("Resultado en Kelvin: " + resultKelvF.getValor());
                                break;
                        }
                    } else {
                        Toast.makeText(MainMain.this, "Ingresa la cantidad de grados", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Maneja la excepción si la conversión falla (por ejemplo, si el formato no es válido)
                    Toast.makeText(MainMain.this, "Ingresa un valor válido para grados", Toast_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Acciones a realizar cuando no se ha seleccionado nada
            }
        });
    }
}
