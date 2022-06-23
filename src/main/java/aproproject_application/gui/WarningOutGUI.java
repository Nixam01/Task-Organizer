package aproproject_application.gui;

import org.apache.velocity.texen.ant.TexenTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarningOutGUI extends JFrame implements ActionListener {

    private JFrame frame;
    private JLabel text;
    private JLabel text2;
    private Font font = new Font("font",Font.ITALIC,15);
    private JButton button;

    public WarningOutGUI(){

        setSize(300, 200);
        setLayout(null);
        setTitle("Uwaga!");

        frame = new JFrame("Uwaga!");

        text =  new JLabel("Pole nazwy i opis zadania");
        text.setBounds(55, 30,200,30);
        text.setFont(font);
        add(text);

        text2 =  new JLabel("nie mogą być puste!");
        text2.setBounds(70, 50,200,30);
        text2.setFont(font);
        add(text2);

        button = new JButton("Ok");
        button.setBounds(90,100,100,30);
        button.addActionListener(this);
        add(button);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == button){
            this.dispose();
        }

    }
}
