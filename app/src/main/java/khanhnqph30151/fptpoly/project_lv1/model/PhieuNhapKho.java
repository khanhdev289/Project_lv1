package khanhnqph30151.fptpoly.project_lv1.model;

public class PhieuNhapKho {
    int id_pnk;
    int soluong;
    int id_sp;
    int id_tv;
    String ngayXuat;
    public int getId_tv() {
        return id_tv;
    }

    public void setId_tv(int id_tv) {
        this.id_tv = id_tv;
    }


    public int getId_pnk() {
        return id_pnk;
    }

    public void setId_pnk(int id_pnk) {
        this.id_pnk = id_pnk;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }
}
