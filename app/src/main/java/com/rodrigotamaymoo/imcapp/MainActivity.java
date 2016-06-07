package com.rodrigotamaymoo.imcapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextPeso;
    private EditText mEditTextEstatura;
    protected Button mButtonCalcular;
    protected Button mButtonLimpiar;
    private TextView mTextViewImc;
    private ImageView mImageViewEstado;
    private TextView mTextViewEstado;
    protected Button mButtonTablaImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mEditTextPeso = (EditText) findViewById(R.id.edit_text_peso);
        mEditTextEstatura = (EditText) findViewById(R.id.edit_text_estatura);
        mButtonCalcular = (Button) findViewById(R.id.button_calcular);
        mButtonLimpiar = (Button) findViewById(R.id.button_limpiar);
        mTextViewImc = (TextView) findViewById(R.id.text_view_imc);
        mImageViewEstado = (ImageView) findViewById(R.id.image_view_estado);
        mTextViewEstado = (TextView) findViewById(R.id.text_view_estado_imc);
        mButtonTablaImc = (Button) findViewById(R.id.button_tabla_imc);

    }

    public void Click_ButtonImc(View v){

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        try {
            String s = mEditTextPeso.getText().toString();
            double peso = Double.parseDouble(s);
            s = mEditTextEstatura.getText().toString();
            double estatura = Double.parseDouble(s);
            double imc = peso / (estatura * estatura);
            mTextViewImc.setText(String.format("%.2f",imc));

            if (imc < 18.5) {
                mImageViewEstado.setImageResource(R.drawable.android_bajo);
            } else {
                if (imc < 25) {
                    mImageViewEstado.setImageResource(R.drawable.android_normal);
                } else {
                    if (imc < 30) {
                        mImageViewEstado.setImageResource(R.drawable.android_sobrepeso);
                    } else {
                        if (imc < 40) {
                            mImageViewEstado.setImageResource(R.drawable.android_obesidad);
                        } else {
                            mImageViewEstado.setImageResource(R.drawable.android_obesidadextrema);
                        }
                    }
                }
            }

            if (imc < 18.5) {
                mTextViewEstado.setText("Bajo");
            } else {
                if (imc < 25) {
                    mTextViewEstado.setText("Normal");
                } else {
                    if (imc < 30) {
                        mTextViewEstado.setText("Sobrepeso");
                    } else {
                        if (imc < 40) {
                            mTextViewEstado.setText("Obesidad");
                        } else {
                            mTextViewEstado.setText("Obesidad Extrema");
                        }
                    }
                }
            }


        } catch (NumberFormatException W) {

        }
    }

    public void Click_ButtonLimpiar(View v){
        mEditTextPeso.setText("");
        mEditTextEstatura.setText("");
        mTextViewImc.setText("0.0");
        mTextViewEstado.setText("");
        mImageViewEstado.setImageDrawable(null);
    }

    public void Click_ButtonTabla(View v){
        Intent i = new Intent(this, Tabla2Activity.class);
        startActivity(i);
    }

}
