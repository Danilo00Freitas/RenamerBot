package Frontend;

import Backend.ArchiveManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JFileChooser fileChooser = new JFileChooser();
    private String namePattern = "MOV_NG_";
    private String dirName;

    private String fileName;
    private String fileDir;

    public MainScreen(){
        //Criando a armação principal

        this.setTitle("Lasa Archive Renamer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(600,500);
        this.setResizable(false);
        this.setLayout(new FlowLayout());

        //Criando o painel principal (onde ficam os botões etc...)
        this.add(mainPanel);
        fileChooser.setFileSelectionMode(1);
        mainPanel.add(fileChooser);

        //Configurando a nomeclatura do arquivo

        JPanel configPanel = new JPanel();
        configPanel.setLayout(new GridBagLayout());
        this.add(configPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        JTextField collectDate = new JTextField();
        collectDate.setText("Informe a data da coleta");
        collectDate.setEditable(false);
        configPanel.add(collectDate, constraints);
        constraints.gridx = 1;

        JTextField informCollectDate = new JTextField();
        informCollectDate.setText("ddmmaaaa");
        informCollectDate.setEditable(true);
        configPanel.add(informCollectDate, constraints);
        constraints.gridx = 0;


        JTextField archiveDate = new JTextField();
        archiveDate.setText("Informe a data dos arquivos");
        archiveDate.setEditable(false);
        configPanel.add(archiveDate,constraints);
        constraints.gridx = 1;

        JTextField informArchiveDate = new JTextField();
        informArchiveDate.setText("ddmmaaaa");
        informArchiveDate.setEditable(true);
        configPanel.add(informArchiveDate, constraints);
        constraints.gridx = 0;


        //configurando jbutton com action listenner
        JButton renameButton = new JButton();
        renameButton.setText("Renomear Arquivos");
        this.add(renameButton);

        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileName = namePattern + informCollectDate.getText() + "_" + informArchiveDate.getText();
                fileDir = fileChooser.getSelectedFile().toString();
                dirName = fileDir + "Renomeados";

                System.out.println(fileName);
                System.out.println(fileDir);

                ArchiveManager archiveManager = new ArchiveManager(fileDir);
                archiveManager.fileCopier(fileName);

            }
        });






    }
}
