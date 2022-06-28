package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class clockTime extends Thread{
    private JLabel lbl = new JLabel();

    public clockTime(JLabel lbl){
        this.lbl = lbl;
    }
    
    @Override
    public void run(){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        while (true) {            
            Date now = new Date();
            String time = sdf.format(now);
            lbl.setText(time);
            
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {break;}
        }    
    
    }
}
