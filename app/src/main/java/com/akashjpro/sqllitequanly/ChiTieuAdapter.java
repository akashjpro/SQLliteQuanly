package com.akashjpro.sqllitequanly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Akashjpro on 9/22/2016.
 */
public class ChiTieuAdapter extends BaseAdapter{
    Context myContext;
    int myLayout;
    List<ChiTieu> arrayChiTieu;

    public ChiTieuAdapter(Context myContext, int myLayout, List<ChiTieu> arrayChiTieu) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrayChiTieu = arrayChiTieu;
    }

    @Override
    public int getCount() {
        return arrayChiTieu.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayChiTieu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView txtTen, txtPhi, txtGhiChu;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = view;
        ViewHolder holder = new ViewHolder();

        if(rowview==null){
            rowview = inflater.inflate(myLayout, null);
            holder.txtTen = (TextView) rowview.findViewById(R.id.ten);
            holder.txtPhi = (TextView) rowview.findViewById(R.id.chiPhi);
            holder.txtGhiChu = (TextView) rowview.findViewById(R.id.ghiChu);
            rowview.setTag(holder);
        }
        else {
            holder = (ViewHolder) rowview.getTag();
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");// định dạng tiền tệ số

        holder.txtTen.setText(arrayChiTieu.get(i).ten);
        holder.txtPhi.setText(decimalFormat.format(arrayChiTieu.get(i).chiPhi) + "Đ");
        holder.txtGhiChu.setText(arrayChiTieu.get(i).ghiChu);


        return rowview;
    }
}
