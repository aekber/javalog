package org.ekber.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

	public static void generateQRCode(String url) {
	    Charset charset = Charset.forName("ISO-8859-1");
	    CharsetEncoder encoder = charset.newEncoder();
	    byte[] b = null;
	    
	    if(url != null){
	    	
		    try {
		        // Convert a string to ISO-8859-1 bytes in a ByteBuffer
		        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(url));
		        b = bbuf.array();
		    } catch (CharacterCodingException e) {
		        System.out.println(e.getMessage());
		    }
	
		    String data = null;
		    try {
		        data = new String(b, "ISO-8859-1");
		    } catch (UnsupportedEncodingException e) {
		        System.out.println(e.getMessage());
		    }
	
		    // get a byte matrix for the data
		    BitMatrix matrix = null;
		    int h = 100;
		    int w = 100;
		    Writer writer = new QRCodeWriter();
		    try {
		        matrix = writer.encode(data, BarcodeFormat.QR_CODE, w, h);
		    } catch (WriterException e) {
		        System.out.println(e.getMessage());
		    }
	
		    String filePath = "C:/Users/N50944/Desktop/javalog-new/javalog-parent/javalog-web/src/main/webapp/img/qrcode.png";
		    File file = new File(filePath);
		    try {
		        MatrixToImageWriter.writeToFile(matrix, "PNG", file);
		    } catch (IOException e) {
		        System.out.println(e.getMessage());
		    }
		}else{
			System.out.println("Sayfa url i null olamaz...");
		}
	}
}
