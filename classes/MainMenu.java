import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MainMenu extends JFrame {
    
    private Picture targetPicture = null;
    private Picture chosenPicture = null;
    private MenuSwitcher parent;
    private final Color colorScheme = Color.GRAY;
    
    public MainMenu(MenuSwitcher parent) {
        this.parent = parent;
        setTitle("ImgEmbed Main Menu");
        setSize(parent.getSize()[0], parent.getSize()[1]);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel northPanel = new JPanel(new FlowLayout());
            northPanel.setPreferredSize(new Dimension(300,35));
        JPanel centerPanel = new JPanel(new FlowLayout());
        
        JButton encodeImageBtn = new JButton("Encode Image");
            encodeImageBtn.addActionListener((e)-> parent.switchMenu(1, this.getLocation().x, this.getLocation().y));
            encodeImageBtn.setBackground(colorScheme);
        JButton decodeImageBtn = new JButton("Decode Image");
            decodeImageBtn.addActionListener((e) -> parent.switchMenu(2, this.getLocation().x, this.getLocation().y));
            decodeImageBtn.setBackground(colorScheme);
            
        try{
            BufferedImage myPicture = ImageIO.read(this.getClass().getResource("/MainMenu.png"));
            Image dimg = myPicture.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            Image iimg = myPicture.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(dimg));
            centerPanel.add(picLabel);
            this.setIconImage(iimg);
        }
        catch(Exception e){}
            
        northPanel.add(encodeImageBtn);
        northPanel.add(decodeImageBtn);
        
        northPanel.setBackground(colorScheme);
        centerPanel.setBackground(colorScheme);
        
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }
    
}