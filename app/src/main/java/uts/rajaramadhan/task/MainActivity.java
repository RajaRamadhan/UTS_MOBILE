package uts.rajaramadhan.task;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> agendaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listViewAgenda);
        agendaList = new ArrayList<>();

        // Memuat agenda dari database
        loadAgenda();

        // Tombol untuk menambah agenda baru
        Button btnAddAgenda = findViewById(R.id.btnAddAgenda);
        btnAddAgenda.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddAgendaActivity.class));
        });
    }

    private void loadAgenda() {
        Cursor cursor = null;
        try {
            cursor = dbHelper.getAllAgenda();
            agendaList.clear();

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Mengambil nilai untuk setiap kolom
                    String namaAgenda = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA_AGENDA));
                    String deskripsi = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESKRIPSI));
                    String status = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS));

                    // Menambahkan data ke dalam agendaList
                    agendaList.add(namaAgenda + " - " + deskripsi + " (" + status + ")");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Error retrieving data: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        // Mengatur adapter untuk ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, agendaList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Memuat ulang agenda saat activity kembali ke tampilan
        loadAgenda();
    }
}
