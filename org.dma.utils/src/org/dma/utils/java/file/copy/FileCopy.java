/*******************************************************************************
 * 2011 Public Domain
 * Contributors
 * Marco Lopes (marcolopes@netc.pt)
 *******************************************************************************/
package org.dma.utils.java.file.copy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.dma.utils.java.file.FileUtils;
import org.dma.utils.java.file.HTTPUtils;

public abstract class FileCopy implements IFileCopy {

	/*
	 * Copy
	 */
	public Boolean copyFile(File src, File dst) {

		try{
			final InputStream bis =
				new BufferedInputStream(
						new FileInputStream(src));

			final OutputStream bos =
				new BufferedOutputStream(
						new FileOutputStream(dst));

			try{
				// Transfer bytes from input to output
				byte[] buf = new byte[1024];
				int len;
				while((len = bis.read(buf)) > 0 && !cancel())
					bos.write(buf, 0, len);

			}finally{
				FileUtils.close(bos);
				FileUtils.close(bis);
			}

			return cancel()? null : true;

		} catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		} catch (IOException e){
		}

		return false;
	}


	public boolean copyFile(String src, String dst) {

		return copyFile(new File(src), new File(dst));

	}


	/*
	 * Download
	 */
	public Boolean downloadFile(String src, String dst) {

		try{
			final BufferedInputStream bis =
				new BufferedInputStream(
					HTTPUtils.getInputStream(src));

			final OutputStream bos =
					new BufferedOutputStream(
							new FileOutputStream(dst));

			try{
				// Transfer bytes from input to output
				byte[] buf = new byte[1024];
				int len;
				while((len = bis.read(buf)) > 0 && !cancel())
					bos.write(buf, 0, len);

			}finally{
				FileUtils.close(bos);
				FileUtils.close(bis);
			}

			return cancel()? null : true;

		} catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		} catch (IOException e){
		}

		return false;

	}


}