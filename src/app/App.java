package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class App extends JFrame{


    Job j = new Job();
    news n = new news();
    movie m = new movie();
    Weather w = new Weather();
    Parent[] servicesArray= {j , n, m, w };
    String[] services = {"Job Vacancy", "News", "Movie", "Weather Condition"};
    int serviceSelection = 0;
    JComboBox serviceComboBox;
    String userInput;
    String displayedResult;

    JPanel bottomPanel = new JPanel();
    JPanel topPanel = new JPanel();//new BorderLayout());

    JPanel middlePanel = new JPanel();
    JPanel upperHalfPanel = new JPanel();
    JPanel displayPanel = new JPanel();

    JTextArea textArea = new JTextArea(5,30);

    public App() {

        this.setTitle("Web Scraper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1020, 680);
        this.getContentPane().setBackground(new Color(50, 60, 90));
        this.setLayout(new GridLayout(2,1));


        JLabel titleLabel = new JLabel("  WEB SCRAPING");
        titleLabel.setFont(new Font("ROMAN_BASELINE", Font. BOLD, 40));
        titleLabel.setForeground(new Color(255, 150, 100));
        titleLabel.setLayout(new FlowLayout());

        topPanel.setLayout(new GridLayout(1,2));
        topPanel.setBackground(new Color(45, 60, 80));

        JLabel descriptionLabel = new JLabel("<html> <br> Choose the Right service category to <br>" +
                " search for the topic of your choice</html>");
        descriptionLabel.setForeground(new Color(230, 150, 100));

        JPanel innerPanel2 = new JPanel();
        descriptionLabel.setFont(new Font("Serif", Font.ITALIC , 18));
        innerPanel2.setBackground(new Color(45, 60, 80));
        innerPanel2.add(descriptionLabel);
        topPanel.add(titleLabel);
        topPanel.add(innerPanel2);


        JLabel servicesLabel = new JLabel("Available Services");
        servicesLabel.setForeground(new Color(240, 200, 100));


        middlePanel.setLayout(new FlowLayout());
        middlePanel.add(servicesLabel);

        serviceComboBox = new JComboBox(services);
        serviceComboBox.setSelectedIndex(serviceSelection);
        serviceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == serviceComboBox) {
                    serviceSelection = serviceComboBox.getSelectedIndex();

                }

            }
        });

        middlePanel.setBackground(new Color(50, 60, 90));
        middlePanel.add(serviceComboBox);



        //bottomPanel.setBorder(BorderFactory.createEmptyBorder());
        bottomPanel.setBackground(new Color(50, 60, 90));

        JTextField searchInput = new JTextField(32);
        JButton searchButton = new JButton("Search");

        JLabel searchLabel = new JLabel("Enter title: ");
        searchLabel.setForeground(new Color(240, 170, 50));
        searchLabel.setFont(new Font("Serif", Font.BOLD , 16));

        bottomPanel.add(searchLabel);
        bottomPanel.add(searchInput);
        bottomPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent s) {
                if (s.getSource() == searchButton ){

                    userInput = searchInput.getText();
                    String invalidInput = userInput.replace(" ", "");

                    if (! invalidInput.equals("")){

                        servicesArray[serviceSelection].search(userInput);
                        displayedResult = String.join("\n", servicesArray[serviceSelection].displayResult());
                        textArea.setText(displayedResult);
                    }
                    else{
                        JOptionPane.showMessageDialog(bottomPanel,"Invalid Input", "Input Error",JOptionPane.ERROR_MESSAGE);
                    }

                }



            }
        });

        upperHalfPanel.setBackground(new Color(50, 60, 90));
        upperHalfPanel.setLayout(new GridLayout(0,1));
        upperHalfPanel.add(topPanel);
        upperHalfPanel.add(middlePanel);
        upperHalfPanel.add(bottomPanel);

        this.add(upperHalfPanel);



        textArea.setColumns(65);
        textArea.setRows(12);

        textArea.setFont(new Font("Sans-Serif", Font.ITALIC, 14));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBackground(new Color(50, 60, 90));
        textArea.setForeground(new Color(240, 170, 100));

        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        displayPanel.setBackground(new Color(50, 60, 90));
        displayPanel.setLayout(new FlowLayout());
        displayPanel.add(scroll);

        this.add(displayPanel);

       this.setVisible(true);

    }


    public static void main(String[] args) {
        new App();

    }


}


