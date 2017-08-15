/**
 * WebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.rockstar.o2o.webservice.shortmessage;

public interface WebService extends javax.xml.rpc.Service {
    public java.lang.String getWebServiceSoap12Address();

    public com.rockstar.o2o.webservice.shortmessage.WebServiceSoap getWebServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com.rockstar.o2o.webservice.shortmessage.WebServiceSoap getWebServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getWebServiceSoapAddress();

    public com.rockstar.o2o.webservice.shortmessage.WebServiceSoap getWebServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.rockstar.o2o.webservice.shortmessage.WebServiceSoap getWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
