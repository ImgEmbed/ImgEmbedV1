import javax.imageio.ImageIO;
import java.math.BigInteger;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
    /** Method to test zeroBlue */
    public static void testZeroBlue()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    public static void testKeepOnlyBlue()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyBlue();
        beach.explore();
    }

    public static void testKeepOnlyRed()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyRed();
        beach.explore();
    }

    public static void testKeepOnlyGreen()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyGreen();
        beach.explore();
    }

    public static void testNegate()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.negate();
        beach.explore();
    }

    public static void testGrayscale()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.grayscale();
        beach.explore();
    }

    public static void testFixUnderwater()
    {
        Picture water = new Picture("water.jpg");
        water.explore();
        water.fixUnderwater();
        water.explore();
    }

    /** Method to test mirrorVertical */
    public static void testMirrorVertical()
    {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVertical();
        caterpillar.explore();
    }

    public static void testMirrorVerticalRightToLeft()
    {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVerticalRightToLeft();
        caterpillar.explore();
    }

    public static void testMirrorHorizontal()
    {
        Picture redMotorcycle = new Picture("redMotorcycle.jpg");
        redMotorcycle.explore();
        redMotorcycle.mirrorHorizontal();
        redMotorcycle.explore();
    }

    public static void testMirrorHorizontalBotToTop()
    {
        Picture redMotorcycle = new Picture("redMotorcycle.jpg");
        redMotorcycle.explore();
        redMotorcycle.mirrorHorizontalBotToTop();
        redMotorcycle.explore();
    }

    public static void testMirrorDiagonal()
    {
        Picture beach = new Picture("thruDoor.jpg");
        beach.explore();
        beach.mirrorDiagonal();
        beach.explore();
    }

    /** Method to test mirrorTemple */
    public static void testMirrorTemple()
    {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    public static void testMirrorArms()
    {
        Picture snowman = new Picture("snowman.jpg");
        snowman.explore();
        snowman.mirrorArms();
        snowman.explore();
    }
    
    public static void testMirrorGull()
    {
        Picture seagull = new Picture("seagull.jpg");
        seagull.explore();
        seagull.mirrorGull();
        seagull.explore();
    }
    
    /** Method to test the collage method */
    public static void testCollage()
    {
        Picture canvas = new Picture("640x480.jpg");
        canvas.createCollage();
        canvas.explore();
    }
    
    public static void testMyCollage()
    {
        Picture canvas = new Picture("640x480.jpg");
        canvas.myCollage();
        canvas.explore();
    }
    
    public static void testCopy()
    {
        Picture canvas = new Picture("640x480.jpg");
        canvas.copy(new Picture("flower1.jpg"), 0, 0);
        canvas.copy(new Picture("flower2.jpg"), 100, 0);
        canvas.explore();
    }
    
    public static void testPartialCopy()
    {
        Picture canvas = new Picture("640x480.jpg");
        canvas.partialCopy(new Picture("flower1.jpg"), 0, 50, 20, 80, 50, 60);
        canvas.partialCopy(new Picture("flower2.jpg"), 100, 40, 30, 70, 40, 70);
        canvas.explore();
    }

    /** Method to test horizontal edgeDetection */
    public static void testEdgeDetectionHorizontal()
    {
        Picture swan = new Picture("redMotorcycle.jpg");
        swan.edgeDetectionHorizontal(20);
        swan.explore();
    }
    
    public static void testEdgeDetectionVertical()
    {
        Picture swan = new Picture("redMotorcycle.jpg");
        swan.edgeDetectionVertical(20);
        swan.explore();
    }
    
    public static void testEdgeDetectionDual()
    {
        Picture swan = new Picture("redMotorcycle.jpg");
        swan.edgeDetectionDual(20);
        swan.explore();
    }
    
    public static void testEdgeDetectionTriple()
    {
        Picture swan = new Picture("redMotorcycle.jpg");
        swan.edgeDetectionTriple(20);
        swan.explore();
    }
    
    public static void testEncode()
    {
        Picture target = new Picture("720p.jpg");
        Picture secret = new Picture("robot.jpg");
        
        boolean success = target.encodeBinary(secret.toBinaryString());
        if(success)
              target.write("encoded.png");
              
        target.explore();
    }
    
    public static void testDecode()
    {
        //http://www.mkyong.com/java/how-to-convert-byte-to-bufferedimage-in-java/
        Picture target = new Picture("Output.png");
        //System.out.println(target.decodeBinaryToString());
        try{
            String secret = target.decodeBinaryToString();
            int numOfBytes = secret.length() / 8;
            byte[] bytes = new byte[numOfBytes];
            for(int i = 0; i < numOfBytes-1; i++)
                bytes[i] = (byte)Integer.parseInt(secret.substring(8 * i, 8*(i+1)), 2);
                
            InputStream in = new ByteArrayInputStream(bytes);
            BufferedImage bImageFromConvert = ImageIO.read(in);
            /*byte dataToWrite[] = secret_BigInt.toByteArray();
            FileOutputStream out = new FileOutputStream("decode");
            out.write(dataToWrite);
            out.close();*/
            ImageIO.write(bImageFromConvert, "png", new File("decoded.png"));
            Picture secret_Picture = new Picture("decoded.png");
            
            secret_Picture.explore();
        }
        catch(Exception e){System.out.println(e);}
    }

    /** Main method for testing.  Every class can have a main
     * method in Java */
    public static void main(String[] args)
    {
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run
        testZeroBlue();
        testKeepOnlyBlue();
        testKeepOnlyRed();
        testKeepOnlyGreen();
        testNegate();
        testGrayscale();
        testFixUnderwater();
        testMirrorVertical();
        testMirrorVerticalRightToLeft();
        testMirrorHorizontalBotToTop();
        testMirrorTemple();
        testMirrorArms();
        testMirrorGull();
        testMirrorDiagonal();
        testCollage();
        testMyCollage();
        testCopy();
        testPartialCopy();
        testEdgeDetectionHorizontal();
        testEdgeDetectionVertical();
        testEdgeDetectionDual();
        
        //Additional methods not in the lab
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
    }
}