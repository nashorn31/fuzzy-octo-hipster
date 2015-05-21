package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XMLParseable {

	/**
	 * Implements a method to cast the object into xml format.
	 * @param doc the xml document which the object should be added
	 * @param rootElement the element on which the object should be added
	 * @return the xml document with the object added
	 */
	public Document toXML(Document doc, Element rootElement);

}
