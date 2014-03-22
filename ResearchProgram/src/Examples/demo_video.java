package Examples;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.OpenCVFrameGrabber;
 
public class demo_video {
 public static void main(String[] args) {
  //Create canvas frame for displaying video.
     CanvasFrame canvas = new CanvasFrame("VideoCanvas"); 
   
  //Set Canvas frame to close on exit
     canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);   
      
     //Declare FrameGrabber to import video from "video.mp4"
     FrameGrabber grabber = new OpenCVFrameGrabber("video.mp4");  
      
      
     try {      
       
      //Start grabber to capture video
      grabber.start();      
       
      //Declare img as IplImage
      IplImage img;
       
      while (true) {
        
       //inser grabed video fram to IplImage img
       img = grabber.grab();
        
       //Set canvas size as per dimentions of video frame.
       canvas.setCanvasSize(grabber.getImageWidth(), grabber.getImageHeight()); 
        
       if (img != null) {       
        //Show video frame in canvas
        canvas.showImage(img);               
        }
       }
      }
     catch (Exception e) {      
     }
    }
}