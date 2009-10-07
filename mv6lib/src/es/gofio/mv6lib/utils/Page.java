package es.gofio.mv6lib.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class Page {
	public static String getPage(String url) {
		try {
			return fetchPage(new URL(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getPage(URL url) {
		return fetchPage(url);
	}
	
	private static String fetchPage(URL url) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "ISO8859_1"));
			String page = "";
			String line;
			while((line = br.readLine()) != null) {
				page = page + line;
			}
			return page;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}
}
