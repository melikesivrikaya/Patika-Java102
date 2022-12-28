package PatikaClon.View;

import PatikaClon.Helper.Config;
import PatikaClon.Helper.Helper;
import PatikaClon.Model.Quiz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizAddGUI extends JFrame{
    private JPanel wrapperQuizAdd;
    private JTextField fld_quiz_ask;
    private JTextField fld_quiz_ans1;
    private JTextField fld_quiz_ans2;
    private JTextField fld_quiz_ans3;
    private JTextField fld_quiz_ans4;
    private JButton btn_quiz_add;

    public QuizAddGUI(int content_id,int user_id){
        add(wrapperQuizAdd);
        setSize(400,350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setLocation(Helper.screenCennterPoint("x",getSize().width),Helper.screenCennterPoint("y",getSize().height));


        btn_quiz_add.addActionListener(e -> {
            String ask = fld_quiz_ask.getText();
            String ans1 = fld_quiz_ans1.getText();
            String ans2 = fld_quiz_ans2.getText();
            String ans3 = fld_quiz_ans3.getText();
            String ans4 = fld_quiz_ans4.getText();
            if(ask.trim().isEmpty() || ans1.trim().isEmpty() || ans2.trim().isEmpty() || ans3.trim().isEmpty() || ans4.trim().isEmpty()){
                Helper.msg("null");
            }else {
                if(Quiz.add(ask,ans1,ans2,ans3,ans4,content_id,user_id)){
                    Helper.msg("done");
                    dispose();
                }else{
                    Helper.msg("none");
                }
            }
        });
    }
}
