package application;

import java.util.ArrayList;
import java.util.List;
import com.aktek.digitalsignage.entity.LayoutMedia;
import com.aktek.digitalsignage.entity.Region;
import com.aktek.digitalsignage.util.ClientConfig;
import com.aktek.digitalsignage.util.ComponentUtil;
import com.aktek.digitalsignage.util.XmlUtil;
import com.aktek.ws.client.xibo.WsClient4Xibo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	Group group;
	Scene scene;
	String layoutXml;
	Stage pStage;
	// xlf te degisiklik olup olmadigini kontrol etmek icin kullandigimiz
	// arrayList
	ArrayList<String> xlfArrayList = new ArrayList<>();
	// xlf i kontrol etmek icin koyulan sure
	int timeForControl = 15000;
	ClientConfig cf = new ClientConfig();
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		runApp(primaryStage);		

	}

	public void runApp(Stage stage) throws Exception {
		pStage = stage;
		setStage(pStage);

		try {
		
			cf.testConfig();
			layoutXml = XmlUtil.getFileContent("C:/Users/MUSTAFA/Desktop/Deneme/1.xlf");
			//C:\Users\aktekstajer\Documents\Deneme
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (xlfArrayList.isEmpty()) {
			xlfArrayList.add(layoutXml);
			createScene(pStage, layoutXml);
			createRegions(layoutXml);
			
		} else {
			if (xlfArrayList.get(0).equalsIgnoreCase(layoutXml)) {
				System.out.println("Xlf'de degisiklik olmadý!");
	
			} else {
				xlfArrayList.remove(0);
				xlfArrayList.add(layoutXml);
				createScene(pStage, layoutXml);
				createRegions(layoutXml);
				System.out.println("XDegisiklik!");
			}
		}

		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(timeForControl);
			} catch (InterruptedException exc) {
				throw new Error("Unexpected interruption", exc);
			}

			// Update text on FX Application Thread:
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("Init Stage...");
						runApp(pStage);
						

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		});
		thread.setDaemon(true);
		thread.start();
	}

	public void setStage(Stage pStage2) {
		pStage = pStage2;

	}

	public Stage getStage() {
		return pStage;
	}

	public static void main(String[] args) {

		launch(args);

	}

	public void createScene(Stage primaryStage, String layoutXml) throws Exception {
		Double width = Double.valueOf(XmlUtil.getValueByTagAttribute(layoutXml, "layout", "width", ""));
		Double height = Double.valueOf(XmlUtil.getValueByTagAttribute(layoutXml, "layout", "height", ""));
		String bgcolor = XmlUtil.getValueByTagAttribute(layoutXml, "layout", "bgcolor", "");

		// XmlUtil.getValueByTagAttribute(layoutXml,"layout","width","");

		group = new Group();
		scene = new Scene(group, width, height, Color.web(bgcolor));
		primaryStage.setScene(scene);

		primaryStage.setFullScreen(true);
		primaryStage.show();

	}

	public void createRegions(String layoutxml) throws Exception {
		List<Region> regionList = XmlUtil.getRegions(layoutxml);
		ComponentUtil compUtil = new ComponentUtil();

		for (Region region : regionList) {
			StackPane stackPane = new StackPane();
			stackPane.setLayoutX(region.getLeft());
			stackPane.setLayoutY(region.getTop());
			stackPane.setPrefWidth(region.getWidth());
			stackPane.setPrefHeight(region.getHeight());
			group.getChildren().add(stackPane);

			List<LayoutMedia> mediaList = region.getMediaList();

			for (LayoutMedia layoutMedia : mediaList) {
				compUtil.addMedia(group, stackPane, layoutMedia, region);
			}
		}
	}

}
