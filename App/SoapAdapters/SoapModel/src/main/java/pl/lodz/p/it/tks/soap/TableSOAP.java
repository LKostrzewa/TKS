//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.20 at 05:09:54 PM CEST 
//


package pl.lodz.p.it.tks.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tableSOAP complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tableSOAP">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.soap.tks.it.p.lodz.pl}resourceSOAP">
 *       &lt;sequence>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numOfPeople" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tableSOAP", propOrder = {
    "number",
    "numOfPeople"
})
public class TableSOAP
    extends ResourceSOAP
{

    protected int number;
    protected int numOfPeople;

    /**
     * Gets the value of the number property.
     * 
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     */
    public void setNumber(int value) {
        this.number = value;
    }

    /**
     * Gets the value of the numOfPeople property.
     * 
     */
    public int getNumOfPeople() {
        return numOfPeople;
    }

    /**
     * Sets the value of the numOfPeople property.
     * 
     */
    public void setNumOfPeople(int value) {
        this.numOfPeople = value;
    }

}
