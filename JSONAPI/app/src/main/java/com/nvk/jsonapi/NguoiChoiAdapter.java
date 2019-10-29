package com.nvk.jsonapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NguoiChoiAdapter extends RecyclerView.Adapter<NguoiChoiAdapter.NguoiChoiHolder> {
    private Context context;
    private List<NguoiChoi> nguoiChois;


    public NguoiChoiAdapter(Context context, List<NguoiChoi> nguoiChois) {
        this.context = context;
        this.nguoiChois = nguoiChois;
    }

    @NonNull
    @Override
    public NguoiChoiAdapter.NguoiChoiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_nguoi_choi,parent,false);
        return new NguoiChoiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiChoiAdapter.NguoiChoiHolder holder, int position) {
        NguoiChoi nguoiChoi = nguoiChois.get(position);
        holder.tvItemTenDangNhap.setText(nguoiChoi.getTenTaiKhoan());
        holder.tvItemEmail.setText(nguoiChoi.getEmail());
    }


    @Override
    public int getItemCount() {
        return nguoiChois.size();
    }

    public class NguoiChoiHolder extends RecyclerView.ViewHolder {
        private TextView tvItemTenDangNhap,tvItemEmail;
        public NguoiChoiHolder(@NonNull View itemView) {
            super(itemView);

            tvItemTenDangNhap = itemView.findViewById(R.id.tvItemTenDangNhap);
            tvItemEmail = itemView.findViewById(R.id.tvItemEmail);
        }
    }
}
