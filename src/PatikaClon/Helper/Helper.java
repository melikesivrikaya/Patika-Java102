package PatikaClon.Helper;

import javax.swing.*;
import java.awt.*;
public class Helper {

    public static int screenCennterPoint(String axis,int size){
        int point = 0 ;
        if(axis.equals("x")){
            point =  (Toolkit.getDefaultToolkit().getScreenSize().width - size ) / 2;
        }
        if(axis.equals("y")){
            point = (Toolkit.getDefaultToolkit().getScreenSize().height - size ) / 2;
        }
        return point;
    }

    public static void setLayout(){
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void msg(String message){
        String msg = "";
        String title = "Hata";
        switch (message){
            case "null":
                msg = "Lütfen tüm alanları doldurunuz !";
                title = "Hata";
                break;
            case "done" :
                msg = "İşlem başarılı.";
                title = "Sonuç";
                break;
            case "none" :
                msg = "İşlem başarısız.";
                title = "Sonuç";
                break;
            case "cancel":
                msg = " İşlem iptal edildi";
                break;
            default:
                msg = message;
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean sure(){
        return JOptionPane.showConfirmDialog(null,"Emin misin ? ","Devam etmek istiyor musunuz? ",JOptionPane.YES_NO_OPTION) == 0;
    }
}
