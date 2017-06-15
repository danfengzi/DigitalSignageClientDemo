package com.aktek.digitalsignage.util;

import com.aktek.ws.client.xibo.WsClient4Xibo;

public class ClientConfig {

	public String fileLib = "C:/Users/MUSTAFA/Desktop/XiboLibrary/";

	public String requiredFilesLocation = "C:\\Users\\MUSTAFA\\Desktop\\XiboLibrary\\requiredFiles.xml";
	public String downloadRequiredFilesLocation = "C:\\Users\\MUSTAFA\\Desktop\\Deneme";
	private static final String serverKey = "z2xNLR";
	private static final String hwKey = "ed2263bea2d5bb7da5ec59cd385f7acf";
										
	public void testConfig() {
		try {
			System.out.println("Begin Test");
			new WsClient4Xibo().createRequiredFilesXmlFile(serverKey, hwKey, "", requiredFilesLocation);

			new WsClient4Xibo().downloadRequiredFiles(serverKey, hwKey, "", downloadRequiredFilesLocation,requiredFilesLocation);
			System.out.println("End Test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFileLib() {
		return fileLib;
	}

	public void setFileLib(String fileLib) {
		this.fileLib = fileLib;
	}
}
