package com.example.semana6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Orden;

public class MainActivity extends AppCompatActivity {
    private Button buttonUp, buttonDown, buttonRight, buttonLeft, buttonColor;
    private Socket socketcito;
    private BufferedWriter escritorcito;
    private BufferedReader lectorcito;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonDown = findViewById(R.id.buttonDown);
        buttonRight = findViewById(R.id.buttonRight);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonColor = findViewById(R.id.buttonColor);
        buttonUp = findViewById(R.id.buttonUp);
        initClient();

        buttonUp.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Orden obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "UP";
                            isactive= true;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "UP";
                            isactive= false;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
        buttonDown.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Orden obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "DOWN";
                            isactive= true;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "DOWN";
                            isactive= false;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
        buttonLeft.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Orden obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "LEFT";
                            isactive= true;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "LEFT";
                            isactive= false;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
        buttonRight.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Orden obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "RIGHT";
                            isactive= true;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "RIGHT";
                            isactive= false;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
        buttonColor.setOnTouchListener(
                (view,event)->{
                    Gson gson = new Gson();
                    String key;
                    Boolean isactive;
                    Orden obj;
                    String json;
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            gson = new Gson();
                            key = "COLOR";
                            isactive= true;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                        case MotionEvent.ACTION_UP:
                            gson = new Gson();
                            key = "COLOR";
                            isactive= false;
                            obj = new Orden(key,isactive);
                            json = gson.toJson(obj);
                            sendMessage(json);
                            break;
                    }
                    return true;
                }
        );
    }

    public void initClient() {
        new Thread(
                ()->{
                    try {
                        //Paso 2: Enviar solicitud de conexion
                        socketcito = new Socket("192.168.1.16",2021);
                        //Paso 3: Cliente y server conectados
                        System.out.println("Se ha conectado al servidor!!!");

                        InputStream is = socketcito.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        lectorcito = new BufferedReader(isr);

                        OutputStream os = socketcito.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        escritorcito = new BufferedWriter(osw);

                        while(true) {
                            System.out.println("Esperando mensaje....");
                            String line = lectorcito.readLine();
                            System.out.println("Recibido: " + line);


                        }

                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

        ).start();
    }
    public void sendMessage(String msg) {
        new Thread(
                ()->{
                    try {
                        escritorcito.write(msg+"\n");
                        escritorcito.flush();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }
}