package edu.byu.cs.imageeditor.studentCode;

//import android.util.Log;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by jparker on 4/29/16.
 */
public class ImageEditor implements IImageEditor {

    private int width = 0;
    private int height = 0;
    private String header;
    private List<Pixel> _image;

    public void load(BufferedReader bufferedReader) {
        String line;
        String[] line_list;

        _image = new ArrayList<Pixel>();

        try {
            line = bufferedReader.readLine();
            line_list = line.split("\\s+");

            // Read until you get the height and the width.
            while(line.contains("\u0023") || line_list.length < 2){
                line = bufferedReader.readLine();
                line_list = line.split("\\s+");
//                Log.d("Debug", line);
            }

            // set height and width
            width = Integer.parseInt(line_list[0]);
            height = Integer.parseInt(line_list[1]);
            int max_val = Integer.parseInt(bufferedReader.readLine());
            header = String.format(Locale.US, "P3\n%d %d\n%d\n", width, height, max_val);

            // Debug: check the header
//             Log.d("Header Check", header.toString());

            for(int i=0; i<width*height; i++){
                int r = Integer.parseInt(bufferedReader.readLine());
                int g = Integer.parseInt(bufferedReader.readLine());
                int b = Integer.parseInt(bufferedReader.readLine());
                // create pixel
                Pixel pixel = new Pixel(r,g,b);
                _image.add(pixel);
            }

        } catch (Exception e){
            // Log.d("Exception", "Error in bufferedReader");
        }

    }

    private Pixel pixelXY(int x, int y) throws IndexOutOfBoundsException {
        if (x < width && y < height) {
            return _image.get(y * width + x);
        }
        else{
            throw new IndexOutOfBoundsException("Pixel out of range");
        }
    }

    public String invert() {
        // Log.d("Debug", "inverting...");
        StringBuilder imageString = new StringBuilder();
        imageString.append(header);
        Pixel tempPixel;
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                tempPixel = pixelXY(x,y);
                tempPixel.invert();
                imageString.append(tempPixel.r + "\n");
                imageString.append(tempPixel.g + "\n");
                imageString.append(tempPixel.b + "\n");
            }
        }
        return imageString.toString();
    }

    public String grayscale() {
        // Log.d("Debug", "grayscaling...");
        StringBuilder imageString = new StringBuilder(header);
        Pixel tempPixel;
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                tempPixel = pixelXY(x,y);
                tempPixel.grayscale();
                imageString.append(tempPixel.r + "\n");
                imageString.append(tempPixel.g + "\n");
                imageString.append(tempPixel.b + "\n");
            }
        }
        // Log.d("Debug", "done grayscaling");
        return imageString.toString();
    }

    public String emboss() {
        // Log.d("Debug", "embossing...");
        StringBuilder imageString = new StringBuilder(header);
        Pixel tempPixel;
        // set first row of pixels to 128
        for (int i = 0; i < width; i++){
            tempPixel = pixelXY(i,0);
            tempPixel.setTo(128,128,128);
            imageString.append(tempPixel.r + "\n");
            imageString.append(tempPixel.g + "\n");
            imageString.append(tempPixel.b + "\n");
        }
        for(int y = 1; y < height; y++){
            // set first pixel in row to 128
            tempPixel = pixelXY(0,y-1);
            tempPixel.setTo(128,128,128);
            imageString.append(tempPixel.r + "\n");
            imageString.append(tempPixel.g + "\n");
            imageString.append(tempPixel.b + "\n");
            for (int x = 1; x < width; x++){
                tempPixel = pixelXY(x,y);
                tempPixel.emboss(pixelXY(x-1,y-1));
                imageString.append(tempPixel.r + "\n");
                imageString.append(tempPixel.g + "\n");
                imageString.append(tempPixel.b + "\n");
            }
        }
        return imageString.toString();
    }

    public String motionblur(int blurLength){
        // Log.d("Debug", "blurring...");
        StringBuilder imageString = new StringBuilder(header);
        Pixel tempPixel;
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                tempPixel = pixelXY(x,y);
                tempPixel.reset();
                int limit = ((x + blurLength) <= width) ? blurLength : width - x;
                for(int k=1; k < limit; k++){
                    tempPixel.r += pixelXY(x+k,y)._r;
                    tempPixel.g += pixelXY(x+k,y)._g;
                    tempPixel.b += pixelXY(x+k,y)._b;
                }

                tempPixel.r = tempPixel.r/limit;
                tempPixel.g = tempPixel.g/limit;
                tempPixel.b = tempPixel.b/limit;

                imageString.append(tempPixel.r + "\n");
                imageString.append(tempPixel.g + "\n");
                imageString.append(tempPixel.b + "\n");
            }
        }
        return imageString.toString();
    }
}

class Pixel {
    public int _r;
    public int _g;
    public int _b;
    public int r;
    public int g;
    public int b;

    public Pixel(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
        this._r = r;
        this._g = g;
        this._b = b;
    }

    public Pixel(Pixel pixel){
        this(pixel.r, pixel.g, pixel.b);
    }

    public void reset(){
        r = _r;
        g = _g;
        b = _b;
    }

    public void setTo(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void invert(){
        r = 255 - _r;
        g = 255 - _g;
        b = 255 - _b;
    }

    public void grayscale(){
        int gray = (_r + _g + _b)/3;
        r = gray;
        g = gray;
        b = gray;
    }

    public void emboss(Pixel pixel){
        int max_diff;
        int _diff; // intermediate variable

        // calculate blue gradient
        // (blue is lowest priority)
        max_diff = this._b - pixel._b;

        // calculated green gradient
        _diff = this._g - pixel._g;
        if (Math.abs(_diff) >= Math.abs(max_diff)){
            max_diff = _diff;
        }

        // calculate red difference
        _diff = this._r - pixel._r;
        if (Math.abs(_diff) >= Math.abs(max_diff)){
            max_diff = _diff;
        }

        // set rgb values to max gradient value
        this.r = clip(max_diff + 128, 0, 255);
        this.g = clip(max_diff + 128, 0, 255);
        this.b = clip(max_diff + 128, 0, 255);
    }

    private int clip(int x, int min, int max){
        int val;
        if (x > max){
            val = max;
        }
        else if (x < min) {
            val = min;
        }
        else {
            val = x;
        }
        return val;
    }
}