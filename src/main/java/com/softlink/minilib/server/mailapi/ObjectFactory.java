
package com.softlink.minilib.server.mailapi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.itpro.sendmail.soap.client.gen package. 
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

    private final static QName _SendMailWithCCResponse_QNAME = new QName("http://sendmailservice.itpro.com/", "sendMailWithCCResponse");
    private final static QName _SendMailWithCC_QNAME = new QName("http://sendmailservice.itpro.com/", "sendMailWithCC");
    private final static QName _SendMailResponse_QNAME = new QName("http://sendmailservice.itpro.com/", "sendMailResponse");
    private final static QName _SendMail_QNAME = new QName("http://sendmailservice.itpro.com/", "sendMail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.itpro.sendmail.soap.client.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendMail }
     * 
     */
    public SendMail createSendMail() {
        return new SendMail();
    }

    /**
     * Create an instance of {@link SendMailWithCC }
     * 
     */
    public SendMailWithCC createSendMailWithCC() {
        return new SendMailWithCC();
    }

    /**
     * Create an instance of {@link SendMailResponse }
     * 
     */
    public SendMailResponse createSendMailResponse() {
        return new SendMailResponse();
    }

    /**
     * Create an instance of {@link SendMailWithCCResponse }
     * 
     */
    public SendMailWithCCResponse createSendMailWithCCResponse() {
        return new SendMailWithCCResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMailWithCCResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendmailservice.itpro.com/", name = "sendMailWithCCResponse")
    public JAXBElement<SendMailWithCCResponse> createSendMailWithCCResponse(SendMailWithCCResponse value) {
        return new JAXBElement<SendMailWithCCResponse>(_SendMailWithCCResponse_QNAME, SendMailWithCCResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMailWithCC }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendmailservice.itpro.com/", name = "sendMailWithCC")
    public JAXBElement<SendMailWithCC> createSendMailWithCC(SendMailWithCC value) {
        return new JAXBElement<SendMailWithCC>(_SendMailWithCC_QNAME, SendMailWithCC.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendmailservice.itpro.com/", name = "sendMailResponse")
    public JAXBElement<SendMailResponse> createSendMailResponse(SendMailResponse value) {
        return new JAXBElement<SendMailResponse>(_SendMailResponse_QNAME, SendMailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendmailservice.itpro.com/", name = "sendMail")
    public JAXBElement<SendMail> createSendMail(SendMail value) {
        return new JAXBElement<SendMail>(_SendMail_QNAME, SendMail.class, null, value);
    }

}
