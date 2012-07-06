/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2012, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.data.arcims;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.xml.bind.JAXBException;
import org.geotools.data.ows.AbstractRequest;
import org.geotools.data.ows.HTTPResponse;
import org.geotools.data.ows.Response;
import org.geotools.ows.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geotools.arcxml.ArcXML;
import org.geotools.arcxml.AxlRequest;
import org.geotools.arcxml.AxlResponse;

/**
 *
 * @author Matthijs Laan, B3Partners
 */
public class ArcXMLRequest extends AbstractRequest {
    private static final Log log = LogFactory.getLog(ArcXMLRequest.class);

    AxlRequest request;
    
    public ArcXMLRequest(URL onlineResource, String serviceName, AxlRequest request) {
        super(onlineResource, new java.util.Properties());
        this.request = request;
        if(serviceName != null) {
            properties.setProperty("ServiceName", serviceName);
        }
        properties.setProperty("ClientVersion", "4.0");
        properties.setProperty("Form", "false");
        properties.setProperty("Encode", "false");
    }
        
    @Override
    protected void initRequest() {
    }

    @Override
    protected void initService() {
    }

    @Override
    protected void initVersion() {
    }
    
    @Override
    protected String processKey(String key) {
        if("SERVICENAME".equals(key)) {
            return "ServiceName";
        } else {
            return key;
        }
    }
    
    @Override
    public void performPostOutput(OutputStream out) throws IOException {      
        try {
            ArcXML.getJaxbContext().createMarshaller().marshal(new ArcXML(request), out);
        } catch (JAXBException ex) {
            throw new IOException("Cannot marshal request", ex);
        }
    }

    @Override
    public Response createResponse(HTTPResponse httpr) throws ServiceException, IOException {
        throw new UnsupportedOperationException("Not supported");
    }
    
    public AxlResponse parseResponse(HTTPResponse httpr) throws Exception {
        long startTime = System.currentTimeMillis();
        ArcXML axl = (ArcXML)ArcXML.getJaxbContext().createUnmarshaller().unmarshal(httpr.getResponseStream());
        if(log.isDebugEnabled()) {
            log.debug("ArcXML unmarshal time (includes server time): " + (System.currentTimeMillis() - startTime) + " ms");
        }
        
        return axl.getResponse();
    }
}
