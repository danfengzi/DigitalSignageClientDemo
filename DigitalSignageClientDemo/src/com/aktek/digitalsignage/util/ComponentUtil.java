package com.aktek.digitalsignage.util;

import java.io.File;
import com.aktek.digitalsignage.entity.LayoutMedia;
import com.aktek.digitalsignage.entity.Region;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

public class ComponentUtil {

	ClientConfig clientConfig = new ClientConfig();

	public void addMedia(Group group, StackPane stackPane, LayoutMedia media, Region region) throws Exception {

		switch (media.getType()) {
		case "datasetview":
			System.out.println("Dataset okundu");
			WebView dataset = new WebView();
			WebEngine datasetJS = dataset.getEngine();
			datasetJS.setJavaScriptEnabled(true);
			File fileDataSet = new File(clientConfig.getFileLib() + media.getId() + ".htm");
			// System.out.println(clientConfig.getFileLib() + media.getId());
			datasetJS.load(fileDataSet.toURI().toString());

			stackPane.getChildren().add(dataset);

			break;

		case "embedded":
			// System.out.println("Embedded okundu");

			WebView embedded = new WebView();
			WebEngine embeddedJS = embedded.getEngine();
			embeddedJS.setJavaScriptEnabled(true);
			File fileEmbedded = new File(clientConfig.getFileLib() + media.getId() + ".htm");
			System.out.println(clientConfig.getFileLib() + media.getId());
			embeddedJS.load(fileEmbedded.toURI().toString());

			stackPane.getChildren().add(embedded);

			break;

		case "image":

			Image image = new Image("file:///" + clientConfig.getFileLib() + media.getOptions().getUri());
			ImageView iv1 = new ImageView(image);

			if (iv1.getImage().getWidth() >= region.getWidth()) {

				iv1.setFitWidth(region.getWidth());
			}
			if (iv1.getImage().getHeight() >= region.getHeight()) {

				iv1.setFitHeight(region.getHeight());
			}

			if (media.getOptions().getScaleType().equals("stretch")) {
				iv1.setFitWidth(region.getWidth());
				iv1.setFitHeight(region.getHeight());
			}

			else if (media.getOptions().getScaleType().equals("center")) {

				if (media.getOptions().getAlign().equals("left")) {

					if (media.getOptions().getValign().equals("top")) {
						stackPane.setAlignment(Pos.TOP_LEFT);
					} else if (media.getOptions().getValign().equals("middle")) {
						stackPane.setAlignment(Pos.CENTER_LEFT);
					} else if (media.getOptions().getValign().equals("bottom")) {
						stackPane.setAlignment(Pos.BOTTOM_LEFT);
					}
				}
				if (media.getOptions().getAlign().equals("center")) {

					if (media.getOptions().getValign().equals("top")) {
						stackPane.setAlignment(Pos.TOP_CENTER);
					} else if (media.getOptions().getValign().equals("middle")) {
						stackPane.setAlignment(Pos.BOTTOM_CENTER);
					} else if (media.getOptions().getValign().equals("bottom")) {
						stackPane.setAlignment(Pos.BOTTOM_CENTER);
					}
				}
				if (media.getOptions().getAlign().equals("right")) {

					if (media.getOptions().getValign().equals("top")) {
						stackPane.setAlignment(Pos.TOP_RIGHT);
					} else if (media.getOptions().getValign().equals("middle")) {
						stackPane.setAlignment(Pos.CENTER_RIGHT);
					} else if (media.getOptions().getValign().equals("bottom")) {
						stackPane.setAlignment(Pos.BOTTOM_RIGHT);
					}
				}

			}

			Timeline duration = new Timeline(new KeyFrame(Duration.seconds(Integer.valueOf(media.getDuration()))));
			duration.play();

			stackPane.getChildren().add(iv1);

			break;
		case "video":
			Media media1 = new Media("file:///" + clientConfig.getFileLib() + media.getOptions().getUri());
			MediaPlayer mediaPlayer = new MediaPlayer(media1);
			mediaPlayer.setAutoPlay(true);
			Timeline duration1 = new Timeline(new KeyFrame(Duration.seconds(Integer.valueOf(media.getDuration()))));
			duration1.play();
			if (media.getOptions().getLoop() == "1") {
				mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			}
			if (media.getOptions().getMute() == "1") {
				mediaPlayer.setMute(true);
			}

			mediaPlayer.setMute(true);
			MediaView mediaView = new MediaView(mediaPlayer);
			mediaView.setFitWidth(region.getWidth());
			mediaView.setFitHeight(region.getHeight());
			stackPane.getChildren().add(mediaView);
			break;

		case "webpage":
			String page = java.net.URLDecoder.decode(media.getOptions().getUri(), "UTF-8");
			WebView browser = new WebView();
			WebEngine webEngine1 = browser.getEngine();
			webEngine1.setJavaScriptEnabled(true);

			webEngine1.load(page);
			if (media.getOptions().getScaling() == "center") {
				browser.scaleXProperty();
				browser.scaleYProperty();
				browser.scaleZProperty();
			}
			webEngine1.reload();
			Timeline duration2 = new Timeline(new KeyFrame(Duration.seconds(Integer.valueOf(media.getDuration()))));
			duration2.play();
			stackPane.getChildren().add(browser);
			break;
		case "text":

			WebView text = new WebView();
			Timeline duration4;
			text.setVisible(false);

			WebEngine js = text.getEngine();
			js.setJavaScriptEnabled(true);
			File txt = new File(clientConfig.getFileLib() + media.getId() + ".htm");
			js.load(txt.toURI().toString());

			if (media.getOptions().getLoop() == "1") {
				duration4 = new Timeline(new KeyFrame(Duration.INDEFINITE));
			} else
				duration4 = new Timeline(new KeyFrame(Duration.seconds(Integer.valueOf(media.getDuration()))));

			/*
			 * Rectangle2D stageBounds = Screen.getPrimary().getVisualBounds();
			 * //final double marqueeWidth = 441.6; final double marqueeHeight =
			 * 165.6; TextFlow textFlow = new TextFlow(); double textWidth =
			 * textFlow.getLayoutBounds().getWidth();
			 * 
			 * String marquee_text_str = "DENEME 1 2 3"; Text marquee_text = new
			 * Text(marquee_text_str);
			 * 
			 * /*marquee_text.setFill(Color.YELLOW); marquee_text.setFont(font);
			 * textFlow.getChildren().addAll(marquee_text); textFlow.setStyle(
			 * "-fx-background-color: rgba(0, 0, 0, 0);");
			 * textFlow.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT); //
			 * Create initial and final keys KeyValue initkv = new
			 * KeyValue(textFlow.translateXProperty(), marqueeWidth);
			 * 
			 * KeyFrame initkf = new KeyFrame(Duration.ZERO, initkv); KeyValue
			 * endkv = new KeyValue(textFlow.translateXProperty(), -1.0 *
			 * textWidth); KeyFrame endkf = new
			 * KeyFrame(Duration.seconds(Integer.valueOf(media.getOptions().
			 * getSpeed())),endkv);
			 */

			duration4.play();
			stackPane.getChildren().add(text);

		case "clock":
			WebView clock = new WebView();
			WebEngine webEngine2 = new WebEngine();
			webEngine2 = clock.getEngine();
			webEngine2.setJavaScriptEnabled(true);

			File clk = new File(clientConfig.getFileLib() + media.getId() + ".htm");
			webEngine2.load(clk.toURI().toString());

			Timeline duration3 = new Timeline(new KeyFrame(Duration.seconds(Integer.valueOf(media.getDuration()))));
			duration3.play();
			stackPane.getChildren().add(clock);

			break;

		default:
			break;
		}

	}// BerkanK
}
