package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class danhSachNhanVien {
    // Nắm giữ danh sách sinh viên nhập từ người dùng
    private ArrayList<nhanVien> ds = new ArrayList<>();
    
    // Lưu và mở file
    private String path = "danhSachNhanVien.dat";
    
    public void saveFile() throws Exception{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
            
        oos.writeObject(ds);
        oos.close();
        fos.close();
    }
    public void openFile() throws Exception{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        File file = new File(path);
        // Kiểm tra file có tồn tại, nếu mà tồn tại 
        if(file.exists()){ // đọc dữ liệu từ file
            ds = (ArrayList<nhanVien>) ois.readObject();
        }else{ // còn ko thì khởi tạo giá trị ban đầu cho file
            initDataTable();
        }
        ois.close();
        fis.close();
    }
    public void initDataTable() {
        ds.add(new nhanVien("NV01", "Trương Vũ Tấn Sang", "10/3/2002",
                19, "sang@gmail.com", 10000000));
        ds.add(new nhanVien("NV02", "Lê Thị T", "1/1/1997",
                24, "lethit@gmail.com", 6500000));
        ds.add(new nhanVien("NV03", "Lê Văn K", "2/5/2001",
                20, "levank@gmail.com", 6000000));
    }
    
    // Xét vị trí trên danh sách 
    private int vitri = -1;
    public String getStatus(){
        return "Bản ghi: "+ (vitri+1)+ " / "+ ds.size();
    }
    
    // trả về nhân viên hiện tại trong danh sách
    public nhanVien viTriNhanVien(){
        if(ds.size() == 0)return null;
        return ds.get(vitri);
    }
    //trả về vị trí index của nhân viên trong status
    public int viTriIndex(nhanVien nv){
        return vitri = ds.indexOf(nv);
    }
     
    public void first(){
        vitri = 0;
    }
    public void prev(){
        if(vitri > 0) vitri -- ;
    }
    public void next(){
        if(vitri < ds.size() - 1) vitri ++;
    }
    public void last(){
        vitri = ds.size()-1;
    }
    
    
    public void addNV(nhanVien nv){
        ds.add(nv);
    }
    public nhanVien findById(String Id){
        for(nhanVien nv : ds){
            if(nv.getId().equals(Id)){
                return nv;
            }
        }
        return null;
    }

    public boolean deleteById(String Id){
        for(nhanVien nv : ds){
            if(nv.getId().equals(Id)){
                ds.remove(nv);
                return true;
            }
        }
        return false;
    }
    public void update(nhanVien nv){
        nhanVien id = findById(nv.getId());
        if(id != null){
            id.setName(nv.getName());
            id.setBirthday(nv.getBirthday());
            id.setAge(nv.getAge());
            id.setEmail(nv.getEmail());
            id.setSalary(nv.getSalary());
        }
    }
    // chuyển đổi lương ra hệ cơ số Decimal thay vì .E
    public DecimalFormat setCurrency(){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("##,###", dfs);
        return df;
    }
    
    public void showTable(DefaultTableModel tblModel){// Lấy mô hình dữ liệu của bảng
        tblModel.setRowCount(0);    // và xóa sạch các hàng
        DecimalFormat df = setCurrency(); // chuyển đổi lương
        // duyệt List nhân viên và bổ sung các nhân viên vào bảng
        for(nhanVien nv : ds){
            tblModel.addRow(new Object[]{
                nv.getId(), nv.getName(), nv.getBirthday(), nv.getAge(), 
                nv.getEmail(), df.format(nv.getSalary())
            });
        }
        tblModel.fireTableDataChanged();// cập nhật thay đổi bảng
    }
    
    // Sắp xếp lương
    public void collectionSalary(){
        Collections.sort(ds, (a,b) -> (int)(a.getSalary() - b.getSalary()));
    }
    // Sắp xếp id nhân viên
    public void collectionID(){
        Collections.sort(ds, new Comparator<nhanVien>() {
            @Override
            // Nếu o1>o2 retun 1, o1<02 return -1, o1 = o2 returny 0
            public int compare(nhanVien o1, nhanVien o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
    }
    //  nhập ngày sinh và tìm tuổi kể từ ngày sinh
    private static int year, month, date;
    public int tinhTuoi(JTextField field) throws ParseException{
        String strDayOfBirth = field.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse(strDayOfBirth);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        date = c.get(Calendar.DATE);
        
        LocalDate dob = LocalDate.of(year, month, date);
        LocalDate now1 = LocalDate.now();
        
        Period diff1 = Period.between(dob, now1);// tìm tuổi từ ngày sinh
        
        return diff1.getYears();
    }
    
}