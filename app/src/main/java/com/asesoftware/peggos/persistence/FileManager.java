package com.asesoftware.peggos.persistence;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileManager {
    private String path;
    private String fileName;
    private Context context;

    public FileManager(Context context) {
        path = context.getFilesDir().getPath().toString();
        this.context = context;
        fileName = "Peggos.data";
    }

    public void saveData(String data) throws Exception {
        try {
            File file = obtenerArchivo();
            FileOutputStream outputStream = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void readData() throws Exception {
        try {
            FileInputStream fis;
            File file = obtenerArchivo();
            String content = "";
            fis = context.openFileInput(file.getName());
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                content += new String(input);
            }
            Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public File obtenerArchivo() throws IOException {
        File file = new File(this.path + "/" + this.fileName);
        validarExistenciaArchivo(file);
        return file;
    }

    public void validarExistenciaArchivo(File file) throws IOException {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new IOException("Problemas al crear el archivo");
        }
    }
}
