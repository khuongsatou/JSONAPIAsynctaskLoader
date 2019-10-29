package com.nvk.jsonapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private NguoiChoiAdapter adapter;
    private List<NguoiChoi> nguoiChois;
    private RecyclerView rcvNguoiChoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiation();
        setAdapterNguoiChoi();

        if (getSupportLoaderManager().getLoader(0) !=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        //fetch
        getSupportLoaderManager().restartLoader(0,null,this);
    }

    private void setAdapterNguoiChoi() {
        nguoiChois = new ArrayList<>();
        adapter = new NguoiChoiAdapter(this,nguoiChois);
        rcvNguoiChoi.setLayoutManager(new LinearLayoutManager(this));
        rcvNguoiChoi.setAdapter(adapter);

    }

    private void radiation() {
        rcvNguoiChoi = findViewById(R.id.rcvNguoiChoi);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new NguoiChoiLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        //data json
        JSONObject objNguoiChoi = null;

        try {
            objNguoiChoi = new JSONObject(data);
            JSONArray arrNguoiChoi =objNguoiChoi.getJSONArray("nguoi_choi");
            for (int i = 0; i < arrNguoiChoi.length(); i++) {
                JSONObject objItemNguoiChoi = arrNguoiChoi.getJSONObject(i);
                String tenDangNhap = objItemNguoiChoi.getString("ten_dang_nhap");
                String email = objItemNguoiChoi.getString("email");
                NguoiChoi nguoiChoi = new NguoiChoi();
                nguoiChoi.setTenTaiKhoan(tenDangNhap);
                nguoiChoi.setEmail(email);
                nguoiChois.add(nguoiChoi);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
