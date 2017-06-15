package com.aktek.digitalsignage.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.aktek.digitalsignage.entity.Layout;
import com.aktek.digitalsignage.entity.LayoutMedia;
import com.aktek.digitalsignage.entity.OptionsMedia;
import com.aktek.digitalsignage.entity.OptionsRegion;
import com.aktek.digitalsignage.entity.Raw;
import com.aktek.digitalsignage.entity.Region;
import com.aktek.digitalsignage.entity.Tags;

public class XmlUtil  {

	public static String getValueByTagAttribute(String xml, String tag, String attribute, String type)
			throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));

		Document doc = dBuilder.parse(is);

		doc.getDocumentElement().normalize();

		Element element = (Element) doc.getElementsByTagName(tag).item(0);

		String result = element.getAttribute(attribute);

		return result;
	}

	public static String getFileContent(String path) throws Exception {
		File file = new File(path);

		FileInputStream fin = new FileInputStream(file);

		byte[] b = new byte[fin.available()];

		String result = "";
		fin.read(b);
		for (int i = 0; i < b.length; i++) {
			result += Character.toString((char) b[i]);
		}

		fin.close();

		return result;
	}

	public static List<Region> getRegions(String xml) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));

		Document doc = dBuilder.parse(is);

		doc.getDocumentElement().normalize();

		NodeList nodeList = doc.getElementsByTagName("region");

		List<Region> regionList = new ArrayList<Region>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				Region region = new Region();
				region.setWidth(Double.valueOf(element.getAttribute("width")));
				region.setHeight(Double.valueOf(element.getAttribute("height")));
				region.setTop(Double.valueOf(element.getAttribute("top")));
				region.setLeft(Double.valueOf(element.getAttribute("left")));
				region.setId(element.getAttribute("id"));

				List<LayoutMedia> mediaList = getMedia(xml, region.getId());

				region.setMediaList(mediaList);

				region.setOptions(getOptionsRegion(xml, region.getId()));

				regionList.add(region);
			}
		}

		return regionList;
	}

	public static Tags getTags(String xml) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));

		Document doc = dBuilder.parse(is);

		doc.getDocumentElement().normalize();

		NodeList nodeList = doc.getElementsByTagName("tags");

		Tags tags = new Tags();
		Node node = nodeList.item(0);

		Element element = (Element) node;

		String tag = element.getAttribute("tag");

		List<String> tagList = new ArrayList<String>();
		tagList.add(tag);
		tags.setTagList(tagList);

		return tags;
	}

	public static OptionsRegion getOptionsRegion(String xml, String id) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));

		Document doc = dBuilder.parse(is);

		doc.getDocumentElement().normalize();

		NodeList nodeList = doc.getElementsByTagName("region");

		OptionsRegion optionsRegion = new OptionsRegion();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				if (element.getAttribute("id").equals(id)) {

					List<Element> elementOptionsList = getElementList(element, "options");
					if (elementOptionsList.size() > 0) {
						Element elementOptions = elementOptionsList.get(0);
						String transOut = getFirstElementTextValueByList(getElementList(elementOptions, "transOut"));
						String transOutDuration = getFirstElementTextValueByList(
								getElementList(elementOptions, "transOutDuration"));

						optionsRegion.setTransOut(transOut);
						optionsRegion.setTransOutDuration(transOutDuration);
					}

					break;
				}
			}
		}

		return optionsRegion;
	}

	public static List<LayoutMedia> getMedia(String xml, String id) throws Exception {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));

		Document doc = dBuilder.parse(is);

		doc.getDocumentElement().normalize();

		NodeList nodeList = doc.getElementsByTagName("region");
		List<LayoutMedia> mediaList = new ArrayList<LayoutMedia>();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				if (element.getAttribute("id").equals(id)) {

					List<Element> elementMediaList = getElementList(element, "media");
					for (Element elementMedia : elementMediaList) {
						LayoutMedia media = new LayoutMedia();
						media.setDuration(elementMedia.getAttribute("duration"));
						media.setId(elementMedia.getAttribute("id"));
						media.setLkid(elementMedia.getAttribute("lkid"));
						media.setRender(elementMedia.getAttribute("render"));
						media.setSchemaVersion(elementMedia.getAttribute("schemaVersion"));
						media.setType(elementMedia.getAttribute("type"));

						OptionsMedia optionsMedia = new OptionsMedia();
						List<Element> elementOptionsList = getElementList(elementMedia, "options");
						if (elementOptionsList.size() > 0) {
							Element elementOptions = elementOptionsList.get(0);
							optionsMedia
									.setXmds(getFirstElementTextValueByList(getElementList(elementOptions, "xmds")));
							optionsMedia.setDirection(
									getFirstElementTextValueByList(getElementList(elementOptions, "direction")));
							optionsMedia.setEffect(
									getFirstElementTextValueByList(getElementList(elementOptions, "effect")));
							optionsMedia.setFitText(
									getFirstElementTextValueByList(getElementList(elementOptions, "fitText")));
							optionsMedia
									.setName(getFirstElementTextValueByList(getElementList(elementOptions, "name")));
							optionsMedia.setScroolSpeed(
									getFirstElementTextValueByList(getElementList(elementOptions, "scrollSpeed")));
							optionsMedia
									.setSpeed(getFirstElementTextValueByList(getElementList(elementOptions, "speed")));
							optionsMedia.setUri(getFirstElementTextValueByList(getElementList(elementOptions, "uri")));
							optionsMedia
									.setTheme(getFirstElementTextValueByList(getElementList(elementOptions, "theme")));
							optionsMedia.setOffset(
									getFirstElementTextValueByList(getElementList(elementOptions, "offset"))); // For
																												// Clock
							optionsMedia.setScaleType(
									getFirstElementTextValueByList(getElementList(elementOptions, "scaleType")));
							optionsMedia
									.setAlign(getFirstElementTextValueByList(getElementList(elementOptions, "align")));
							optionsMedia.setValign(
									getFirstElementTextValueByList(getElementList(elementOptions, "valign")));
							optionsMedia.setTransOutDuration(
									getFirstElementTextValueByList(getElementList(elementOptions, "transOutDuration")));
							optionsMedia.setTransOutDirection(getFirstElementTextValueByList(
									getElementList(elementOptions, "transOutDirection")));
							optionsMedia.setLoop(
									getFirstElementTextValueByList(getElementList(elementOptions, "loop")));
							optionsMedia.setMute(
									getFirstElementTextValueByList(getElementList(elementOptions, "mute")));
						}

						media.setOptions(optionsMedia);

						Raw raw = new Raw();
						List<Element> elementRawList = getElementList(elementMedia, "raw");
						if (elementRawList.size() > 0) {
							Element elementRaw = elementRawList.get(0);
							raw.setText(getFirstElementTextValueByList(getElementList(elementRaw, "text")));
						}

						media.setRaw(raw);

						mediaList.add(media);
					}

					break;
				}
			}
		}

		return mediaList;
	}

	public static Layout getLayoutByXml(String xml) throws Exception {
		Layout layout = new Layout();
		layout.setBackground(getValueByTagAttribute(xml, "layout", "background", ""));
		layout.setBgColor(getValueByTagAttribute(xml, "layout", "bgcolor", ""));
		layout.setHeight(getValueByTagAttribute(xml, "layout", "height", ""));
		layout.setWidth(getValueByTagAttribute(xml, "layout", "width", ""));
		layout.setResolutionid(getValueByTagAttribute(xml, "layout", "resolutionid", ""));
		layout.setSchemaVersion(getValueByTagAttribute(xml, "layout", "schemaVersion", ""));

		// Regions...
		layout.setRegionList(getRegions(xml));

		// Tags...
		layout.setTags(getTags(xml));

		return layout;
	}

	public static List<Element> getElementList(Element mainElement, String tagName) {
		List<Element> elementList = new ArrayList<Element>();
		Node childNode = mainElement.getFirstChild();
		while (childNode != null) {
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elementDtl = (Element) childNode;
				if (elementDtl.getTagName().equals(tagName)) {
					elementList.add(elementDtl);
				}
			}
			childNode = childNode.getNextSibling();
		}
		return elementList;
	}

	public static String getFirstElementTextValueByList(List<Element> elementList) {
		Element elementTmp = (Element) GlobalUtil.toObject4ListElement(elementList, 0);
		String elementTextValue = "";
		if (elementTmp != null) {
			elementTextValue = elementTmp.getTextContent();
		}

		return elementTextValue;
	}

	
}
