/*******************************************************************************
 * 2008-2015 Public Domain
 * Contributors
 * Marco Lopes (marcolopes@netc.pt)
 * Sergio Gomes (s.miguelgomes@hotmail.com)
 *******************************************************************************/
package org.dma.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Collection;
import java.util.GregorianCalendar;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;

public class PdfFileHandler {

	public final File file;

	public PdfFileHandler(String filename) {
		this(new File(filename));
	}

	public PdfFileHandler(File file) {
		this.file=file;
	}


	/**
	 * <a href=http://itextpdf.sourceforge.net/howtosign.html>
	 * How to sign a PDF using iText</a>
	 */
	public void sign(KeyStore keyStore, String password,
			String reason, String location, String contact) throws Exception {

		FileInputStream fis=new FileInputStream(file);
		File output=new File(file+".tmp");

		String alias=keyStore.aliases().nextElement();
		PrivateKey privateKey=(PrivateKey)keyStore.getKey(alias, password.toCharArray());

		PdfStamper stamper=PdfStamper.createSignature(
				new PdfReader(fis),
				new FileOutputStream(output),
				'\0'); // keep pdf version

		PdfSignatureAppearance signature=stamper.getSignatureAppearance();
		signature.setCrypto(privateKey,
				keyStore.getCertificateChain(alias),
				null, PdfSignatureAppearance.WINCER_SIGNED);

		signature.setSignDate(new GregorianCalendar());
		signature.setReason(reason);
		signature.setLocation(location);
		signature.setContact(contact);
		//comment next line to have an invisible signature
		//signature.setVisibleSignature(new Rectangle(100, 100, 200, 200), 1, null);

		stamper.close();
		fis.close(); //PdfReader does not close stream
		file.delete();
		output.renameTo(file);

	}


	public void addScript(String script) throws Exception {

		FileInputStream fis=new FileInputStream(file);
		File output=new File(file+".tmp");

		PdfStamper stamper=new PdfStamper(
				new PdfReader(fis),
				new FileOutputStream(output));

		stamper.addJavaScript(script);

		stamper.close();
		fis.close(); //PdfReader does not close stream
		file.delete();
		output.renameTo(file);

	}


	/** Parameterized file will be used as OUTPUT */
	public void merge(Collection<File> files) throws Exception {

		PdfCopyFields copy=new PdfCopyFields(new FileOutputStream(file));

		for(File file: files){
			PdfReader reader=new PdfReader(file.getAbsolutePath());
			copy.addDocument(reader);
		}

		copy.close();

	}


}