import javax.swing.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class DecodeMenu extends JFrame {
    
    private String targetPicture = null;
    private JButton decodeBtn;
    private MenuSwitcher parent;
    private final Color colorScheme = Color.GRAY;
    
    public DecodeMenu(MenuSwitcher parent) {
        this.parent = parent;
        setTitle("ImgEmbed Decoder");
        setSize(parent.getSize()[0], parent.getSize()[1]);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            northPanel.setPreferredSize(new Dimension(300,35));
        JPanel centerPanel = new JPanel(new FlowLayout());
            
        JButton backBtn = new JButton("Back");
            backBtn.addActionListener((e) -> parent.switchMenu(0, this.getLocation().x, this.getLocation().y));
            backBtn.setBackground(colorScheme);
        JButton loadImageBtn = new JButton("Load Image");
            loadImageBtn.addActionListener((e) -> loadImage());
            loadImageBtn.setBackground(colorScheme);
        decodeBtn = new JButton("Decode");
            decodeBtn.addActionListener((e) -> decodeImage());
            decodeBtn.setVisible(false);
            decodeBtn.setBackground(colorScheme);
            
        try{
            BufferedImage myPicture = ImageIO.read(this.getClass().getResource("/DecodeMenu.png"));
            Image dimg = myPicture.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            Image iimg = myPicture.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(dimg));
            centerPanel.add(picLabel);
            setIconImage(iimg);
        }
        catch(Exception e){}
            
        northPanel.add(backBtn);
        northPanel.add(loadImageBtn);
        northPanel.add(decodeBtn);
        
        northPanel.setBackground(colorScheme);
        centerPanel.setBackground(colorScheme);
        
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }
    
    private void loadImage()
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("PNG Files", "png"));
        fc.setAcceptAllFileFilterUsed(false);
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            targetPicture = file.getPath();
            decodeBtn.setVisible(true);
        } else {
            
        }
    }
    
    private void decodeImage()
    {
        Picture targetPictureObj = new Picture(targetPicture);
        try {
            String secret = targetPictureObj.decodeBinaryToString();
            int numOfBytes = secret.length() / 8;
            byte[] bytes = new byte[numOfBytes];
            for(int i = 0; i < numOfBytes; i++)
                bytes[i] = (byte)Integer.parseInt(secret.substring(8 * i, 8*(i+1)), 2);
                
            FileDialog fDialog = new FileDialog(this, "Save", FileDialog.SAVE);
            fDialog.setVisible(true);
            String path = fDialog.getDirectory() + fDialog.getFile();
            FileOutputStream writer = new FileOutputStream(new File(path));
            writer.write(bytes);
            writer.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "The target file is corrupted or doesn't have hidden data. Error: " + e, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
}