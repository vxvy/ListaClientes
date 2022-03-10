package com.example.listaclientes.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String readNewsFromAsset(Context context) throws IOException {
        InputStream inputStream = context.getAssets().open("clientes.json");

        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);

        String data = new String(buffer);
        return data;
    }
}
