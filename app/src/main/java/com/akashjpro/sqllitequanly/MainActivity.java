package com.akashjpro.sqllitequanly;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvChiTieu;
    ArrayList<ChiTieu> mangChiTieu = new ArrayList<>();
    EditText edtTen, edtPhi, edtGhiChu;
    Button btnThem, btnSua, btnXoa;
    SQLite db;
    ChiTieuAdapter adapter;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();

        adapter = new ChiTieuAdapter(MainActivity.this, R.layout.item, mangChiTieu);
        lvChiTieu.setAdapter(adapter);
        db = new SQLite(this, "QuanLy.sqlite", null, 1);

        //tao bang
        db.queryData("CREATE TABLE IF NOT EXISTS ChiTieu(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR, ChiPhi INTEGER, GhiChu VARCHAR )");

       // THEM DU LIEU
        db.queryData("INSERT INTO ChiTieu VALUES(null, 'Mua Dien Thoai1', '5000000', 'SamSung1')");

        loadDaTa();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTen.getText().toString();
                String ghichu = edtGhiChu.getText().toString();
                int chiphi = Integer.parseInt(edtPhi.getText().toString());
                db.queryData("INSERT INTO ChiTieu VALUES(null, ' " + ten + " ', ' " + chiphi + " ', ' " + ghichu + " ')");
                Toast.makeText(MainActivity.this, "Đã Thêm", Toast.LENGTH_SHORT).show();

                loadDaTa();
                clearEdt();
            }

        });

        lvChiTieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtTen.setText(mangChiTieu.get(i).ten);
                edtPhi.setText(mangChiTieu.get(i).chiPhi + "");
                edtGhiChu.setText(mangChiTieu.get(i).ghiChu);

                id = mangChiTieu.get(i).id;
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTen.getText().toString();
                String ghichu = edtGhiChu.getText().toString();
                int chiphi = Integer.parseInt(edtPhi.getText().toString());
                db.queryData("UPDATE ChiTieu SET Ten = ' " + ten +" ', ChiPhi = ' "+ chiphi +" ', GhiChu = ' "+ ghichu +" ' WHERE Id = ' "+ id +" ' ");
                Toast.makeText(MainActivity.this, "Đã sửa", Toast.LENGTH_SHORT).show();
                loadDaTa();
                clearEdt();
            }
        });

        lvChiTieu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogXacNhanXoa(mangChiTieu.get(id).ten);
                id = mangChiTieu.get(i).id;
                return false;
            }
        });
    }

    private void DialogXacNhanXoa(String noidung){
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setTitle("Xac nhan xoa");
        alBuilder.setIcon(android.R.drawable.ic_delete);
        alBuilder.setMessage("Ban co muon xoa khong" + noidung + "khong");
        alBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.queryData("DELETE FROM ChiTieu WHERE id = '"+ id +"' ");
                Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                loadDaTa();
            }
        });
        alBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alBuilder.show();
    }

    private void loadDaTa() {
        mangChiTieu.clear();

        Cursor cursorCT = db.getData("SELECT * FROM ChiTieu");
        while (cursorCT.moveToNext()){
            mangChiTieu.add(new ChiTieu(
                    cursorCT.getInt(0),
                    cursorCT.getString(1),
                    cursorCT.getInt(2),
                    cursorCT.getString(3)

            ));

            adapter.notifyDataSetChanged();
        }

    }

    private void addControls() {

        edtTen = (EditText) findViewById(R.id.editTextTen);
        edtPhi = (EditText) findViewById(R.id.editTextChiPhi);
        edtGhiChu = (EditText) findViewById(R.id.editTextGhiChu);

        btnThem = (Button) findViewById(R.id.buttonThêm);
        btnSua = (Button) findViewById(R.id.buttonSua);
        btnXoa = (Button) findViewById(R.id.buttonXoa);
        lvChiTieu = (ListView) findViewById(R.id.listViewChiTieu);

    }

    private void  clearEdt(){
        edtTen.setText("");
        edtPhi.setText("");
        edtGhiChu.setText("");
    }


}
