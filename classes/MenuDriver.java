import javax.swing.JFrame;
import javax.swing.UIManager;

public class MenuDriver implements MenuSwitcher{
    JFrame mainMenuFrame = null;
    JFrame encodeFrame = null;
    JFrame decodeFrame = null;
    
    public static void main(String args[])
    {
        MenuDriver driver = new MenuDriver();
        driver.init();
    }
    
    private void init()
    {
        try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){}
        mainMenuFrame = new MainMenu(this);
        encodeFrame = new EncodeMenu(this);
        decodeFrame = new DecodeMenu(this);
        
        mainMenuFrame.setVisible(true);
    }
    
    public void switchMenu(int id, int x, int y)
    {
        switch(id)
        {
            case 0:
                mainMenuFrame.setVisible(true);
                encodeFrame.setVisible(false);
                decodeFrame.setVisible(false);
                mainMenuFrame.setLocation(x, y);
            break;
            case 1:
                mainMenuFrame.setVisible(false);
                encodeFrame.setVisible(true);
                decodeFrame.setVisible(false);
                encodeFrame.setLocation(x, y);
            break;
            case 2:
                mainMenuFrame.setVisible(false);
                encodeFrame.setVisible(false);
                decodeFrame.setVisible(true);
                decodeFrame.setLocation(x, y);
            break;
            default:
            break;
        }
    }
}