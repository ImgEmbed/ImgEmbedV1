import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.math.BigInteger;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void keepOnlyRed()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void keepOnlyGreen()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setBlue(0);
            }
        }
    }

    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(255-pixelObj.getRed());
                pixelObj.setGreen(255-pixelObj.getGreen());
                pixelObj.setBlue(255-pixelObj.getBlue());
            }
        }
    }

    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int sum = pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen();
                int out = sum / 3;
                pixelObj.setRed(out);
                pixelObj.setGreen(out);
                pixelObj.setBlue(out);
            }
        }
    }

    public void fixUnderwater()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed((int)(pixelObj.getRed()*2*2.5));
                pixelObj.setBlue((int)(pixelObj.getBlue()*0.5*2.5));
                pixelObj.setGreen((int)(pixelObj.getGreen()*0.5*2.5));
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int row = 0; row < height / 2; row++)
        {
            for (int col = 0; col < pixels[row].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int row = 0; row < height / 2; row++)
        {
            for (int col = 0; col < pixels[row].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        } 
    }

    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        int square = pixels.length < pixels[0].length ? pixels.length : pixels[0].length;
        
        for (int srcRow = square-1, destCol = square-1; srcRow > 0; 
                srcRow--, destCol--)
            for(int curPixel = 0; curPixel < pixels[srcRow].length &&
                    pixels[srcRow][curPixel] != pixels[curPixel][destCol]; curPixel++)
                pixels[curPixel][destCol].setColor(pixels[srcRow][curPixel].getColor());
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        for (int row = 27; row < 97; row++)
        {
            for (int col = 13; col < mirrorPoint; col++)
            {
                count++;
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        
        System.out.println(count);
    }
    
    public void mirrorGull()
    {
        int mirrorPoint = 348;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        for (int row = 232; row < 329; row++)
        {
            for (int col = 225; col < mirrorPoint; col++)
            {
                count++;
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        
        System.out.println(count);
    }
    
    public void mirrorArms()
    {
        int mirrorPoint = 196;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
            
        for (int col = 103; col < 297; col++)
        {
            for (int row = 163; row < mirrorPoint; row++)
            {
                count++;
                topPixel = pixels[row][col];      
                bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
        
        System.out.println(count);
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }
    
    public void partialCopy(Picture fromPic, 
    int startRow, int startCol, int fromRow, int fromEndRow, int fromCol, int fromEndCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        int originalStartCol = startCol;
        int originalFromCol = fromCol;
        
        for (; 
        fromRow <= fromEndRow &&
        startRow < toPixels.length; 
        fromRow++, startRow++, startCol = originalStartCol, fromCol = originalFromCol)
        {
            for (; 
            fromCol <= fromEndCol &&
            startCol < toPixels[0].length;  
            fromCol++, startCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[startRow][startCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }
    
    public void myCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        flower1.grayscale();
        this.copy(flower1, 0, 0);
        flower1 = new Picture("flower1.jpg");
        flower1.mirrorDiagonal();
        this.copy(flower1, 150, 0);
        flower1 = new Picture("flower1.jpg");
        flower1.mirrorVertical();
        this.copy(flower1, 300, 0);
        this.mirrorVertical();
    }

    /** Method to show large changes in color horizontally
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetectionHorizontal(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }
    
    /** Method to show large changes in color vertically
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetectionVertical(int edgeDist)
    {
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color bottomColor = null;
        
        for (int col = 0; col < pixels[0].length; col++)
        {
            for (int row = 0; row < pixels.length-1; row++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[row+1][col];
                bottomColor = bottomPixel.getColor();
                if (topPixel.colorDistance(bottomColor) > 
                edgeDist)
                    topPixel.setColor(Color.BLACK);
                else
                    topPixel.setColor(Color.WHITE);
            }
        }
    }
    
    /** Method to show large changes in color horizontally and vertically
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetectionDual(int edgeDist)
    {
        Pixel thisPixel = null;
        Pixel bottomPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color bottomColor = null;
        Color rightColor = null;
        
        for (int row = 0; row < pixels.length-1; row++)
        {
            for (int col = 0; col < pixels[0].length-1; col++)
            {
                thisPixel = pixels[row][col];
                bottomPixel = pixels[row+1][col];
                rightPixel = pixels[row][col+1];
                bottomColor = bottomPixel.getColor();
                rightColor = rightPixel.getColor();
                if (thisPixel.colorDistance(bottomColor) > 
                edgeDist || thisPixel.colorDistance(rightColor) > 
                edgeDist)
                    thisPixel.setColor(Color.BLACK);
                else
                    thisPixel.setColor(Color.WHITE);
            }
        }
    }
    
    /** Method to show large changes in color horizontally, vertically, and diagonally
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetectionTriple(int edgeDist)
    {
        Pixel thisPixel = null;
        Pixel bottomPixel = null;
        Pixel rightPixel = null;
        Pixel diagonalPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color bottomColor = null;
        Color rightColor = null;
        Color diagonalColor = null;
        
        for (int row = 0; row < pixels.length-1; row++)
        {
            for (int col = 0; col < pixels[0].length-1; col++)
            {
                thisPixel = pixels[row][col];
                bottomPixel = pixels[row+1][col];
                rightPixel = pixels[row][col+1];
                diagonalPixel = pixels[row+1][col+1];
                bottomColor = bottomPixel.getColor();
                rightColor = rightPixel.getColor();
                diagonalColor = diagonalPixel.getColor();
                if (thisPixel.colorDistance(bottomColor) > 
                edgeDist || thisPixel.colorDistance(rightColor) > 
                edgeDist || thisPixel.colorDistance(diagonalColor) > 
                edgeDist)
                    thisPixel.setColor(Color.BLACK);
                else
                    thisPixel.setColor(Color.WHITE);
            }
        }
    }
    
    public boolean encodeBinary(byte[] message)
    {
        Pixel[][] pixels = this.getPixels2D();
        
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            { 
                if(pixels[row][col].getRed() % 2 != 0)
                    if(pixels[row][col].getRed() == 255)
                        pixels[row][col].setRed(254);
                    else
                        pixels[row][col].setRed(pixels[row][col].getRed() - 1);
                
                if(pixels[row][col].getBlue() % 2 != 0)
                    if(pixels[row][col].getBlue() == 255)
                        pixels[row][col].setBlue(254);
                    else
                        pixels[row][col].setBlue(pixels[row][col].getBlue() - 1);
                
                if(pixels[row][col].getGreen() % 2 != 0)
                    if(pixels[row][col].getGreen() == 255)
                        pixels[row][col].setGreen(254);
                    else
                        pixels[row][col].setGreen(pixels[row][col].getGreen() - 1);
            }
        }
        
        int messageIndex = 0;
        for(Pixel[] curPixRow : pixels)
            for(Pixel curPix : curPixRow)
            {   
                if(messageIndex >= message.length)
                {
                    curPix.setRed(curPix.getRed() + 1);
                    return true;
                }
                else if(message[messageIndex] == (byte)1)
                    curPix.setRed(curPix.getRed() + 1);
                    
                messageIndex++;  
                  
                if(messageIndex >= message.length)
                {
                    curPix.setGreen(curPix.getGreen() + 1);
                    return true;
                }
                else if(message[messageIndex] == (byte)1)
                    curPix.setGreen(curPix.getGreen() + 1);
                    
                messageIndex++;
                    
                if(messageIndex >= message.length)
                {
                    curPix.setBlue(curPix.getBlue() + 1);
                    return true;
                }
                else if(message[messageIndex] == (byte)1)
                    curPix.setBlue(curPix.getBlue() + 1);
                    
                messageIndex++;
            }
            
        return true;
    }
    
    public boolean encodeBinary(String binaryMessage)
    {
        Pixel[][] pixels = this.getPixels2D();
        if(binaryMessage.length() > 3 * pixels.length * pixels[0].length - 1)
            return false;
        
        byte[] message = new byte[binaryMessage.length()];
        
        for(int i = 0; i < binaryMessage.length(); i++)
            if(binaryMessage.charAt(i) == '0')
                message[i] = 0;
            else if(binaryMessage.charAt(i) == '1')
                message[i] = 1;
            else
                return false;
                
        return encodeBinary(message);
    }
    
    public byte[] decodeBinary()
    {
        Pixel[][] pixels = this.getPixels2D();
        List<Byte> message = new ArrayList<Byte>();
        int messageSize = 0;
        
        for(int row = 0; row < pixels.length; row++)
            for(int col = 0; col < pixels[0].length; col++)
            {
                if(pixels[row][col].getRed() % 2 == 0)
                    message.add(new Byte((byte)0));
                else
                {
                    message.add(new Byte((byte)1));
                    messageSize = message.size()-1;
                }
                
                if(pixels[row][col].getGreen() % 2 == 0)
                    message.add(new Byte((byte)0));
                else
                {
                    message.add(new Byte((byte)1));
                    messageSize = message.size()-1;
                }
                
                if(pixels[row][col].getBlue() % 2 == 0)
                    message.add(new Byte((byte)0));
                else
                {
                    message.add(new Byte((byte)1));
                    messageSize = message.size()-1;
                }
            }
            
        byte[] out = new byte[messageSize];
        for(int i=0; i<messageSize; i++)
            out[i] = message.get(i);

        return out;
    }
    
    public String decodeBinaryToString()
    {
        byte[] decoded = this.decodeBinary();
            
        StringBuilder out = new StringBuilder("");
        for(int i=0; i<decoded.length; i++)
            out.append(decoded[i]);

        return out.toString();
    }

    public byte[] toBinaryArray()
    {
        //http://www.mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/
        //http://exampledepot.8waytrips.com/egs/java.math/Bytes2Str.html
        byte[] imageInByte = null;
        byte[] out = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( this.getBufferedImage(), "png", baos );
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            
            StringBuilder sb = new StringBuilder("");
            for(byte x : imageInByte)
                sb.append(byteToBitString(x));
            
            out = new byte[sb.length()];
            for(int i=0; i<sb.length(); i++)
               out[i] = sb.charAt(i) == '0' ? (byte)0 : (byte)1;
        } catch(Exception e) {}
        
        return out;
    }
    
    public String toBinaryString()
    {
        byte[] binaryArray = this.toBinaryArray();
        
        StringBuilder sb = new StringBuilder("");
        for(byte x : binaryArray)
            sb.append(x==(byte)0?'0':'1');
        
        return sb.toString();
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
    
    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
