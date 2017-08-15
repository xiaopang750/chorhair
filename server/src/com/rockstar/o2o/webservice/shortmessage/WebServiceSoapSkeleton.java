/**
 * WebServiceSoapSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.rockstar.o2o.webservice.shortmessage;

public class WebServiceSoapSkeleton implements com.rockstar.o2o.webservice.shortmessage.WebServiceSoap, org.apache.axis.wsdl.Skeleton {
    private com.rockstar.o2o.webservice.shortmessage.WebServiceSoap impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendSMS", _params, new javax.xml.namespace.QName("http://tempuri.org/", "SendSMSResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "SendSMS"));
        _oper.setSoapAction("http://tempuri.org/SendSMS");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendSMS") == null) {
            _myOperations.put("sendSMS", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendSMS")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendSMS_R", _params, new javax.xml.namespace.QName("http://tempuri.org/", "SendSMS_RResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "SendSMS_R"));
        _oper.setSoapAction("http://tempuri.org/SendSMS_R");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendSMS_R") == null) {
            _myOperations.put("sendSMS_R", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendSMS_R")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "subcode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendSMSEx", _params, new javax.xml.namespace.QName("http://tempuri.org/", "SendSMSExResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "SendSMSEx"));
        _oper.setSoapAction("http://tempuri.org/SendSMSEx");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendSMSEx") == null) {
            _myOperations.put("sendSMSEx", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendSMSEx")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "subcode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendSMSEx_R", _params, new javax.xml.namespace.QName("http://tempuri.org/", "SendSMSEx_RResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "SendSMSEx_R"));
        _oper.setSoapAction("http://tempuri.org/SendSMSEx_R");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendSMSEx_R") == null) {
            _myOperations.put("sendSMSEx_R", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendSMSEx_R")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("unRegister", _params, new javax.xml.namespace.QName("http://tempuri.org/", "UnRegisterResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "UnRegister"));
        _oper.setSoapAction("http://tempuri.org/UnRegister");
        _myOperationsList.add(_oper);
        if (_myOperations.get("unRegister") == null) {
            _myOperations.put("unRegister", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("unRegister")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "cardno"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "cardpwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("chargUp", _params, new javax.xml.namespace.QName("http://tempuri.org/", "ChargUpResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "ChargUp"));
        _oper.setSoapAction("http://tempuri.org/ChargUp");
        _myOperationsList.add(_oper);
        if (_myOperations.get("chargUp") == null) {
            _myOperations.put("chargUp", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("chargUp")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBalance", _params, new javax.xml.namespace.QName("http://tempuri.org/", "GetBalanceResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetBalance"));
        _oper.setSoapAction("http://tempuri.org/GetBalance");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBalance") == null) {
            _myOperations.put("getBalance", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBalance")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getStatus", _params, new javax.xml.namespace.QName("http://tempuri.org/", "GetStatusResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetStatus"));
        _oper.setSoapAction("http://tempuri.org/GetStatus");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getStatus") == null) {
            _myOperations.put("getStatus", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getStatus")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getCode", _params, new javax.xml.namespace.QName("http://tempuri.org/", "GetCodeResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetCode"));
        _oper.setSoapAction("http://tempuri.org/GetCode");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getCode") == null) {
            _myOperations.put("getCode", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getCode")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "newpwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("UDPPwd", _params, new javax.xml.namespace.QName("http://tempuri.org/", "UDPPwdResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "UDPPwd"));
        _oper.setSoapAction("http://tempuri.org/UDPPwd");
        _myOperationsList.add(_oper);
        if (_myOperations.get("UDPPwd") == null) {
            _myOperations.put("UDPPwd", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("UDPPwd")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("UDPSIGN", _params, new javax.xml.namespace.QName("http://tempuri.org/", "UDPSIGNResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "UDPSIGN"));
        _oper.setSoapAction("http://tempuri.org/UDPSIGN");
        _myOperationsList.add(_oper);
        if (_myOperations.get("UDPSIGN") == null) {
            _myOperations.put("UDPSIGN", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("UDPSIGN")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "subcode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "comName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("UDPSIGNEX", _params, new javax.xml.namespace.QName("http://tempuri.org/", "UDPSIGNEXResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "UDPSIGNEX"));
        _oper.setSoapAction("http://tempuri.org/UDPSIGNEX");
        _myOperationsList.add(_oper);
        if (_myOperations.get("UDPSIGNEX") == null) {
            _myOperations.put("UDPSIGNEX", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("UDPSIGNEX")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("RECSMS", _params, new javax.xml.namespace.QName("http://tempuri.org/", "RECSMSResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfMOBody"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "RECSMS"));
        _oper.setSoapAction("http://tempuri.org/RECSMS");
        _myOperationsList.add(_oper);
        if (_myOperations.get("RECSMS") == null) {
            _myOperations.put("RECSMS", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("RECSMS")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "subcode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("RECSMSEx", _params, new javax.xml.namespace.QName("http://tempuri.org/", "RECSMSExResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfMOBody"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "RECSMSEx"));
        _oper.setSoapAction("http://tempuri.org/RECSMSEx");
        _myOperationsList.add(_oper);
        if (_myOperations.get("RECSMSEx") == null) {
            _myOperations.put("RECSMSEx", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("RECSMSEx")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("RECSMS_UTF8", _params, new javax.xml.namespace.QName("http://tempuri.org/", "RECSMS_UTF8Result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfMOBody"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "RECSMS_UTF8"));
        _oper.setSoapAction("http://tempuri.org/RECSMS_UTF8");
        _myOperationsList.add(_oper);
        if (_myOperations.get("RECSMS_UTF8") == null) {
            _myOperations.put("RECSMS_UTF8", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("RECSMS_UTF8")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "subcode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("RECSMSEx_UTF8", _params, new javax.xml.namespace.QName("http://tempuri.org/", "RECSMSEx_UTF8Result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfMOBody"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "RECSMSEx_UTF8"));
        _oper.setSoapAction("http://tempuri.org/RECSMSEx_UTF8");
        _myOperationsList.add(_oper);
        if (_myOperations.get("RECSMSEx_UTF8") == null) {
            _myOperations.put("RECSMSEx_UTF8", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("RECSMSEx_UTF8")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "province"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "city"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "trade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "entname"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "linkman"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "phone"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "email"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "fax"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "postcode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("register", _params, new javax.xml.namespace.QName("http://tempuri.org/", "RegisterResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "Register"));
        _oper.setSoapAction("http://tempuri.org/Register");
        _myOperationsList.add(_oper);
        if (_myOperations.get("register") == null) {
            _myOperations.put("register", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("register")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getFlag", _params, new javax.xml.namespace.QName("http://tempuri.org/", "GetFlagResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetFlag"));
        _oper.setSoapAction("http://tempuri.org/GetFlag");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getFlag") == null) {
            _myOperations.put("getFlag", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getFlag")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("SMSTest", _params, new javax.xml.namespace.QName("http://tempuri.org/", "SMSTestResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "SMSTest"));
        _oper.setSoapAction("http://tempuri.org/SMSTest");
        _myOperationsList.add(_oper);
        if (_myOperations.get("SMSTest") == null) {
            _myOperations.put("SMSTest", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("SMSTest")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "code"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "type"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("testCode", _params, new javax.xml.namespace.QName("http://tempuri.org/", "TestCodeResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "TestCode"));
        _oper.setSoapAction("http://tempuri.org/TestCode");
        _myOperationsList.add(_oper);
        if (_myOperations.get("testCode") == null) {
            _myOperations.put("testCode", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("testCode")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getAllInfo", _params, new javax.xml.namespace.QName("http://tempuri.org/", "GetAllInfoResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "RegistryInfo"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetAllInfo"));
        _oper.setSoapAction("http://tempuri.org/GetAllInfo");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getAllInfo") == null) {
            _myOperations.put("getAllInfo", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getAllInfo")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ver"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "oem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getAllInfo2", _params, new javax.xml.namespace.QName("http://tempuri.org/", "GetAllInfo2Result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "RegistryInfo2"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetAllInfo2"));
        _oper.setSoapAction("http://tempuri.org/GetAllInfo2");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getAllInfo2") == null) {
            _myOperations.put("getAllInfo2", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getAllInfo2")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "gd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setGaoDuan", _params, new javax.xml.namespace.QName("http://tempuri.org/", "SetGaoDuanResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "SetGaoDuan"));
        _oper.setSoapAction("http://tempuri.org/SetGaoDuan");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setGaoDuan") == null) {
            _myOperations.put("setGaoDuan", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setGaoDuan")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getGaoDuan", _params, new javax.xml.namespace.QName("http://tempuri.org/", "GetGaoDuanResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetGaoDuan"));
        _oper.setSoapAction("http://tempuri.org/GetGaoDuan");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getGaoDuan") == null) {
            _myOperations.put("getGaoDuan", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getGaoDuan")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mt", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mtResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mt"));
        _oper.setSoapAction("http://tempuri.org/mt");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mt") == null) {
            _myOperations.put("mt", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mt")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdSmsSend", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSendResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend"));
        _oper.setSoapAction("http://tempuri.org/mdSmsSend");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdSmsSend") == null) {
            _myOperations.put("mdSmsSend", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdSmsSend")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdSmsSend_u", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend_uResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend_u"));
        _oper.setSoapAction("http://tempuri.org/mdSmsSend_u");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdSmsSend_u") == null) {
            _myOperations.put("mdSmsSend_u", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdSmsSend_u")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdSmsSend_DES", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend_DESResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend_DES"));
        _oper.setSoapAction("http://tempuri.org/mdSmsSend_DES");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdSmsSend_DES") == null) {
            _myOperations.put("mdSmsSend_DES", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdSmsSend_DES")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdSmsSend_AES", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend_AESResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend_AES"));
        _oper.setSoapAction("http://tempuri.org/mdSmsSend_AES");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdSmsSend_AES") == null) {
            _myOperations.put("mdSmsSend_AES", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdSmsSend_AES")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdSmsSend_g", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend_gResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdSmsSend_g"));
        _oper.setSoapAction("http://tempuri.org/mdSmsSend_g");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdSmsSend_g") == null) {
            _myOperations.put("mdSmsSend_g", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdSmsSend_g")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mo", _params, new javax.xml.namespace.QName("http://tempuri.org/", "moResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mo"));
        _oper.setSoapAction("http://tempuri.org/mo");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mo") == null) {
            _myOperations.put("mo", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mo")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "maxID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mo2", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mo2Result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mo2"));
        _oper.setSoapAction("http://tempuri.org/mo2");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mo2") == null) {
            _myOperations.put("mo2", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mo2")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "maxid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("report", _params, new javax.xml.namespace.QName("http://tempuri.org/", "reportResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "report"));
        _oper.setSoapAction("http://tempuri.org/report");
        _myOperationsList.add(_oper);
        if (_myOperations.get("report") == null) {
            _myOperations.put("report", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("report")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "maxid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("report2", _params, new javax.xml.namespace.QName("http://tempuri.org/", "report2Result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "report2"));
        _oper.setSoapAction("http://tempuri.org/report2");
        _myOperationsList.add(_oper);
        if (_myOperations.get("report2") == null) {
            _myOperations.put("report2", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("report2")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "maxid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("report2DES", _params, new javax.xml.namespace.QName("http://tempuri.org/", "report2DESResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "report2DES"));
        _oper.setSoapAction("http://tempuri.org/report2DES");
        _myOperationsList.add(_oper);
        if (_myOperations.get("report2DES") == null) {
            _myOperations.put("report2DES", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("report2DES")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("msgid", _params, new javax.xml.namespace.QName("http://tempuri.org/", "msgidResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "msgid"));
        _oper.setSoapAction("http://tempuri.org/msgid");
        _myOperationsList.add(_oper);
        if (_myOperations.get("msgid") == null) {
            _myOperations.put("msgid", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("msgid")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("balance", _params, new javax.xml.namespace.QName("http://tempuri.org/", "balanceResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "balance"));
        _oper.setSoapAction("http://tempuri.org/balance");
        _myOperationsList.add(_oper);
        if (_myOperations.get("balance") == null) {
            _myOperations.put("balance", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("balance")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("gxmt", _params, new javax.xml.namespace.QName("http://tempuri.org/", "gxmtResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "gxmt"));
        _oper.setSoapAction("http://tempuri.org/gxmt");
        _myOperationsList.add(_oper);
        if (_myOperations.get("gxmt") == null) {
            _myOperations.put("gxmt", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("gxmt")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "fname"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sort"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "total"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ftype"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("fileMT", _params, new javax.xml.namespace.QName("http://tempuri.org/", "fileMTResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "fileMT"));
        _oper.setSoapAction("http://tempuri.org/fileMT");
        _myOperationsList.add(_oper);
        if (_myOperations.get("fileMT") == null) {
            _myOperations.put("fileMT", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("fileMT")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mmsFileMT", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mmsFileMTResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mmsFileMT"));
        _oper.setSoapAction("http://tempuri.org/mmsFileMT");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mmsFileMT") == null) {
            _myOperations.put("mmsFileMT", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mmsFileMT")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "title"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdMmsSend", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsSendResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsSend"));
        _oper.setSoapAction("http://tempuri.org/mdMmsSend");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdMmsSend") == null) {
            _myOperations.put("mdMmsSend", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdMmsSend")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "title"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdMmsSend_ex", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsSend_exResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsSend_ex"));
        _oper.setSoapAction("http://tempuri.org/mdMmsSend_ex");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdMmsSend_ex") == null) {
            _myOperations.put("mdMmsSend_ex", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdMmsSend_ex")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "title"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdMmsSend_uex", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsSend_uexResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsSend_uex"));
        _oper.setSoapAction("http://tempuri.org/mdMmsSend_uex");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdMmsSend_uex") == null) {
            _myOperations.put("mdMmsSend_uex", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdMmsSend_uex")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdMmsSendF", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsSendFResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsSendF"));
        _oper.setSoapAction("http://tempuri.org/mdMmsSendF");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdMmsSendF") == null) {
            _myOperations.put("mdMmsSendF", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdMmsSendF")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "title"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "txt"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "srcnumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdAudioSend", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdAudioSendResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdAudioSend"));
        _oper.setSoapAction("http://tempuri.org/mdAudioSend");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdAudioSend") == null) {
            _myOperations.put("mdAudioSend", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdAudioSend")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "title"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "srcnumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdFaxSend", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdFaxSendResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdFaxSend"));
        _oper.setSoapAction("http://tempuri.org/mdFaxSend");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdFaxSend") == null) {
            _myOperations.put("mdFaxSend", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdFaxSend")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mdMmsReceive", _params, new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsReceiveResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "mdMmsReceive"));
        _oper.setSoapAction("http://tempuri.org/mdMmsReceive");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mdMmsReceive") == null) {
            _myOperations.put("mdMmsReceive", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mdMmsReceive")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pszMobis"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pszMsg"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "iMobiCount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pszSubPort"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mongateCsSpSendSmsNew", _params, new javax.xml.namespace.QName("http://tempuri.org/", "MongateCsSpSendSmsNewResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "MongateCsSpSendSmsNew"));
        _oper.setSoapAction("http://tempuri.org/MongateCsSpSendSmsNew");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mongateCsSpSendSmsNew") == null) {
            _myOperations.put("mongateCsSpSendSmsNew", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mongateCsSpSendSmsNew")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mongateCsGetSmsExEx", _params, new javax.xml.namespace.QName("http://tempuri.org/", "MongateCsGetSmsExExResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfString"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "MongateCsGetSmsExEx"));
        _oper.setSoapAction("http://tempuri.org/MongateCsGetSmsExEx");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mongateCsGetSmsExEx") == null) {
            _myOperations.put("mongateCsGetSmsExEx", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mongateCsGetSmsExEx")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mongateCsGetStatusReportExEx", _params, new javax.xml.namespace.QName("http://tempuri.org/", "MongateCsGetStatusReportExExResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfString"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "MongateCsGetStatusReportExEx"));
        _oper.setSoapAction("http://tempuri.org/MongateCsGetStatusReportExEx");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mongateCsGetStatusReportExEx") == null) {
            _myOperations.put("mongateCsGetStatusReportExEx", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mongateCsGetStatusReportExEx")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "sn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "mobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ext"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "stime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rrid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "bcode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("bianliang", _params, new javax.xml.namespace.QName("http://tempuri.org/", "bianliangResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://tempuri.org/", "bianliang"));
        _oper.setSoapAction("http://tempuri.org/bianliang");
        _myOperationsList.add(_oper);
        if (_myOperations.get("bianliang") == null) {
            _myOperations.put("bianliang", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("bianliang")).add(_oper);
    }

    public WebServiceSoapSkeleton() {
        this.impl = new com.rockstar.o2o.webservice.shortmessage.WebServiceSoapImpl();
    }

    public WebServiceSoapSkeleton(com.rockstar.o2o.webservice.shortmessage.WebServiceSoap impl) {
        this.impl = impl;
    }
    public java.lang.String sendSMS(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.sendSMS(sn, pwd, mobile, content);
        return ret;
    }

    public java.lang.String sendSMS_R(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.sendSMS_R(sn, pwd, mobile, content, rrid);
        return ret;
    }

    public java.lang.String sendSMSEx(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String subcode) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.sendSMSEx(sn, pwd, mobile, content, subcode);
        return ret;
    }

    public java.lang.String sendSMSEx_R(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String subcode, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.sendSMSEx_R(sn, pwd, mobile, content, subcode, rrid);
        return ret;
    }

    public java.lang.String unRegister(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.unRegister(sn, pwd);
        return ret;
    }

    public java.lang.String chargUp(java.lang.String sn, java.lang.String pwd, java.lang.String cardno, java.lang.String cardpwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.chargUp(sn, pwd, cardno, cardpwd);
        return ret;
    }

    public java.lang.String getBalance(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getBalance(sn, pwd);
        return ret;
    }

    public java.lang.String getStatus(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getStatus(sn, pwd);
        return ret;
    }

    public java.lang.String getCode(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getCode(sn, pwd);
        return ret;
    }

    public java.lang.String UDPPwd(java.lang.String sn, java.lang.String pwd, java.lang.String newpwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.UDPPwd(sn, pwd, newpwd);
        return ret;
    }

    public java.lang.String UDPSIGN(java.lang.String sn, java.lang.String pwd, java.lang.String sign) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.UDPSIGN(sn, pwd, sign);
        return ret;
    }

    public java.lang.String UDPSIGNEX(java.lang.String sn, java.lang.String pwd, java.lang.String subcode, java.lang.String sign, java.lang.String comName) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.UDPSIGNEX(sn, pwd, subcode, sign, comName);
        return ret;
    }

    public com.rockstar.o2o.webservice.shortmessage.MOBody[] RECSMS(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        com.rockstar.o2o.webservice.shortmessage.MOBody[] ret = impl.RECSMS(sn, pwd);
        return ret;
    }

    public com.rockstar.o2o.webservice.shortmessage.MOBody[] RECSMSEx(java.lang.String sn, java.lang.String pwd, java.lang.String subcode) throws java.rmi.RemoteException
    {
        com.rockstar.o2o.webservice.shortmessage.MOBody[] ret = impl.RECSMSEx(sn, pwd, subcode);
        return ret;
    }

    public com.rockstar.o2o.webservice.shortmessage.MOBody[] RECSMS_UTF8(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        com.rockstar.o2o.webservice.shortmessage.MOBody[] ret = impl.RECSMS_UTF8(sn, pwd);
        return ret;
    }

    public com.rockstar.o2o.webservice.shortmessage.MOBody[] RECSMSEx_UTF8(java.lang.String sn, java.lang.String pwd, java.lang.String subcode) throws java.rmi.RemoteException
    {
        com.rockstar.o2o.webservice.shortmessage.MOBody[] ret = impl.RECSMSEx_UTF8(sn, pwd, subcode);
        return ret;
    }

    public java.lang.String register(java.lang.String sn, java.lang.String pwd, java.lang.String province, java.lang.String city, java.lang.String trade, java.lang.String entname, java.lang.String linkman, java.lang.String phone, java.lang.String mobile, java.lang.String email, java.lang.String fax, java.lang.String address, java.lang.String postcode, java.lang.String sign) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.register(sn, pwd, province, city, trade, entname, linkman, phone, mobile, email, fax, address, postcode, sign);
        return ret;
    }

    public java.lang.String getFlag(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getFlag(sn, pwd);
        return ret;
    }

    public java.lang.String SMSTest(java.lang.String sn, java.lang.String pwd, java.lang.String mobile) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.SMSTest(sn, pwd, mobile);
        return ret;
    }

    public java.lang.String testCode(java.lang.String content, java.lang.String code, java.lang.String type) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.testCode(content, code, type);
        return ret;
    }

    public com.rockstar.o2o.webservice.shortmessage.RegistryInfo getAllInfo(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        com.rockstar.o2o.webservice.shortmessage.RegistryInfo ret = impl.getAllInfo(sn, pwd);
        return ret;
    }

    public com.rockstar.o2o.webservice.shortmessage.RegistryInfo2 getAllInfo2(java.lang.String sn, java.lang.String pwd, java.lang.String ver, java.lang.String oem) throws java.rmi.RemoteException
    {
        com.rockstar.o2o.webservice.shortmessage.RegistryInfo2 ret = impl.getAllInfo2(sn, pwd, ver, oem);
        return ret;
    }

    public double setGaoDuan(java.lang.String sn, java.lang.String pwd, java.lang.String gd) throws java.rmi.RemoteException
    {
        double ret = impl.setGaoDuan(sn, pwd, gd);
        return ret;
    }

    public java.lang.String getGaoDuan(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getGaoDuan(sn, pwd);
        return ret;
    }

    public java.lang.String mt(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String ext, java.lang.String stime, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mt(sn, pwd, mobile, content, ext, stime, rrid);
        return ret;
    }

    public java.lang.String mdSmsSend(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String ext, java.lang.String stime, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdSmsSend(sn, pwd, mobile, content, ext, stime, rrid);
        return ret;
    }

    public java.lang.String mdSmsSend_u(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String ext, java.lang.String stime, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdSmsSend_u(sn, pwd, mobile, content, ext, stime, rrid);
        return ret;
    }

    public java.lang.String mdSmsSend_DES(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String ext, java.lang.String stime, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdSmsSend_DES(sn, pwd, mobile, content, ext, stime, rrid);
        return ret;
    }

    public java.lang.String mdSmsSend_AES(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String ext, java.lang.String stime, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdSmsSend_AES(sn, pwd, mobile, content, ext, stime, rrid);
        return ret;
    }

    public java.lang.String mdSmsSend_g(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String ext, java.lang.String stime, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdSmsSend_g(sn, pwd, mobile, content, ext, stime, rrid);
        return ret;
    }

    public java.lang.String mo(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mo(sn, pwd);
        return ret;
    }

    public java.lang.String mo2(java.lang.String sn, java.lang.String pwd, int maxID) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mo2(sn, pwd, maxID);
        return ret;
    }

    public java.lang.String report(java.lang.String sn, java.lang.String pwd, long maxid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.report(sn, pwd, maxid);
        return ret;
    }

    public java.lang.String report2(java.lang.String sn, java.lang.String pwd, long maxid, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.report2(sn, pwd, maxid, rrid);
        return ret;
    }

    public java.lang.String report2DES(java.lang.String sn, java.lang.String pwd, long maxid, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.report2DES(sn, pwd, maxid, rrid);
        return ret;
    }

    public java.lang.String msgid(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.msgid(sn, pwd);
        return ret;
    }

    public java.lang.String balance(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.balance(sn, pwd);
        return ret;
    }

    public java.lang.String gxmt(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String ext, java.lang.String stime, java.lang.String rrid) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.gxmt(sn, pwd, mobile, content, ext, stime, rrid);
        return ret;
    }

    public java.lang.String fileMT(java.lang.String sn, java.lang.String pwd, java.lang.String fname, java.lang.String rrid, int sort, int total, int ftype, java.lang.String content) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.fileMT(sn, pwd, fname, rrid, sort, total, ftype, content);
        return ret;
    }

    public java.lang.String mmsFileMT(java.lang.String sn, java.lang.String pwd, java.lang.String rrid, java.lang.String content) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mmsFileMT(sn, pwd, rrid, content);
        return ret;
    }

    public java.lang.String mdMmsSend(java.lang.String sn, java.lang.String pwd, java.lang.String title, java.lang.String mobile, java.lang.String content, java.lang.String stime) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdMmsSend(sn, pwd, title, mobile, content, stime);
        return ret;
    }

    public java.lang.String mdMmsSend_ex(java.lang.String sn, java.lang.String pwd, java.lang.String title, java.lang.String mobile, java.lang.String content, java.lang.String stime, java.lang.String ext) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdMmsSend_ex(sn, pwd, title, mobile, content, stime, ext);
        return ret;
    }

    public java.lang.String mdMmsSend_uex(java.lang.String sn, java.lang.String pwd, java.lang.String title, java.lang.String mobile, java.lang.String content, java.lang.String stime, java.lang.String ext) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdMmsSend_uex(sn, pwd, title, mobile, content, stime, ext);
        return ret;
    }

    public java.lang.String mdMmsSendF(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String stime) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdMmsSendF(sn, pwd, mobile, content, stime);
        return ret;
    }

    public java.lang.String mdAudioSend(java.lang.String sn, java.lang.String pwd, java.lang.String title, java.lang.String mobile, java.lang.String txt, java.lang.String content, java.lang.String srcnumber, java.lang.String stime) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdAudioSend(sn, pwd, title, mobile, txt, content, srcnumber, stime);
        return ret;
    }

    public java.lang.String mdFaxSend(java.lang.String sn, java.lang.String pwd, java.lang.String title, java.lang.String mobile, java.lang.String content, java.lang.String srcnumber, java.lang.String stime) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdFaxSend(sn, pwd, title, mobile, content, srcnumber, stime);
        return ret;
    }

    public java.lang.String mdMmsReceive(java.lang.String sn, java.lang.String pwd) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mdMmsReceive(sn, pwd);
        return ret;
    }

    public java.lang.String mongateCsSpSendSmsNew(java.lang.String userId, java.lang.String password, java.lang.String pszMobis, java.lang.String pszMsg, int iMobiCount, java.lang.String pszSubPort) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mongateCsSpSendSmsNew(userId, password, pszMobis, pszMsg, iMobiCount, pszSubPort);
        return ret;
    }

    public java.lang.String[] mongateCsGetSmsExEx(java.lang.String userId, java.lang.String password) throws java.rmi.RemoteException
    {
        java.lang.String[] ret = impl.mongateCsGetSmsExEx(userId, password);
        return ret;
    }

    public java.lang.String[] mongateCsGetStatusReportExEx(java.lang.String userId, java.lang.String password) throws java.rmi.RemoteException
    {
        java.lang.String[] ret = impl.mongateCsGetStatusReportExEx(userId, password);
        return ret;
    }

    public java.lang.String bianliang(java.lang.String sn, java.lang.String pwd, java.lang.String mobile, java.lang.String content, java.lang.String ext, java.lang.String stime, java.lang.String rrid, java.lang.String bcode) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.bianliang(sn, pwd, mobile, content, ext, stime, rrid, bcode);
        return ret;
    }

}
