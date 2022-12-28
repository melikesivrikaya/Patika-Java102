package PatikaClon.View;

import PatikaClon.Helper.Config;
import PatikaClon.Helper.Helper;
import PatikaClon.Helper.Item;
import PatikaClon.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class EducaterGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tabbet_course;
    private JPanel pnl_course;
    private JScrollPane scrl_course;
    private JTable tbl_course_list;
    private JPanel pnl_content;
    private JScrollPane scrl_content;
    private JTable tbl_content_list;
    private JComboBox cmb_courses;
    private JTextField fld_content_name;
    private JTextField fld_content_comment;
    private JTextField fld_content_link;
    private JButton tbn_content_add;
    private JButton btn_content_delete;
    private JLabel lbl_content;
    private JLabel lbl_content_id;
    private JLabel lbl_shr;
    private JTextField fld_course_shr;
    private JButton btn_course_shr;
    private JPanel pnl_quiz;
    private JTable tbl_quiz_list;
    private JScrollPane scrl_quiz;
    private JLabel lbl_welcome;
    private JLabel fld_content;
    private JTextField fld_shr_cntnt_name;
    private JButton btn_content_shr;
    private JComboBox cmb_shr_course;
    private JButton btn_out;
    private DefaultTableModel mdl_course_list;
    private Educater educater;
    private Content content;
    private Quiz quiz;
    private DefaultTableModel mdl_content_list;
    private User user;
    private DefaultTableModel mdl_quiz_list;

    public EducaterGUI(User user) {
        this.user = user;
        educater = new Educater(user);
        content = new Content(user);
        quiz = new Quiz(user);

        Helper.setLayout();
        add(wrapper);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.screenCennterPoint("x",getSize().width),Helper.screenCennterPoint("y",getSize().height));
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        lbl_welcome.setText("Hoşgeldin : " + user.getName());

        // Course Tabbet List
        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] courseColum = {"ID","Eğitim Adı","Dili"};
        mdl_course_list.setColumnIdentifiers(courseColum);
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(25);
        //tbl_course_list.getColumnModel().getColumn(2).setMaxWidth(100);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);

        refleshCoursList();
        //////////////////////

        // Content Tabbet List
        mdl_content_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 4 || column == 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] contentColum = { "ID","Başlık","Açıklama","Youtube Linki","Ait Olduğu Eğitim"};
        mdl_content_list.setColumnIdentifiers(contentColum);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);
        tbl_content_list.setModel(mdl_content_list);
        refleshContentList();
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(25);
        tbl_content_list.getColumnModel().getColumn(4).setMaxWidth(200);
        tbl_content_list.getColumnModel().getColumn(1).setMaxWidth(200);

        cmb_shr_course.addItem("Tümü");
        comboSherchReflesh();

        tbl_content_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                int id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0).toString());
                String name = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),1).toString();
                String comment = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),2).toString();
                String link = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),3).toString();
                if(name.trim().isEmpty() || comment.trim().isEmpty()|| link.trim().isEmpty()){
                    Helper.msg("null");
                }
                else {
                    if (content.update(id, name, comment, link)) {
                        Helper.msg("done");
                        refleshQuizList();
                    } else {
                        Helper.msg("none");
                    }
                    refleshContentList();
                }
            }

        });
        comboCourse();

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem quiz = new JMenuItem("Soru Ekle");
        popupMenu.add(quiz);
        tbl_content_list.setComponentPopupMenu(popupMenu);
        quiz.addActionListener(e -> {
            int content_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
            QuizAddGUI quizAddGUI = new QuizAddGUI(content_id, user.getId());
            quizAddGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refleshQuizList();
                }
            });
        });

        ////////////////////////////////////


        // Quiz Tabbet List
        mdl_quiz_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 || column == 6){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] quizColum = {"ID","Soru","Doğru Cevap","Yalnış Cevap","Yalnış Cevap","Yalnış Cevap","Ait olduğu İçerik"};
        mdl_quiz_list.setColumnIdentifiers(quizColum);
        tbl_quiz_list.setModel(mdl_quiz_list);
        tbl_quiz_list.getColumnModel().getColumn(0).setMaxWidth(25);
        refleshQuizList();

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Sil");
        jPopupMenu.add(delete);
        tbl_quiz_list.setComponentPopupMenu(jPopupMenu);
        delete.addActionListener(e -> {
            if(Helper.sure()){
                int id = Integer.parseInt(tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),0).toString());
                if(Quiz.delete(id)){
                    Helper.msg("done");
                    refleshQuizList();
                }
                else{
                    Helper.msg("none");
                }
            }
            else{
                Helper.msg("cancel");
            }
        });





        //////////////////////////////////

        tbn_content_add.addActionListener(e -> {
            if(fld_content_name.getText().trim().isEmpty() || fld_content_comment.getText().trim().isEmpty()|| fld_content_link.getText().trim().isEmpty()){
                Helper.msg("null");
            }else {
                String name = fld_content_name.getText();
                String comment = fld_content_comment.getText();
                String link = fld_content_link.getText();
                String course = cmb_courses.getSelectedItem().toString();
                if(content.add(name,comment,link,user.getId(),educater.getCourseName(course))){
                    Helper.msg("done");
                    refleshContentList();
                    fld_content_link.setText(null);
                    fld_content_comment.setText(null);
                    fld_content_name.setText(null);
                    fld_shr_cntnt_name.setText(null);
                    cmb_shr_course.setSelectedIndex(0);
                }
                else{
                    Helper.msg("none");
                }
            }
        });
        tbl_content_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0).toString();
                lbl_content_id.setText( id);
            }
        });
        btn_content_delete.addActionListener(e -> {
                if(Helper.sure()){
                    int id = Integer.parseInt(lbl_content_id.getText());
                    if(content.delete(id)){
                        lbl_content_id.setText(null);
                        Helper.msg("done");
                        refleshContentList();
                    }
                    else {
                        Helper.msg("none");
                    }
                }
                else {
                    Helper.msg("cancel");
                }

        });
        btn_course_shr.addActionListener(e -> {
            String shrData = fld_course_shr.getText();
            refleshCoursSherchList(shrData);
        });
        btn_content_shr.addActionListener(e -> {

                String name = fld_shr_cntnt_name.getText();
                String course = cmb_shr_course.getSelectedItem().toString();
                if(course.equals("Tümü")){
                    course = "";}
                else {
                    course = String.valueOf(educater.getCourseName(course));
                }
                refleshContentSherchList(name, course);
        });
        btn_out.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });
    }

    public void refleshCoursList(){
        mdl_course_list.setRowCount(0);
        for(Course c : educater.getList()){
            Object[] o = {c.getId(),c.getName(),c.getLang()};
            mdl_course_list.addRow(o);
        }
    }
    public void refleshContentList(){
        mdl_content_list.setRowCount(0);
        for(Content c : content.getList()){
            Object[] o = {c.getId(),c.getName(), c.getComment(), c.getLink(),educater.getCourseName(c.getCourse_id())};
            mdl_content_list.addRow(o);
        }
    }

    public void comboCourse() {
        for(Course c : educater.getList()){
            cmb_courses.addItem(new Item(c.getId(),c.getName()));
        }

    }
    public void refleshQuizList(){
        mdl_quiz_list.setRowCount(0);
        for(Quiz q : quiz.getList(user.getId())){
            Object[] o = {q.getId(),q.getAsk(),q.getAnswer1(),q.getAnswer2(),q.getAnswer3(),q.getAnswer4(),content.getContentName(q.getContent_id())};
            mdl_quiz_list.addRow(o);
        }
    }
    public void refleshCoursSherchList(String course_name){
        mdl_course_list.setRowCount(0);
        for(Course c : educater.sherchCourse(course_name)){
            Object[] o = {c.getId(),c.getName(),c.getLang()};
            mdl_course_list.addRow(o);
        }
    }
    public  void refleshContentSherchList(String content_name,String course_id){
        mdl_content_list.setRowCount(0);
        for(Content c : content.sherch(content_name,course_id)){
            Object[] o = {c.getId(),c.getName(), c.getComment(), c.getLink(),educater.getCourseName(c.getCourse_id())};
            mdl_content_list.addRow(o);
        }
    }
    public void comboSherchReflesh(){
        for(Course c : educater.getList()){
            cmb_shr_course.addItem(c.getName());
        }
    }
}
