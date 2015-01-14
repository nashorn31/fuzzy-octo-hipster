package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XMLParseable {

	public Document toXML(Document doc, Element rootElement);

}
