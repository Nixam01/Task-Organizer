package aproproject_application.gui;

import aproproject.Tasks.Task;
import aproproject_application.gui.TaskManagerGUI;
import aproproject_application.*;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskGUI extends JFrame implements ActionListener{

    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    private int yearNum;
    private int dayNum;
    private int monthNum;

    private JButton confirmButton;
    private JButton backButton;
    private JTextField textReceiver = new JTextField();
    private JSpinner dateSpinner;
    private JLabel dateLabel;

    private JSpinner hourSpinner;
    private JLabel hourLabel;
//    private JTextField textValue = new JTextField();
//    private JLabel year;
//    private JLabel month;
//    private JLabel day;
    private JLabel taskName;
    private JLabel taskDescription;
    private JLabel priority;
    private JLabel warning;
//    private JTextField yearField;
//    private JTextField monthField;
//    private JTextField dayField;
    private JTextField taskNameField;
    private JTextArea taskDescriptionField;
    private JTextField priorityField;

    private JSlider prioritySlider;

    private JButton additionButton =new JButton("Add");
    private JButton closeButton=new JButton("Close");
    private Font font = new Font("font",Font.ITALIC,15);

    public AddTaskGUI(){

        setSize(600, 500);
        setLayout(null);
        setTitle("Dodaj Taska");

        frame = new JFrame("Dodaj zadanie");
        //panel = new JPanel();
        //panel.setBorder(BorderFactory.createEmptyBorder(200,200,100,200));
        //panel.setLayout(new GridLayout(6,3));

//        year =  new JLabel("Rok zadania: ");
//        year.setBounds(60, 100,100,30);
//        year.setFont(font);
//        add(year);
//
//        yearField = new JTextField();
//        yearField.setBounds(200, 100,300,30);
//        add(yearField);
//
//        month =  new JLabel("Miesiąc zadania: ");
//        month.setBounds(60, 160,300,30);
//        month.setFont(font);
//        add(month);
//
//        monthField = new JTextField();
//        monthField.setBounds(200, 160,300,30);
//        add(monthField);
//
//        day =  new JLabel("Dzień zadania: ");
//        day.setBounds(60, 220,300,30);
//        day.setFont(font);
//        add(day);
//
//        dayField = new JTextField();
//        dayField.setBounds(200, 220,300,30);
//        add(dayField);

        dateLabel = new JLabel("Data wykonania: ");
        dateLabel.setBounds(60, 30,300,30);
        dateLabel.setFont(font);
        add(dateLabel);

        SimpleDateFormat model = new SimpleDateFormat("dd.MM.yy");
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, model.toPattern()));
        dateSpinner.setPreferredSize(new Dimension(100,60));
        dateSpinner.setBounds(200, 30,300,30);
        add(dateSpinner);

        hourLabel = new JLabel("Godzina wykonania: ");
        hourLabel.setBounds(60, 90,300,30);
        hourLabel.setFont(font);
        add(hourLabel);

        SimpleDateFormat hourModel = new SimpleDateFormat("HH:mm");
        hourSpinner = new JSpinner(new SpinnerDateModel());
        hourSpinner.setEditor(new JSpinner.DateEditor(hourSpinner, hourModel.toPattern()));
        hourSpinner.setPreferredSize(new Dimension(100,60));
        hourSpinner.setBounds(200, 90,300,30);
        add(hourSpinner);

        taskName =  new JLabel("Nazwa zadania: ");
        taskName.setBounds(60, 150,300,30);
        taskName.setFont(font);
        add(taskName);

        taskNameField = new JTextField();
        taskNameField.setBounds(200, 150,300,30);
        add(taskNameField);

        taskDescription =  new JLabel("Opis zadania: ");
        taskDescription.setBounds(60, 210,300,30);
        taskDescription.setFont(font);
        add(taskDescription);

        taskDescriptionField = new JTextArea();
        taskDescriptionField.setBounds(200, 210,300,60);
        taskDescriptionField.setBorder(new JTextField().getBorder());
        add(taskDescriptionField);

        priority = new JLabel("Priorytet zadania: ");
        priority.setBounds(60, 300,300,30);
        priority.setFont(font);
        add(priority);

//        priorityField = new JTextField();
//        priorityField.setBounds(200,300,300,30);
//        add(priorityField);

        prioritySlider = new JSlider(0,10,5);
        prioritySlider.setBounds(200,300,300,30);
        prioritySlider.setPaintTrack(true);
        prioritySlider.setPaintTicks(true);
        prioritySlider.setPaintLabels(true);
        add(prioritySlider);

        additionButton = new JButton("Dodaj");
        additionButton.setBounds(300,360,200,50);
        additionButton.addActionListener(this);
        add(additionButton);

        closeButton = new JButton("Zamknij");
        closeButton.setBounds(60,360,200,50);
        closeButton.addActionListener(this);
        add(closeButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        frame.pack();

        //Controller.getInstance().addTask(new Task(cal.getTime(),"Podlać paprotkę","No podlej ją",10,11));

    }

    public void addActionEvent()
    {
        //adding Action listener to components
        additionButton.addActionListener(this);
        closeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Object source = e.getSource();

        if(e.getSource() == closeButton){
            this.dispose();
            try {
                new TaskManagerGUI();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (e.getSource() == additionButton){
            try {
                Calendar cal = Calendar.getInstance();

                Date date = (Date) dateSpinner.getValue();
                Date hour = (Date) hourSpinner.getValue();

                cal.set((date.getYear() + 1900),date.getMonth(),date.getDay(), hour.getHours(), hour.getMinutes());


                String name = taskNameField.getText();
                String description = taskDescriptionField.getText();
                //int id = Controller.getInstance().getNextTaskId();
                //int priority = Integer.parseInt(priorityField.getText());
                int priority = prioritySlider.getValue();

                if(name.isEmpty() || description.isEmpty()){
                    new WarningOutGUI();
                } else {
                    Controller.getInstance().addTask(new Task(cal.getTime(),name,description,priority));
                    try {
                        new TaskManagerGUI();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    this.dispose();
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

}
