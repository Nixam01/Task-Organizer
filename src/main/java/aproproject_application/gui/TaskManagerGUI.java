package aproproject_application.gui;

import aproproject.Tasks.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import aproproject_application.*;

public class TaskManagerGUI extends JFrame implements ActionListener {

    private int cnt = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JList list;

    private JButton additionButton;
    private JButton editButton;
    private JButton closeButton;
    private JButton deleteButton;
    private JButton watchUpButton;

    private DefaultListModel<Task> model = new DefaultListModel<>();
    private JTextArea taskField;



    public TaskManagerGUI() throws IOException {

//
//        frame = new JFrame("Menadżer Zadań");
//        JButton button = new JButton("Click me");
//        button.addActionListener(this);
//
//        label = new JLabel("Number of clicks: " + cnt);
//
//
//        panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(200,200,100,200));
//        panel.setLayout(new GridLayout(2,1));
//        panel.add(label);
//        panel.add(button);
//
//        frame.add(panel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("Our Gui");
//        frame.pack();
//        frame.setVisible(true);
//
//        list = new JList<Task>();
//

        setSize(800, 550);
        setLayout(null);
        setTitle("Menadżer zadań");

        ArrayList<Task> tasks = new ArrayList<Task>(Controller.getInstance().getAllTasks());

        frame = new JFrame("Menadżer zadań");

        list = new JList(tasks.toArray());
        DefaultListModel taskName = new DefaultListModel();
        list.setVisibleRowCount(15);
        list.setBounds(60,60,320,390); //Było 350
        JScrollPane pane = new JScrollPane(list);
        add(pane);
        pane.createVerticalScrollBar();
        pane.setBounds(60,60,320,390);

        taskField = new JTextArea();
        taskField.setBounds(430, 130,300,190);
        taskField.setBorder(new JTextField().getBorder());
        add(taskField);

        additionButton = new JButton("Dodaj");
        additionButton.setBounds(640,60,90,50);
        additionButton.addActionListener(this);
        add(additionButton);

        editButton = new JButton("Edytuj");
        editButton.setBounds(535,60,90,50);
        editButton.addActionListener(this);
        add(editButton);

        watchUpButton = new JButton("Podgląd");
        watchUpButton.setBounds(430,340,300,50);
        watchUpButton.addActionListener(this);
        add(watchUpButton);

        deleteButton = new JButton("Usuń");
        deleteButton.setBounds(430,60,90,50);
        deleteButton.addActionListener(this);
        add(deleteButton);

        closeButton = new JButton("Zamknij");
        closeButton.setBounds( 430,400,300,50);
        closeButton.addActionListener(this);
        add(closeButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        this.setVisible(true);

    }

//    public static void main(String[] args) throws IOException {
//
//        Controller cntr = Controller.getInstance(); //Start programu!!!
//        cntr.startLogic();
//        new TaskManagerGUI();
////        Calendar cal = Calendar.getInstance();
////        cal.set(2021,5,24);
////        Controller.getInstance().addTask(new Task(cal.getTime(),"Podlać paprotkę","No podlej ją",10,11));
////        System.out.println(Controller.getInstance().getAllTasks());
//
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == additionButton){
            this.dispose();
            new AddTaskGUI();
        }

        if (e.getSource() == deleteButton){
            Task temTask = (Task) list.getSelectedValue();
            try {
                if (temTask != null) {
                    Controller.getInstance().removeTask(temTask.getId());
                    this.dispose();
                    try {
                        new TaskManagerGUI();
                    } catch (IOException ioException) {
                        //ioException.printStackTrace();
                    }
                }

            } catch (IOException ioException) {
               // ioException.printStackTrace();
            }
        }

        if (e.getSource() == editButton){
            Task temTask = (Task) list.getSelectedValue();
            if (temTask != null) {
                this.dispose();
                new EditTaskGUI(temTask);
            }
        }

        if (e.getSource() == list){
            Task temTask = (Task) list.getSelectedValue();
            taskField.setText(temTask.toStringForTaskManager());
        }
        if (e.getSource() == watchUpButton){
            Task temTask = (Task) list.getSelectedValue();
            if (temTask != null) {
                taskField.setText(temTask.toStringForTaskManager());
            }

        }

        if(e.getSource() == closeButton){
            dispose();
            new StartClientView();
        }

    }

}
