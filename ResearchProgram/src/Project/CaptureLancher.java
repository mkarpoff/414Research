package Project;

import java.io.File;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class CaptureLancher {
	public static void main(String[] args) {
		UI gui = new UI();
		File outb = new File("out/outb.yuv");
		File outf = new File("out/outf.yuv");
		Transmitter transmitter = new Transmitter();
		Analyzer analyzer;
		FrameGrabber frameGrabber;
		IplImage origImage, backImage, faceImage ;
		FaceStream stream = new FaceStream();
		
		
		try {
			
			frameGrabber = transmitter.receiveStream();
			analyzer = new Analyzer();
			origImage = frameGrabber.grab();
			transmitter.initializeRecorders(outb, outf, origImage);
			backImage = origImage.clone();
			faceImage = origImage.clone();
			
			while(gui.isVisible()){
				origImage = frameGrabber.grab();
				backImage = origImage.clone();
				analyzer.separateStreams(origImage, backImage, faceImage, stream);
				gui.putFrame(origImage, backImage, faceImage);
				transmitter.videoBuilder(backImage, faceImage);

			}
			stream.toFile();
			gui.destroy();
			transmitter.close();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			gui.destroy();
		} catch (ClassiferLoadFailure e) {
			e.printStackTrace();
			gui.destroy();
		} catch (com.googlecode.javacv.FrameRecorder.Exception e) {
			e.printStackTrace();
			gui.destroy();
		}

	}


}