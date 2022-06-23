package aproproject_application.gui;

import aproproject.Tasks.Task;
import aproproject_application.Controller;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class StartClientView extends JFrame implements ActionListener{
    JButton settings, managetasks, exit, save;
    JDatePanelImpl datePanel;
    JLabel zadania;
    static JTextArea zadania2;
    static List<Task> tasks = Controller.getInstance().getTasksForToday();

    public StartClientView(){
        setSize(1200, 450);
        setTitle("Menadżer zadań - Task Organizer - Ekran startowy");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        managetasks = new JButton("Zarządzanie zadaniami: ");
        managetasks.setBounds(50, 50, 180, 50);
        add(managetasks);
        managetasks.addActionListener(this);

        settings = new JButton("Ustawienia: ");
        settings.setBounds(50, 130, 180, 50);
        add(settings);
        settings.addActionListener(this);

        save = new JButton("Zapisz do pliku");
        save.setBounds(50, 210, 180, 50);
        add(save);
        save.addActionListener(this);

        exit = new JButton("Wyjście ");
        exit.setBounds(50, 290,180,50);
        add(exit);
        exit.addActionListener(this);

        zadania = new JLabel("Zadania do wykonania: ");
        zadania.setBounds(300,50,200,20);
        add(zadania);
        zadania2 = new JTextArea(toTheString());
        zadania2.setBounds(300,75,450,275);
        zadania2.setEditable(false);
        zadania2.setVisible(true);
        zadania2.setBorder(new JTextField().getBorder());
        add(zadania2);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model);
        datePanel.setBounds(825, 50, 300, 290);
        datePanel.addActionListener(this);
        add(datePanel);
        refresh();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //zamykanie okienka po zakończeniu programu
        setVisible(true);
    }

    public static void refresh(){
        tasks = Controller.getInstance().getTasksForToday();
        zadania2.setText(toTheString());
    }

    public void refreshDay(Calendar cal){
        tasks = Controller.getInstance().getTasksAtDay(cal.getTime());
        zadania2.setText(toTheString());
    }

    public static String toTheString(){
        if(tasks.size() == 0)
            return "-";
        StringBuilder sb = new StringBuilder();
        for(Task t: tasks){
            sb.append(t.toStringforGUI());
        }
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source == settings){
            new SettingsGUI();
        }
        else if (source == managetasks){
            this.dispose();
            try {
                new TaskManagerGUI();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if (source == save){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            fileChooser.showSaveDialog(this);
            File fileToSave = fileChooser.getSelectedFile();
            Controller.getInstance().saveTasksToFile(tasks,fileToSave.getPath());
        }
        else if (source == datePanel) {
            Calendar cal = Calendar.getInstance();
            if (datePanel.getModel().getYear() == Calendar.getInstance().get(Calendar.YEAR) && datePanel.getModel().getMonth() == Calendar.getInstance().get(Calendar.MONTH) && datePanel.getModel().getDay() == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                refresh();
            }
            else {
                cal.set(datePanel.getModel().getYear(), datePanel.getModel().getMonth(), datePanel.getModel().getDay(), 0, 0, 0);
                refreshDay(cal);
            }
        }
        else if (source == exit){
            System.exit(1);
       }

    }

}
