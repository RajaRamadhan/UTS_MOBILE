package uts.rajaramadhan.task;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class AddAgendaActivity extends AppCompatActivity {
    private EditText etNamaAgenda, etDeskripsi;
    private Spinner spinnerStatus;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);

        etNamaAgenda = findViewById(R.id.etNamaAgenda);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        Button btnSimpan = findViewById(R.id.btnSimpan);
        dbHelper = new DatabaseHelper(this);

        // Mengisi spinner dengan data
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaAgenda = etNamaAgenda.getText().toString().trim();
                String deskripsi = etDeskripsi.getText().toString().trim();
                String status = spinnerStatus.getSelectedItem().toString(); // Pastikan ini mengambil status dengan benar

                if (dbHelper.addAgenda(namaAgenda, deskripsi, status)) {
                    // Berhasil menambahkan agenda
                    finish(); // Kembali ke MainActivity
                } else {
                    // Gagal menambahkan agenda
                    // Tampilkan pesan kesalahan
                }
            }
        });
    }

}
