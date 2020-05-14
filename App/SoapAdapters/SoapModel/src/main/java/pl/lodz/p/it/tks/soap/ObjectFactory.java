//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.14 at 02:28:16 PM CEST 
//


package pl.lodz.p.it.tks.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.lodz.p.it.tks.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTablesRequest_QNAME = new QName("http://www.soap.tks.it.p.lodz.pl", "getTablesRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.lodz.p.it.tks.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PostTableRequest }
     * 
     */
    public PostTableRequest createPostTableRequest() {
        return new PostTableRequest();
    }

    /**
     * Create an instance of {@link GetResourceRequest }
     * 
     */
    public GetResourceRequest createGetResourceRequest() {
        return new GetResourceRequest();
    }

    /**
     * Create an instance of {@link DeleteResourceResponse }
     * 
     */
    public DeleteResourceResponse createDeleteResourceResponse() {
        return new DeleteResourceResponse();
    }

    /**
     * Create an instance of {@link PostBallRoomRequest }
     * 
     */
    public PostBallRoomRequest createPostBallRoomRequest() {
        return new PostBallRoomRequest();
    }

    /**
     * Create an instance of {@link PostTableResponse }
     * 
     */
    public PostTableResponse createPostTableResponse() {
        return new PostTableResponse();
    }

    /**
     * Create an instance of {@link PostBallRoomResponse }
     * 
     */
    public PostBallRoomResponse createPostBallRoomResponse() {
        return new PostBallRoomResponse();
    }

    /**
     * Create an instance of {@link GetResourceResponse }
     * 
     */
    public GetResourceResponse createGetResourceResponse() {
        return new GetResourceResponse();
    }

    /**
     * Create an instance of {@link ResourceSOAP }
     * 
     */
    public ResourceSOAP createResourceSOAP() {
        return new ResourceSOAP();
    }

    /**
     * Create an instance of {@link DeleteResourceRequest }
     * 
     */
    public DeleteResourceRequest createDeleteResourceRequest() {
        return new DeleteResourceRequest();
    }

    /**
     * Create an instance of {@link GetTablesResponse }
     * 
     */
    public GetTablesResponse createGetTablesResponse() {
        return new GetTablesResponse();
    }

    /**
     * Create an instance of {@link TableSOAP }
     * 
     */
    public TableSOAP createTableSOAP() {
        return new TableSOAP();
    }

    /**
     * Create an instance of {@link BallRoomSOAP }
     * 
     */
    public BallRoomSOAP createBallRoomSOAP() {
        return new BallRoomSOAP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.soap.tks.it.p.lodz.pl", name = "getTablesRequest")
    public JAXBElement<Object> createGetTablesRequest(Object value) {
        return new JAXBElement<Object>(_GetTablesRequest_QNAME, Object.class, null, value);
    }

}
