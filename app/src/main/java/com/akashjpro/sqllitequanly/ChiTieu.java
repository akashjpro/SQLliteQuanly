package com.akashjpro.sqllitequanly;

/**
 * Created by Akashjpro on 9/22/2016.
 */
public class ChiTieu {
    public Integer id;
    public String ten;
    public Integer chiPhi;
    public String ghiChu;

    public ChiTieu(Integer id, String ten, Integer chiPhi, String ghiChu) {
        this.id = id;
        this.ten = ten;
        this.chiPhi = chiPhi;
        this.ghiChu = ghiChu;
    }
}
