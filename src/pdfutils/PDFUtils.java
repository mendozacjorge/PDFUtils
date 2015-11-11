package pdfutils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFUtils {

	static final float CONV_FACTOR = 72 / 2.54f;
	static final float WIDTH = 8.50f; //inches
	static final float HEIGHT = 11f; //inches

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {

			OutputStream os = new FileOutputStream("/wip/pdf/test.pdf");

			String[] imgs = new String[]{
								"/wip/pdf/cert_prof_p1.jpg",
								"/wip/pdf/cert_prof_p2.jpg"
//				"/wip/pdf/apostillado_cert_prepa.jpg"
			};

			printCentered(os, imgs);
//			printFullscreen(os, imgs);
			
		} catch (Exception ex) {
			Logger.getLogger(PDFUtils.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
		}

	}

	public static void printCentered(OutputStream destination, String[] imgs) {
		Document document = new Document(PageSize.LETTER);
		PdfWriter writer = null;

		try {
			writer = PdfWriter.getInstance(document, destination);

			document.open();

			for (String s : imgs) {
				Image image1 = Image.getInstance(s);
				image1.scaleToFit(19f * CONV_FACTOR, 25f * CONV_FACTOR);
				document.add(image1);
				document.newPage();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static void printFullscreen(OutputStream destination, String[] imgs) {

		Document document = new Document(PageSize.LETTER, 0, 0, 0, 0);
		PdfWriter writer = null;

		try {
			writer = PdfWriter.getInstance(document, destination);

			document.open();

			for (String s : imgs) {
				Image image1 = Image.getInstance(s);
				image1.scaleAbsolute(PageSize.LETTER);
				document.add(image1);
				document.newPage();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			if (writer != null) {
				writer.close();
			}
		}

	}
}
