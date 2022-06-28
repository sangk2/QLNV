package test;

import java.awt.Color;
import javax.swing.JTextField;

public class kiemTra {
    public static void Start(JTextField id, JTextField name, JTextField birthday, JTextField age, JTextField email, JTextField salary){
        id.setBackground(Color.white);
        name.setBackground(Color.white);
        birthday.setBackground(Color.white);
        age.setBackground(Color.white);
        email.setBackground(Color.white);
        salary.setBackground(Color.white);
        
    }
    
    public static boolean checkEmpty(JTextField field, StringBuilder sb, String msg){
        boolean check = true;
        if(field.getText().equals("")){
            sb.append(msg).append("\n");
            field.setBackground(Color.yellow);
            check = false;
        }else{
            field.setBackground(Color.white);
        }
        return check;
    }
    public static boolean checkDate(JTextField field, StringBuilder sb){
        boolean check = true;
        if(!checkEmpty(field, sb, "Bạn chưa nhập Ngày Sinh nhân viên. \n"))
            return false;
        if(!field.getText().matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$")){
            sb.append("Ngày sinh không hợp lệ (Ngày/Tháng/Năm).\n");
            field.setBackground(Color.yellow);
            check = false;
        }
        if(check) field.setBackground(Color.white);
        return check;
    }
    public static boolean checkAge(JTextField field, StringBuilder sb){
        boolean check = true;
        if(!checkEmpty(field, sb, "Bạn chưa nhập tuổi nhân viên. \n"))
            return false;
        try {
            int age = Integer.parseInt(field.getText());
            if(age < 16 || age > 55){
                sb.append("Tuổi phải từ 16 đến 55. \n");
                field.setBackground(Color.yellow);
                check = false;
            }
        } catch (Exception e) {
            sb.append("Bạn cần nhập giá trị số. \n");
            field.setBackground(Color.yellow);
            check = false;
        }
        if(check)   field.setBackground(Color.white);
        return check;

    }
    public static boolean checkSalary(JTextField field, StringBuilder sb){
        boolean check = true;
        if(!checkEmpty(field, sb, "Bạn chưa nhập lương nhân viên. \n"))
            return false;
        try {
    // chuyển đổi theo đúng định dạng 100.000 để không bị bắt lỗi là kiểu String
            String luong = field.getText().replaceAll("\\.", "");
            double salary = Double.parseDouble(luong);
            if(salary <= 5000000){
                sb.append("Lương phải trên 5 triệu. \n");
                field.setBackground(Color.yellow);
                check = false;
            }
        } catch (Exception e){
            sb.append("Bạn cần nhập giá trị số. \n");
            field.setBackground(Color.yellow);
            check = false;
        }
        if(check) field.setBackground(Color.white);
        return check;
    }
    public static boolean checkEmail(JTextField field, StringBuilder sb){
        boolean check = true;
        if(!checkEmpty(field, sb, "Bạn chưa nhập Email nhân viên. \n"))
            return false;
        
        if(!field.getText().matches("\\w+@\\w+\\.\\w+")){
            sb.append("Email không hợp lệ. \n");
            field.setBackground(Color.yellow);
            check = false;
        }
        
        if(check) field.setBackground(Color.white);
        return check;
    }
}
