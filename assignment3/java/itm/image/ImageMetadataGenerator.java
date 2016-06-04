package itm.image;

/*******************************************************************************
    This file is part of the ITM course 2016
    (c) University of Vienna 2009-2016
*******************************************************************************/

import itm.model.ImageMedia;
import itm.model.MediaFactory;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
    This class reads images of various formats and stores some basic image meta data to text files.
    It can be called with 3 parameters, an input filename/directory, an output directory and an "overwrite" flag.
    It will read the input image(s), retrieve some meta data and write it to a text file in the output directory.
    The overwrite flag indicates whether the resulting output file should be overwritten or not.
    
    If the input file or the output directory do not exist, an exception is thrown.
*/
public class ImageMetadataGenerator {

    /**
        Constructor.
    */
    public ImageMetadataGenerator()
    {
    }
   

    /**
        Processes an image directory in a batch process.
        @param input a reference to the input image directory
        @param output a reference to the output directory
        @param overwrite indicates whether existing metadata files should be overwritten or not
        @return a list of the created media objects (images)
    */
    public ArrayList<ImageMedia> batchProcessImages( File input, File output, boolean overwrite ) throws IOException
    {
        if ( ! input.exists() ) 
            throw new IOException( "Input file " + input + " was not found!" );
        if ( ! output.exists() ) 
            throw new IOException( "Output directory " + output + " not found!" );
        if ( ! output.isDirectory() ) 
            throw new IOException( output + " is not a directory!" );

        ArrayList<ImageMedia> ret = new ArrayList<ImageMedia>();

        if ( input.isDirectory() ) {
            File[] files = input.listFiles();
            for ( File f : files ) {
                try {
                    ImageMedia result = processImage( f, output, overwrite );
                    System.out.println( "converted " + f + " to " + output );
                    ret.add( result );
                } catch ( Exception e0 ) {
                    System.err.println( "Error converting " + input + " : " + e0.toString() );
                    }
                 }
            } else {
                try {
                    ImageMedia result = processImage( input, output, overwrite );
                    System.out.println( "converted " + input + " to " + output );
                    ret.add( result );
                } catch ( Exception e0 ) {
                    System.err.println( "Error converting " + input + " : " + e0.toString() );
                    }
            }
        return ret;
    }    
    
    /**
        Processes the passed input image and stores the extracted metadata to a textfile in the output directory.
        @param input a reference to the input image
        @param output a reference to the output directory
        @param overwrite indicates whether existing metadata files should be overwritten or not
        @return the created image media object
    */
    protected ImageMedia processImage( File input, File output, boolean overwrite ) throws IOException, IllegalArgumentException
    {
        if ( ! input.exists() ) 
            throw new IOException( "Input file " + input + " was not found!" );
        if ( input.isDirectory() ) 
            throw new IOException( "Input file " + input + " is a directory!" );
        if ( ! output.exists() ) 
            throw new IOException( "Output directory " + output + " not found!" );
        if ( ! output.isDirectory() ) 
            throw new IOException( output + " is not a directory!" );

        // create outputfilename and check whether thumb already exists. All image 
        // metadata files have to start with "img_" -  this is used by the mediafactory!
        File outputFile = new File( output, "img_" + input.getName() + ".txt" );
        if ( outputFile.exists() ) 
            if ( ! overwrite ) {
                // load from file
                ImageMedia media = new ImageMedia();
                media.readFromFile( outputFile );
                return media;
                }


        // get metadata and store it to media object
        ImageMedia media = (ImageMedia) MediaFactory.createMedia( input );

        // ***************************************************************
        //  Fill in your code here!
        // ***************************************************************
ImageReader reader;
        
        // load the input image
        int i = input.getName().lastIndexOf('.');
        String inputFormat = input.getName().substring(i+1);
        
        Iterator<ImageReader> readerType = ImageIO.getImageReadersByFormatName(inputFormat);
        reader = readerType.next();
        
        BufferedImage image = null;
        
        ImageInputStream imageInput = ImageIO.createImageInputStream(input);
        reader.setInput(imageInput, true);
        
        image = reader.read(0);
        
        // set width and height of the image
        int width = image.getWidth();
        media.setWidth(width);
        int height = image.getHeight();
        media.setHeight(height);
        
        // add a tag "image" to the media
        media.addTag("image");
        
        // add a tag corresponding to the filename extension of the file to the media 
        media.addTag(inputFormat);
        
        //analyzing image, finding dominant color
        ArrayList<String> domColors_tags = calcDominantColors(image);
        for(String tag : domColors_tags){
        	System.out.println("Adding domColors_tag: " + tag);
        	media.addTag(tag);
        }
        
        // set orientation
        if(height>width){
        	media.setOrientation(ImageMedia.ORIENTATION_PORTRAIT);
        }
        else{
        	media.setOrientation(ImageMedia.ORIENTATION_LANDSCAPE);
        }
        // if there is a colormodel:
        if(image.getColorModel() != null){
        	ColorModel cMod = image.getColorModel();
        	// set color space type
        	int colSpace = cMod.getColorSpace().getType();
        	media.setColorSpaceType(colSpace);
        	// set pixel size
        	int pixelSize = cMod.getPixelSize();
        	media.setPixelSize(pixelSize);
        	// set transparency
        	int trans = cMod.getTransparency();
        	media.setTransparency(trans);
        	// set number of (color) components
        	int nColCom = cMod.getNumColorComponents();
        	media.setColorNumber(nColCom);
        	
        	// set number of components
        	int nComp = cMod.getNumComponents();
        	media.setComponentsNumber(nComp);
        }
        

        // store meta data
        
        media.writeToFile(outputFile);
        return media;
    }
    
        
    private ArrayList<String> calcDominantColors(BufferedImage image) {
    	ArrayList<String> tags = new ArrayList<String>(1);
    	
    	ColorModel col_mod = image.getColorModel();
    	long redCount = 0, blueCount = 0, greenCount = 0;
    	
    	try{
    		
			if (col_mod.getColorSpace().getType() == ColorSpace.TYPE_RGB){
				for(int i = 0; i < image.getHeight(); i++){
					for(int j = 0; j < image.getWidth(); j++){
						// read the pixel values and extract the color information
						Color color = new Color(image.getRGB(j, i));
						
						int redRetrieved = color.getRed();
						int greenRetrieved = color.getGreen();
						int blueRetrieved = color.getGreen();
						
						if(	(redRetrieved == 255 &&
							greenRetrieved == 255 &&
							blueRetrieved == 255) 
								||
							(redRetrieved == 0 &&
							greenRetrieved == 0 &&
							blueRetrieved == 0) )
								continue;
						
						
						redCount += color.getRed();
						greenCount += color.getGreen();
						blueCount += color.getBlue();
						
					}
				}
				
				long blueORgreenMax = Math.max(blueCount, greenCount);
				long max = Math.max(blueORgreenMax, redCount);
				
				//adding the most dominant color
				if(max == blueCount)
					tags.add("blue");
				else
					if(max == greenCount)
						tags.add("green");
					else 
						tags.add("red");
				
				float redPerc = (max != redCount) ? redCount / (max / 100.0f) : - 1;
				float bluePerc = (max != blueCount) ? blueCount / (max / 100.0f) : - 1;
				float greenPerc = (max != greenCount) ? greenCount / (max / 100.0f) : -1;
				
				final float THRESHOLD = 10.0f;
				//adding colors that are below the threshold
				if(max != blueCount && 100.0f-bluePerc < THRESHOLD)
					tags.add("blue");
				else
					if(max != greenCount && 100.0f-greenPerc < THRESHOLD)
						tags.add("green");
					else 
						if(max != redCount && 100.0f-redPerc < THRESHOLD)
						tags.add("red");
				
				
				
			} else if (col_mod.getColorSpace().getType() == ColorSpace.TYPE_GRAY){
				//do nothing
			} else
					throw new Exception("Unsupported color space!");
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return tags;
	}


	/**
        Main method. Parses the commandline parameters and prints usage information if required.
    */
    public static void main( String[] args ) throws Exception
    {
        if ( args.length < 2 ) {
            System.out.println( "usage: java itm.image.ImageMetadataGenerator <input-image> <output-directory>" );
            System.out.println( "usage: java itm.image.ImageMetadataGenerator <input-directory> <output-directory>" );
            System.exit( 1 );
            }
        File fi = new File( args[0] );
        File fo = new File( args[1] );
        ImageMetadataGenerator img = new ImageMetadataGenerator();
        img.batchProcessImages( fi, fo, true );        
    }    
}
