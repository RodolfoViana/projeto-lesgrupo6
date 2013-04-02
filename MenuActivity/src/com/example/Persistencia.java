package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import android.os.Environment;


public class Persistencia {
	
	XStream xstream = new XStream();
	
	public void salvarDadosString(List<String> list, String arquivo) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getName() + File.separatorChar + "sf" + File.separatorChar + arquivo));
			String func = xstream.toXML(list);
			writer.write(func);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void salvarDadosDouble(List<Double> list, String arquivo) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getName() + File.separatorChar + "sf" + File.separatorChar + arquivo));
			String func = xstream.toXML(list);
			writer.write(func);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> recuperarDadosString(String arquivo) throws Exception {
		XStream xstream = new XStream(new DomDriver()); 
		FileReader reader = new FileReader(Environment.getExternalStorageDirectory().getName() + File.separatorChar + "sf" + File.separatorChar + arquivo);
		return ((List<String>) xstream.fromXML(reader)); 
	}
	
	public List<Double> recuperarDadosDouble(String arquivo) throws Exception {
		XStream xstream = new XStream(new DomDriver()); 
		FileReader reader = new FileReader(Environment.getExternalStorageDirectory().getName() + File.separatorChar + "sf" + File.separatorChar + arquivo);
		return ((List<Double>) xstream.fromXML(reader)); 
	}

	public void apagarDados(String arquivo) {
		File file = new File(Environment.getExternalStorageDirectory().getName() + File.separatorChar + "sf" + File.separatorChar + arquivo);
		
		file.delete();
	}

}
