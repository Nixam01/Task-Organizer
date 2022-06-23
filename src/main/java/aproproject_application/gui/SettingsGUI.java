package aproproject_application.gui;
import aproproject_application.Controller;
import aproproject_application.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class SettingsGUI {
    private LocalTime PreferredDailyNotificationTime;
    private LocalTime StartOfWorkingTime;
    private LocalTime EndOfWorkingTime;
    private int NumberOfDailyNotifications = 10;
    private int DEADLINENotificationBeforeDeadline = 2;

    public SettingsGUI() {
        Date dat = new Date();
        LocalTime t = LocalTime.of(dat.getHours(),dat.getMinutes());
        PreferredDailyNotificationTime = t;
        StartOfWorkingTime = t;
        EndOfWorkingTime = t;

        //Creating the Frame
        JFrame frame = new JFrame("Ustawienia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);


        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JButton save_changes = new JButton("Zapisz zmiany");
        save_changes.addActionListener(e -> {
            Settings settings = Settings.getInstance("tests.properties");
            settings.setProperties(PreferredDailyNotificationTime,StartOfWorkingTime,EndOfWorkingTime,NumberOfDailyNotifications,DEADLINENotificationBeforeDeadline);
            try {
                settings.saveSettings();
            } catch (IOException ioException) {
                System.err.println("Błąd zapisywania ustawień: " + ioException.getMessage());
            }
        });

        JButton exit = new JButton("Wyjdź");
        exit.addActionListener(e -> frame.dispose());
        panel.add(save_changes);
        panel.add(exit);

        JPanel centralPanel = new JPanel();
        centralPanel.setSize(400,400);
        GroupLayout layout = new GroupLayout(centralPanel);
        layout.setAutoCreateGaps(false);
        layout.setAutoCreateContainerGaps(false);
        centralPanel.setLayout(layout);

        JLabel everydayHour = new JLabel("Godzina codziennych powiadomień: ");
        JLabel dailyLimit =   new JLabel("Dzienny limit powiadomień: ");
        JLabel workHours =    new JLabel("Czas pracy: ");
        JLabel startingHour = new JLabel("Godzina rozpoczęcia: ");
        JLabel endingHour =   new JLabel("Godzina zakończenia: ");
        JLabel timeBefore1 =   new JLabel("Ile godzin przed wydarzeniem");
        JLabel timeBefore2 =   new JLabel("ma się pojawiać powiadomienie:");

        Date everydayHourDate = Date.from(Settings.getInstance(Controller.getFileNameWithoutExtension() + ".properties").getPreferredDailyNotificationTime()
                .atDate(LocalDate.of(2021, 1, 1)).atZone(ZoneId.systemDefault()).toInstant());
        SpinnerDateModel sm1 = new SpinnerDateModel(everydayHourDate, null, null, Calendar.MINUTE);
        JSpinner spinner1 = new JSpinner(sm1);
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(spinner1, "H:mm");
        de1.getTextField().setEditable(true);
        spinner1.setEditor(de1);
        spinner1.setPreferredSize(new Dimension(50,30));
        spinner1.addChangeListener(e -> {
            Date date = (Date) ((JSpinner) e.getSource()).getValue();
            PreferredDailyNotificationTime = LocalTime.of(date.getHours(),date.getMinutes()); });

        JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(Settings.getInstance(Controller.getFileNameWithoutExtension() + ".properties").getNumberOfDailyNotifications() ,1,Integer.MAX_VALUE,1));
        spinner2.setPreferredSize(new Dimension(50,30));
        spinner2.addChangeListener(e -> NumberOfDailyNotifications = (int) ((JSpinner)e.getSource()).getValue());

        Date startinHourDate = Date.from(Settings.getInstance(Controller.getFileNameWithoutExtension() + ".properties").getStartOfWorkingTime()
                .atDate(LocalDate.of(2021, 1, 1)).atZone(ZoneId.systemDefault()).toInstant());
        SpinnerDateModel sm3 = new SpinnerDateModel(startinHourDate, null, null, Calendar.MINUTE);
        JSpinner spinner3 = new JSpinner(sm3);
        JSpinner.DateEditor de3 = new JSpinner.DateEditor(spinner3, "H:mm");
        de3.getTextField().setEditable(true);
        spinner3.setEditor(de3);
        spinner3.setPreferredSize(new Dimension(50,30));
        spinner3.addChangeListener(e -> {
            Date date = (Date) ((JSpinner) e.getSource()).getValue();
            StartOfWorkingTime = LocalTime.of(date.getHours(),date.getMinutes()); });

        Date endingHourDate = Date.from(Settings.getInstance(Controller.getFileNameWithoutExtension() + ".properties").getEndOfWorkingTime()
                .atDate(LocalDate.of(2021, 1, 1)).atZone(ZoneId.systemDefault()).toInstant());
        SpinnerDateModel sm4 = new SpinnerDateModel(endingHourDate, null, null, Calendar.MINUTE);
        JSpinner spinner4 = new JSpinner(sm4);
        JSpinner.DateEditor de4 = new JSpinner.DateEditor(spinner4, "H:mm");
        de4.getTextField().setEditable(true);
        spinner4.setEditor(de4);
        spinner4.setPreferredSize(new Dimension(50,30));
        spinner4.addChangeListener(e -> {
            Date date = (Date) ((JSpinner) e.getSource()).getValue();
            EndOfWorkingTime = LocalTime.of(date.getHours(),date.getMinutes()); });

        JSpinner spinner5 = new JSpinner(new SpinnerNumberModel(Settings.getInstance(Controller.getFileNameWithoutExtension() + ".properties").getDEADLINENotificationBeforeDeadline() ,1,Short.MAX_VALUE,1));
        spinner5.setPreferredSize(new Dimension(50,30));
        spinner5.addChangeListener(e -> DEADLINENotificationBeforeDeadline = (int) ((JSpinner)e.getSource()).getValue());

        //Placing elements using Group Layout
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addGap(30)).addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addComponent(everydayHour).addGap(30).addComponent(spinner1,60,GroupLayout.DEFAULT_SIZE,60))
                        .addGroup(layout.createSequentialGroup().addComponent(dailyLimit).addGap(78).addComponent(spinner2,50,GroupLayout.DEFAULT_SIZE,60))
                        .addGroup(layout.createSequentialGroup().addComponent(workHours))
                        .addGroup(layout.createSequentialGroup().addComponent(startingHour).addGap(111).addComponent(spinner3,50,GroupLayout.DEFAULT_SIZE,60))
                        .addGroup(layout.createSequentialGroup().addComponent(endingHour).addGap(109).addComponent(spinner4,50,GroupLayout.DEFAULT_SIZE,60))
                        .addGroup(layout.createSequentialGroup().addComponent(timeBefore1))
                        .addGroup(layout.createSequentialGroup().addComponent(timeBefore2).addGap(50).addComponent(spinner5,50,GroupLayout.DEFAULT_SIZE,60))));

        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createSequentialGroup().addGap(30)).addGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50).addComponent(everydayHour).addComponent(spinner1,15,GroupLayout.DEFAULT_SIZE,20))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50).addComponent(dailyLimit).addComponent(spinner2,15,GroupLayout.DEFAULT_SIZE,20))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(30).addComponent(workHours))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(20).addComponent(startingHour).addComponent(spinner3,15,GroupLayout.DEFAULT_SIZE,20))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50).addComponent(endingHour).addComponent(spinner4,15,GroupLayout.DEFAULT_SIZE,20))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(2).addComponent(timeBefore1))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(10).addComponent(timeBefore2).addComponent(spinner5,15,GroupLayout.DEFAULT_SIZE,20))));

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.add(centralPanel);

        centralPanel.setVisible(true);
        frame.setVisible(true);
    }

}