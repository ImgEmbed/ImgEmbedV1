import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class EncodeMenu extends JFrame {
    
    private String targetPicture = null;
    private String embeddingFile = null;
    private JButton loadEmbeddingBtn;
    private JButton encodeBtn;
    private MenuSwitcher parent;
    private final Color colorScheme = Color.GRAY;
    
    public EncodeMenu(MenuSwitcher parent) {
        this.parent = parent;
        setTitle("ImgEmbed Encoder");
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
        loadEmbeddingBtn = new JButton("Load Embedding File");
            loadEmbeddingBtn.addActionListener((e) -> loadEmbeddingFile());
            loadEmbeddingBtn.setVisible(false);
            loadEmbeddingBtn.setBackground(colorScheme);
        encodeBtn = new JButton("Encode");
            encodeBtn.addActionListener((e) -> encodeImage());
            encodeBtn.setVisible(false);
            encodeBtn.setBackground(colorScheme);
            
        try{
            BufferedImage myPicture = ImageIO.read(this.getClass().getResource("/EncodeMenu.png"));
            Image dimg = myPicture.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            Image iimg = myPicture.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(dimg));
            centerPanel.add(picLabel);
            this.setIconImage(iimg);
        }
        catch(Exception e){}
        northPanel.add(backBtn);
        northPanel.add(loadImageBtn);
        northPanel.add(loadEmbeddingBtn);
        northPanel.add(encodeBtn);
        
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
            loadEmbeddingBtn.setVisible(true);
        } else {
            
        }
    }
    
    private void loadEmbeddingFile()
    {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            embeddingFile = file.getPath();
            encodeBtn.setVisible(true);
        } else {
            
        }
    }
    
    private void encodeImage()
    {
        try{
            Picture targetPictureObj = new Picture(targetPicture);
            
            FileInputStream is = new FileInputStream(embeddingFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int reads = is.read();
            while(reads != -1){
                baos.write(reads);
                reads = is.read(); 
            }
            byte[] data = baos.toByteArray();
         
            StringBuilder sb = new StringBuilder("");
            for(byte x : data)
                sb.append(byteToBitString(x));
            
            boolean success = targetPictureObj.encodeBinary(sb.toString());
            if(!success)
            {
                JOptionPane.showMessageDialog(this, "The target file is too small to contain this data.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            FileDialog fDialog = new FileDialog(this, "Save", FileDialog.SAVE);
            fDialog.setVisible(true);
            String path = fDialog.getDirectory() + fDialog.getFile();
            targetPictureObj.write(path);
        } catch(Exception e){JOptionPane.showMessageDialog(this, "Unknown Error: " + e, "Error", JOptionPane.WARNING_MESSAGE);}
    }
    
    private String byteToBitString(byte in)
    {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((in >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }
    
}