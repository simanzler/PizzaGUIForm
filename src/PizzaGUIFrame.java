import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

public class PizzaGUIFrame extends JFrame {
    JPanel mainPnl;
    JPanel radioPnl;
    JPanel comboPnl;
    JPanel checkPnl;
    JPanel textPnl;
    JPanel buttonPnl;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    JRadioButton radioButton3;
    JComboBox<String> comboBox;
    JCheckBox option1;
    JCheckBox option2;
    JCheckBox option3;
    JCheckBox option4;
    JCheckBox option5;
    JCheckBox option6;
    JTextArea textArea;
    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;
    public PizzaGUIFrame() {
        mainPnl = new JPanel(new BorderLayout());
        CreateRadioPanel();
        mainPnl.add(radioPnl, BorderLayout.NORTH);
        CreateComboPanel();
        mainPnl.add(comboPnl, BorderLayout.WEST);
        CreateCheckPanel();
        mainPnl.add(checkPnl, BorderLayout.CENTER);
        CreateTextPanel();
        mainPnl.add(textPnl, BorderLayout.EAST);
        CreateButtonPanel();
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setTitle("Pizza Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }
    public void CreateRadioPanel() {
        radioPnl = new JPanel(new GridLayout(1,3));
        radioButton1 = new JRadioButton("Thin");
        radioButton2 = new JRadioButton("Regular");
        radioButton3 = new JRadioButton("Deep-dish");

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);


        radioPnl.add(radioButton1);
        radioPnl.add(radioButton2);
        radioPnl.add(radioButton3);

        radioPnl.setBorder(BorderFactory.createTitledBorder("Crust"));
    }
    public void CreateComboPanel() {
        comboPnl = new JPanel();
        String[] size = {
                "Small",
                "Medium",
                "Large",
                "Super"
        };
        comboBox = new JComboBox<>(size);

        comboPnl.add(comboBox);
        comboPnl.setBorder(BorderFactory.createTitledBorder("Size"));
    }
    public void CreateCheckPanel() {
        checkPnl = new JPanel(new GridLayout(2,3));
        option1 = new JCheckBox("Peperoni");
        option2 = new JCheckBox("Sausage");
        option3 = new JCheckBox("Bacon");
        option4 = new JCheckBox("Chicken");
        option5 = new JCheckBox("Spinach");
        option6 = new JCheckBox("Green Onion");

        checkPnl.add(option1);
        checkPnl.add(option2);
        checkPnl.add(option3);
        checkPnl.add(option4);
        checkPnl.add(option5);
        checkPnl.add(option6);
        checkPnl.setBorder(BorderFactory.createTitledBorder("Toppings"));
    }
    public void CreateTextPanel() {
        textPnl = new JPanel();
        textArea = new JTextArea(20,40);
        textArea.setEditable(false);

        textPnl.add(textArea);
        textPnl.setBorder(BorderFactory.createTitledBorder("Order"));
    }
    public void CreateButtonPanel() {
        buttonPnl = new JPanel(new GridLayout(1,3));
        orderBtn = new JButton("Order");
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");

        orderBtn.addActionListener((ActionEvent ae) -> {
            textArea.setText("");
            double cost = 0;
            double tax = 0;
            double total = 0;
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            if (radioButton1.isSelected()){textArea.append("Thin\n");}
            if (radioButton2.isSelected()){textArea.append("Regular\n");}
            if (radioButton3.isSelected()){textArea.append("Deep-dish\n");}

            if (comboBox.getSelectedIndex()==0){textArea.append("Small           $8.00\n"); cost += 8;}
            if (comboBox.getSelectedIndex()==1){textArea.append("Medium          $12.00\n"); cost += 12;}
            if (comboBox.getSelectedIndex()==2){textArea.append("Large           $16.00\n"); cost += 16;}
            if (comboBox.getSelectedIndex()==3){textArea.append("Super           $20.00\n"); cost += 20;}

            if (option1.isSelected()){textArea.append("Peperoni        $1.00\n"); cost++;}
            if (option2.isSelected()){textArea.append("Sausage         $1.00\n"); cost++;}
            if (option3.isSelected()){textArea.append("Bacon           $1.00\n"); cost++;}
            if (option4.isSelected()){textArea.append("Chicken         $1.00\n"); cost++;}
            if (option5.isSelected()){textArea.append("Spinach         $1.00\n"); cost++;}
            if (option6.isSelected()){textArea.append("Green Onion     $1.00\n"); cost++;}

            textArea.append("---------------------------\n");
            tax = cost * .07;
            total = cost + tax;
            textArea.append("Subtotal        " + formatter.format(cost) + "\n");
            textArea.append("Tax             " + formatter.format(tax) + "\n");
            textArea.append("---------------------------\n");
            textArea.append("Total           " + formatter.format(total) + "\n");




        });
        clearBtn.addActionListener((ActionEvent ae) -> {
            radioButton1.setSelected(false);
            radioButton2.setSelected(false);
            radioButton3.setSelected(false);

            comboBox.setSelectedItem(0);

            option1.setSelected(false);
            option2.setSelected(false);
            option3.setSelected(false);
            option4.setSelected(false);
            option5.setSelected(false);
            option6.setSelected(false);

            textArea.setText("");
        });
        quitBtn.addActionListener((ActionEvent ae) -> {
                    Object[] options = {"Quit", "Cancel"};
                    int answer = JOptionPane.showOptionDialog(mainPnl, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
                    if (answer==0)
                        System.exit(0);
                });

        buttonPnl.add(orderBtn);
        buttonPnl.add(clearBtn);
        buttonPnl.add(quitBtn);
    }
}
