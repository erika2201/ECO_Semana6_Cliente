package com.example.semana6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private Button buttonUp, buttonDown, buttonRight, buttonLeft, buttonColor;

    private Socket socketcito;
    private BufferedWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUp = findViewById(R.id.buttonUp);
        buttonDown = findViewById(R.id.buttonDown);
        buttonRight = findViewById(R.id.buttonRight);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonColor = findViewById(R.id.buttonColor);
    }


    public void initClient() {
        new Thread(() -> {
            try {
                socketcito = new Socket("192.168.0.32", 6969);
                System.out.println("Se ha conectado al servidor!!!");

                OutputStream os = socketcito.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                writer = new BufferedWriter(osw);

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ).start();
    }


    public void bottons() {
        buttonUp.setOnClickListener(
                (v)->{
                    new Thread(
                            ()->{
                                try {
                                    writer.write("left\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    writer.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                    ).start();
                });

        buttonDown.setOnClickListener(
                (v)->{
                    //Mensaje para que cambie coodenadas de la bolita
                });

        buttonRight.setOnClickListener(
                (v)->{
                    //Mensaje para que cambie coodenadas de la bolita
                });

        buttonLeft.setOnClickListener(
                (v)->{
                    //Mensaje para que cambie coodenadas de la bolita
                });

        buttonColor.setOnClickListener(
                (v)->{
                    //Mensaje para que cambie color
                });
    }
}