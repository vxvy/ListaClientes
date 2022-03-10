package com.example.listaclientes.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.listaclientes.R;
import com.example.listaclientes.databinding.ActivityMainBinding;
import com.example.listaclientes.pojos_gson.Cliente;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RvAdapterClients rvAdapterClients;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        checkPermission();
        setUpComponents();
    }

    private final int REQUEST_PERMISSION_PHONE_STATE=1;

    private void checkPermission() {
        int permissionCheck =
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.CALL_PHONE)) {
                showExplanation("Permission Needed", getResources().getString(R.string.alert_call_perm), Manifest.permission.CALL_PHONE, REQUEST_PERMISSION_PHONE_STATE);
            } else {
                requestPermission(Manifest.permission.CALL_PHONE, REQUEST_PERMISSION_PHONE_STATE);
            }
        } else {
            Toast.makeText(MainActivity.this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[],int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_PHONE_STATE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showExplanation(String title, String message, final String permission, final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }

    /**
     * Loads the data from the assets in the RecyclerView
     */
    private void setUpComponents() {
        activityMainBinding.rvClients.setLayoutManager(new LinearLayoutManager(this));

        List<Cliente> clientList = new ArrayList<>();
        clientList.add(new Cliente("gregorio", "123456789", "qwerty@gmail.com", false));
        clientList.add(new Cliente("aaaaaa", "777555222", "qwerty@gmail.com", false));
        clientList.add(new Cliente("sssssssss", "234012098", "qwerty@gmail.com", true));
        clientList.add(new Cliente("3333", "0982345765", "qwerty@gmail.com", true));
        clientList.add(new Cliente("qwqw", "123678123", "qwerty@gmail.com", false));
        clientList.add(new Cliente("zxc", "4567892134", "qwerty@gmail.com", false));
        clientList.add(new Cliente("12412", "111222333", "qwerty@gmail.com", false));
        clientList.add(new Cliente("qqqqqqqqq", "123098765", "qwerty@gmail.com", true));
        rvAdapterClients = new RvAdapterClients(clientList, this);

        activityMainBinding.rvClients.setAdapter(rvAdapterClients);
    }


}