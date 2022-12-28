package PatikaClon.View;

import PatikaClon.Helper.Config;
import PatikaClon.Helper.Helper;
import PatikaClon.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGUI extends JFrame{
    private JPanel panel1;
    private JPanel wrapperStdnt;
    private JLabel lbl_welcome_name;
    private JTabbedPane tabbet_quiz;
    private JPanel pnl_course;
    private JScrollPane scrl_course;
    private JTable tbl_course_list;
    private JTextField fld_course_name;
    private JButton btn_join_course;
    private JList list_patika;
    private JComboBox cmb_quiz_course;
    private JPanel pnl_quiz;
    private JButton btn_scor;
    private JButton btn_back;
    private JButton btn_next;
    private JRadioButton rd_btn1;
    private JRadioButton rd_btn2;
    private JRadioButton rd_btn3;
    private JRadioButton rd_btn4;
    private JLabel lbl_ask;
    private JButton btn_quiz_start;
    private JPanel panel_quiz;
    private JComboBox cmb_content;
    private JButton btn_pas;
    private JPanel pnl_content;
    private JScrollPane scrl_content;
    private JComboBox cmb_course_select;
    private JTable tbl_content;
    private JButton btn_content_get;
    private JLabel fld_quiz_piece;
    private JButton button_out;
    private Student student;
    private DefaultTableModel mdl_course_list;
    private DefaultListModel mdl_list_patika;
    private DefaultTableModel mdl_content_list;
    private int add = 0;
    private int dlte = 0;
    private int quizPiece = 0;
    private int quizArrayIndex = 0;
    private ArrayList<Quiz> quizArrayList;
    private int quizScorTrue = 0;
    private ButtonGroup buttonGroup;
    private String[] answer;
    private String[] answerSelected;
    public StudentGUI(User user){

        student = new Student(user);
        add(wrapperStdnt);
        setSize(800,600);
        setLocation(Helper.screenCennterPoint("x",getSize().width),Helper.screenCennterPoint("y",getSize().height));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        lbl_welcome_name.setText("Hoşgeldin : " + user.getName());

        buttonGroup = new ButtonGroup();
        buttonGroup.add(rd_btn1);
        buttonGroup.add(rd_btn2);
        buttonGroup.add(rd_btn3);
        buttonGroup.add(rd_btn4);


        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] courseColum = {"ID","Patika Adı"};
        mdl_course_list.setColumnIdentifiers(courseColum);
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(25);
        refleshCourseList();
        tbl_course_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),1).toString();
                fld_course_name.setText(name);
            }
        });
        mdl_list_patika = new DefaultListModel();
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Patikadan Ayrıl");
        jPopupMenu.add(delete);
        list_patika.setComponentPopupMenu(jPopupMenu);
        list_patika.setModel(mdl_list_patika);
        refleshPatikaList();
        refleshCourseSelect();

        delete.addActionListener(e -> {
            String patika_name = list_patika.getSelectedValue().toString();
            int id = student.getCourseIDByName(patika_name);
            if(student.deletePatika(id)){
                dlte = 1 ;
                refleshPatikaList();
                refleshComboPatika();
                refleshCourseSelect();
                Helper.msg("done");
            }
            else {
                Helper.msg("none");
            }
        });
        quizTAbleOpenClose(false);
        refleshComboPatika();

        cmb_content.addItem("İçerik");
        cmb_quiz_course.addActionListener(e -> {
            if(add == 1 || dlte == 1){
                if(add == 1){
                    add = 0;
                }
                else if(dlte == 1){
                    dlte = 0;
                }
            }else{
                refleshComboContent();
            }

        });

        mdl_content_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 3){
                    return true;
                }
                return false;
            }
        };
        Object[] contentColum = {"ID","Başlık","Açıklama","Link"};
        mdl_content_list.setColumnIdentifiers(contentColum);
        tbl_content.setModel(mdl_content_list);
        tbl_content.getTableHeader().setReorderingAllowed(false);
        tbl_content.getColumnModel().getColumn(0).setMaxWidth(25);

        tbl_content.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                refleshContentList(student.getCourseIDByName(cmb_course_select.getSelectedItem().toString()));
            }
        });


         btn_join_course.addActionListener(e -> {
            int id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString());
            if(fld_course_name.getText().isEmpty()){
                Helper.msg("null");
            }
            else if(student.joinControl(id)){
                Helper.msg("Bu Patikaya zaten katıldınız.");
            }
            else {
                if(student.addPatika(id)){
                    add = 1;
                    Helper.msg("done");
                    fld_course_name.setText(null);
                    refleshPatikaList();
                    refleshComboPatika();
                    refleshCourseSelect();

                }
                else{
                    Helper.msg("none");
                }

            }
        });

         rd_btn1.addActionListener(e -> {
             answer[quizArrayIndex] = rd_btn1.getActionCommand().toString();
             answerSelected[quizArrayIndex] = "1";
         });
         rd_btn2.addActionListener(e -> {
             answer[quizArrayIndex] = rd_btn2.getActionCommand().toString();
             answerSelected[quizArrayIndex] = "2";
         });
         rd_btn3.addActionListener(e -> {
             answer[quizArrayIndex] = rd_btn3.getActionCommand().toString();
             answerSelected[quizArrayIndex] = "3";
         });
         rd_btn4.addActionListener(e -> {
             answer[quizArrayIndex] = rd_btn4.getActionCommand().toString();
             answerSelected[quizArrayIndex] = "4";
         });



        btn_quiz_start.addActionListener(e -> {
            String patika = cmb_quiz_course.getSelectedItem().toString();
            String content = cmb_content.getSelectedItem().toString();
            if(patika.equals("Patika") || content.equals("İçerik")){
                Helper.msg("Bir pakita ve bir içerik seçmiş olmalısınız...");
            }
            else {
                quizTAbleOpenClose(true);
                btn_quiz_start.setVisible(false);
                quiz(student.getContentIDByName(content,student.getCourseIDByName(patika)));
                answer = new String[quizArrayList.size()];
                answerSelected = new String[quizArrayList.size()];
                fld_quiz_piece.setText(quizArrayIndex + 1 + " / " + quizArrayList.size());

            }
        });
        btn_scor.addActionListener(e -> {
            if(Helper.sure()){
                quizTAbleOpenClose(false);
                btn_quiz_start.setVisible(true);
                answerCalculate();
            }
            quizArrayIndex = 0;
            buttonGroup.clearSelection();
        });
        btn_next.addActionListener(e -> {
            buttonGroup.clearSelection();
            if(quizArrayIndex >= 0){
                btn_back.setVisible(true);
            }
            if((quizArrayList.size()-1) == quizArrayIndex){
                btn_next.setVisible(false);
            }
            else {
                quizArrayIndex++;
                quizPut(quizArrayList.get(quizArrayIndex));
            }
            fld_quiz_piece.setText(quizArrayIndex + 1 + " / " + quizArrayList.size());
            getAnswerSelected();
        });
        btn_back.addActionListener(e -> {
            buttonGroup.clearSelection();
            if(quizArrayIndex != quizArrayList.size()){
                btn_next.setVisible(true);
            }
            if(quizArrayIndex == 0){
                btn_back.setVisible(false);
            }
            else{
                quizArrayIndex--;
                quizPut(quizArrayList.get(quizArrayIndex));
            }
            fld_quiz_piece.setText(quizArrayIndex + 1 + " / " + quizArrayList.size());
            getAnswerSelected();
        });
        btn_content_get.addActionListener(e -> {
            int id = student.getCourseIDByName(cmb_course_select.getSelectedItem().toString());
            refleshContentList(id);
        });
        btn_pas.addActionListener(e -> {
            buttonGroup.clearSelection();
            answer[quizArrayIndex] = null;
            answerSelected[quizArrayIndex] = null;
        });
        button_out.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });
    }
    public void refleshCourseList(){
        mdl_course_list.setRowCount(0);
        for(Course c : student.getCourseList()){
            Object[] o = {c.getId(),c.getName()};
            mdl_course_list.addRow(o);
        }
    }
    public void refleshPatikaList(){
        mdl_list_patika.setSize(0);
        for(Student s : student.getPatikaList()){
            mdl_list_patika.addElement(student.getNameByCourseID(s.getCourse_id()));

        }
    }

    public void refleshComboPatika(){
        cmb_quiz_course.removeAllItems();
        cmb_quiz_course.addItem("Patika");
        for(Student s : student.getPatikaList()){
            cmb_quiz_course.addItem(student.getNameByCourseID(s.getCourse_id()));
        }
    }

    public void refleshComboContent() {
        cmb_content.removeAllItems();
        cmb_content.addItem("İçerik");
        String course_name = cmb_quiz_course.getSelectedItem().toString();
        for(Content c : student.getContentList(student.getCourseIDByName(course_name))){
            cmb_content.addItem(c.getName());
        }
    }

    public void quiz(int content_id){
        quizArrayList = student.getQuizList(content_id);
        if (quizArrayList.isEmpty()){
            quizTAbleOpenClose(false);
            btn_quiz_start.setVisible(true);
            Helper.msg("Bu konu hakkında henüz bir soru tanımlanmamıştır");
        }
        else {
            quizPiece = quizArrayList.size();
            quizPut(quizArrayList.get(quizArrayIndex));
        }

    }
    public void quizTAbleOpenClose(boolean x){
        pnl_quiz.setVisible(x);
        btn_back.setVisible(x);
        btn_next.setVisible(x);
        btn_scor.setVisible(x);
        btn_pas.setVisible(x);
        fld_quiz_piece.setVisible(x);
    }
    public boolean quizPut(Quiz quiz){
        if(quiz == null){
            return false;
        }
        else{
            lbl_ask.setText(quiz.getAsk());
            rd_btn1.setText(quiz.getAnswer1());
            rd_btn1.setActionCommand("t");
            rd_btn2.setText(quiz.getAnswer2());
            rd_btn2.setActionCommand("f");
            rd_btn3.setText(quiz.getAnswer3());
            rd_btn3.setActionCommand("f");
            rd_btn4.setText(quiz.getAnswer4());
            rd_btn4.setActionCommand("f");
        }
        return true;
    }
    public void refleshCourseSelect(){
        cmb_course_select.removeAllItems();
        for(Student s : student.getPatikaList()){
            cmb_course_select.addItem(student.getNameByCourseID(s.getCourse_id()));
        }

    }

    public void refleshContentList(int course_id){
        mdl_content_list.setRowCount(0);
        for (Content c : student.getContentList(course_id)) {
            Object[] o = {c.getId(),c.getName(),c.getComment(),c.getLink()};
            mdl_content_list.addRow(o);
        }
    }
    public void answerCalculate(){
        int t = 0 ;
        for(int i = 0 ; i < answer.length ; i++){
            if(answer[i] != null) {
                if (answer[i].equals("t")) {
                    t++;
                }
                answer[i] = null;
                answerSelected[i] = null;
            }
        }
        int scor = (100 / quizArrayList.size()) * t ;
        Helper.msg("Başarı oranınız % " + scor);
    }
    public void getAnswerSelected(){
        if(answerSelected[quizArrayIndex] != null){
            if(answerSelected[quizArrayIndex].equals("1")){
                rd_btn1.setSelected(true);
            }
            else if(answerSelected[quizArrayIndex].equals("2")){
                rd_btn2.setSelected(true);
            }
            else if(answerSelected[quizArrayIndex].equals("3")){
                rd_btn3.setSelected(true);
            }
            else if(answerSelected[quizArrayIndex].equals("4")){
                rd_btn4.setSelected(true);
            }
        }

    }


}


