package com.lima.douglas.apptabuadamultiplicar;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lima.douglas.apptabuadamultiplicar.repository.RecordesRepository;

import java.util.Random;


public class DesafioMedioActivity extends AppCompatActivity {

    ActionBar actionBar;
    TextView txtTime;
    TextView txtPadrao;
    TextView txtAlternar;
    Random random;
    int novoNumero = 0, antigoNumero[] = {0, 0, 0, 0, 0}, antigoNumero2[] = {0, 0, 0, 0, 0}, placar = 0;
    boolean verificarRepetidos = true;
    int multInicial;
    int contador = 60;
    int pontuacao = 0;
    AlertDialog dialog;
    Intent i;
    RecordesRepository repository;
    SQLiteDatabase bd;
    ContentValues values;
    Thread thread;
    boolean sairThread = false;
    Handler handler;
    Button um;
    Button dois;
    Button tres;
    Button quatro;
    int arrayTag, resultado, resultadoErrado;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desafio_medio_activity);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Desafio Médio");
        actionBar.setDisplayHomeAsUpEnabled(true);
        // instanciando view.
        txtAlternar = (TextView) findViewById(R.id.txtAlternar);
        txtPadrao = (TextView) findViewById(R.id.txtPadrao);
        txtTime = (TextView) findViewById(R.id.txtTime);
        random = new Random();
        repository = new RecordesRepository(this);
        handler = new Handler();
        um = (Button) findViewById(R.id.btnUm);
        dois = (Button) findViewById(R.id.btnDois);
        tres = (Button) findViewById(R.id.btnTres);
        quatro = (Button) findViewById(R.id.btnQuatro);


        //inserindo um valor no txtAlternar para ele começar com numeros diferentes.
        do {
            multInicial = random.nextInt(6);
        } while (multInicial < 0);
        antigoNumero[0] = multInicial;
        txtPadrao.setText(String.valueOf(multInicial));

        // inserindo um valor no txtAlternar para ele começar com numeros diferentes.
        do {
            multInicial = random.nextInt(11);
        } while (multInicial < 0);
        antigoNumero2[0] = multInicial;
        txtAlternar.setText(String.valueOf(multInicial));

        contagem();
        gerarTagsBotao();
    }


    // gerando nova tag.
    public void gerarTagsBotao() {

        arrayTag = random.nextInt(4);
        resultado = (Integer.valueOf(txtPadrao.getText().toString())) * (Integer.valueOf(txtAlternar.getText().toString()));


        if (arrayTag == 0) {
            um.setText(String.valueOf(resultado));
            um.setTag(String.valueOf(resultado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == resultado);
            dois.setText(String.valueOf(resultadoErrado));
            dois.setTag(String.valueOf(resultadoErrado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == Integer.valueOf(um.getText().toString()) || resultadoErrado == Integer.valueOf(dois.getText().toString()));
            tres.setText(String.valueOf(resultadoErrado));
            tres.setTag(String.valueOf(resultadoErrado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == Integer.valueOf(um.getText().toString()) || resultadoErrado == Integer.valueOf(dois.getText().toString()) || resultadoErrado == Integer.valueOf(tres.getText().toString()));
            quatro.setText(String.valueOf(resultadoErrado));
            quatro.setTag(String.valueOf(resultadoErrado));
        } else if (arrayTag == 1) {
            dois.setText(String.valueOf(resultado));
            dois.setTag(String.valueOf(resultado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == resultado);
            um.setText(String.valueOf(resultadoErrado));
            um.setTag(String.valueOf(resultadoErrado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == Integer.valueOf(um.getText().toString()) || resultadoErrado == Integer.valueOf(dois.getText().toString()));
            tres.setText(String.valueOf(resultadoErrado));
            tres.setTag(String.valueOf(resultadoErrado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == Integer.valueOf(um.getText().toString()) || resultadoErrado == Integer.valueOf(dois.getText().toString()) || resultadoErrado == Integer.valueOf(tres.getText().toString()));
            quatro.setText(String.valueOf(resultadoErrado));
            quatro.setTag(String.valueOf(resultadoErrado));

        } else if(arrayTag == 2) {
            tres.setText(String.valueOf(resultado));
            tres.setTag(String.valueOf(resultado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == resultado);
            um.setText(String.valueOf(resultadoErrado));
            um.setTag(String.valueOf(resultadoErrado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == Integer.valueOf(um.getText().toString()) || resultadoErrado == Integer.valueOf(tres.getText().toString()));
            dois.setText(String.valueOf(resultadoErrado));
            dois.setTag(String.valueOf(resultadoErrado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == Integer.valueOf(um.getText().toString()) || resultadoErrado == Integer.valueOf(dois.getText().toString()) || resultadoErrado == Integer.valueOf(tres.getText().toString()));
            quatro.setText(String.valueOf(resultadoErrado));
            quatro.setTag(String.valueOf(resultadoErrado));
        } else if(arrayTag == 3) {
            quatro.setText(String.valueOf(resultado));
            quatro.setTag(String.valueOf(resultado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == resultado);
            um.setText(String.valueOf(resultadoErrado));
            um.setTag(String.valueOf(resultadoErrado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == Integer.valueOf(um.getText().toString()) || resultadoErrado == Integer.valueOf(quatro.getText().toString()));
            dois.setText(String.valueOf(resultadoErrado));
            dois.setTag(String.valueOf(resultadoErrado));
            do {
                resultadoErrado = random.nextInt(((resultado + 5) - (resultado - 5)) + 1) + (resultado - 5);
            } while (resultadoErrado < 0 || resultadoErrado == Integer.valueOf(um.getText().toString()) || resultadoErrado == Integer.valueOf(dois.getText().toString()) || resultadoErrado == Integer.valueOf(quatro.getText().toString()));
            tres.setText(String.valueOf(resultadoErrado));
            tres.setTag(String.valueOf(resultadoErrado));
        }

    }


    // verificando a tag do botão que o usuario criou.
    public void respostaUsuario(View view) {

        if (Integer.valueOf(view.getTag().toString()) == (Integer.valueOf(txtPadrao.getText().toString())) * (Integer.valueOf(txtAlternar.getText().toString()))) {
            calcularPadrao();
            calcularAlterar();
            gerarTagsBotao();
        }
    }


    public void calcularPadrao() {

        verificarRepetidos = true;

        // gerando um novo valor.
        while (verificarRepetidos || novoNumero < 1) {
            // gerando novo numero
            novoNumero = random.nextInt(10) + 1;
            // verificando se o novo numero não é igual aos ultimos  cindo numeros gerados.
            // necessario novoNumero ser maior que zero, senão vai encher o vetor de numeros negativos.
            if (novoNumero != antigoNumero[0] && novoNumero >= 0) {
                if (novoNumero != antigoNumero[1]) {
                    if (novoNumero != antigoNumero[2]) {
                        if (novoNumero != antigoNumero[3]) {
                            if (novoNumero != antigoNumero[4]) {
                                for (int j = 4; j != 0; j--) { // deslocando os valores para esquerda do vetor.
                                    antigoNumero[j] = antigoNumero[j - 1];
                                }
                                antigoNumero[0] = novoNumero;
                                // caso chegue ate aqui, então não tem numeros repetidos, já pode sair do loop.
                                verificarRepetidos = false;
                            }
                        }
                    }
                }
            }
        }
        // inserindo novoNumero no textView
        txtPadrao.setText(String.valueOf(novoNumero));
    }


    public void calcularAlterar() {

        verificarRepetidos = true;
        // gerando um novo valor.

        while (verificarRepetidos || novoNumero < 0) {
            // gerando novo numero
            novoNumero = random.nextInt(11);
            // verificando se o novo numero não é igual aos ultimos  cindo numeros gerados.
            // necessario novoNumero ser maior que zero, senão vai encher o vetor de numeros negativos.
            if (novoNumero != antigoNumero2[0] && novoNumero >= 0) {
                if (novoNumero != antigoNumero2[1]) {
                    if (novoNumero != antigoNumero2[2]) {
                        if (novoNumero != antigoNumero2[3]) {
                            if (novoNumero != antigoNumero2[4]) {
                                for (int j = 4; j != 0; j--) { // deslocando os valores para esquerda do vetor.
                                    antigoNumero2[j] = antigoNumero2[j - 1];
                                }
                                antigoNumero2[0] = novoNumero;
                                // caso chegue ate aqui, então não tem numeros repetidos, já pode sair do loop.
                                verificarRepetidos = false;
                            }
                        }
                    }
                }
            }
        }

        // inserindo novoNumero no textView
        txtAlternar.setText(String.valueOf(novoNumero));
        // somando um ponto no placar.
        placar++;
    }



    public void contagem() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                for (int i = contador; i >= 0 && !sairThread; i--) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtTime.setText(String.valueOf(contador));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    contador = i;
                }

                if (!sairThread)
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            finalizarDesafio();
                        }
                    });
            }
        };

        thread = new Thread(runnable);
        thread.start();
    }


    public void finalizarDesafio() {
        pontuacao = (placar * 4) + (contador * 4);

        bd = repository.getWritableDatabase();

        values = new ContentValues();
        values.put("PONTUACAO", pontuacao);
        bd.insert("RECORDES", null, values);

        dialog = new AlertDialog.Builder(this).create();
        // necessario para que o usuario não clique fora do alert para sair.
        dialog.setCancelable(false);
        dialog.setTitle("Pontuação");
        dialog.setMessage(String.valueOf(pontuacao));

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                startActivity(getIntent());
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        // necessario para que o usuario não clique fora do alert para sair.
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    // verificando qual item foi selecionado na actionBar.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                sairThread = true;
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    // metodo sobrescreve o nativo do android.
    public void onBackPressed() {
        // ativar finalizar thread.
        sairThread = true;
        finish();
        //nem este, continua saindo de todoo o app e não para a tela anterior.
        super.onBackPressed();
    }
}
