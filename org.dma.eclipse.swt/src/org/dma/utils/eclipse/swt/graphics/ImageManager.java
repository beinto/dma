/*******************************************************************************
 * 2008-2012 Public Domain
 * Contributors
 * Marco Lopes (marcolopes@netc.pt)
 *******************************************************************************/
package org.dma.utils.eclipse.swt.graphics;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dma.utils.java.awt.ImageUtils;
import org.eclipse.swt.graphics.Image;

public class ImageManager {

	private static final int MISSING_IMAGE_SIZE = 16;

	private static Map<String, Image> cacheMap=new HashMap();

	/**
	 * Returns the cached image or a new one if it does not exist;
	 * Cache Map key based on byte-array hash
	 */
	public static Image getImage(byte[] bytes) {
		String key=String.valueOf(Arrays.hashCode(bytes));
		Image image=cacheMap.get(key);
		if (image==null) {
			try{
				image=SWTImageUtils.createImage(bytes);
			} catch (Exception e){
				image=SWTImageUtils.createImage(MISSING_IMAGE_SIZE);
			}
			cacheMap.put(key, image);
		}

		return image;
	}


	/**
	 * Returns the cached image or a new one if it does not exist;
	 * Cache Map key based on path string
	 */
	public static Image getImage(String path) {
		Image image=cacheMap.get(path);
		if (image==null) {
			try{
				image=SWTImageUtils.createImage(path);
			} catch (Exception e){
				image=SWTImageUtils.createImage(MISSING_IMAGE_SIZE);
			}
			cacheMap.put(path, image);
		}

		return image;
	}


	/**
	 * Returns the cached image or a new one if it does not exist;
	 * Cache Map key based on image hash
	 */
	public static Image getImage(BufferedImage bufferedImage) {
		String key=String.valueOf(Arrays.hashCode(ImageUtils.getImagePixels(bufferedImage)));
		Image image=cacheMap.get(key);
		if (image==null) {
			try{
				image=SWTImageUtils.createImage(bufferedImage);
			} catch (Exception e){
				image=SWTImageUtils.createImage(MISSING_IMAGE_SIZE);
			}
			cacheMap.put(key, image);
		}

		return image;
	}


	/**
	 * Dispose all of the cached images
	 */
	public static void disposeImages() {
		debug();
		// dispose created images
		Iterator<Image> iterator=cacheMap.values().iterator();
		while(iterator.hasNext()){
			iterator.next().dispose();
		}
		cacheMap.clear();
	}


	/*
	 * Debug
	 */
	public static void debug() {

		System.out.println("IMAGE CACHE: " + cacheMap.size());
		Iterator<String> iterator=cacheMap.keySet().iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			System.out.println("key: " + key);
		}
	}


}