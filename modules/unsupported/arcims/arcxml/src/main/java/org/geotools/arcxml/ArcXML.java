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
package org.geotools.arcxml;

import java.util.Collections;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;

/**
 * JAXB root binding for ArcXML. Not all of the possible XML elements have been
 * mapped, only the interesting ones needed to read features. Extend this 
 * package if you want to do a GET_IMAGE request, for example.
 * 
 * These mappings were not created with reading .axl files in mind but could 
 * perhaps be used for that.
 * 
 * @author matthijsln
 */
@XmlRootElement(name="ARCXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArcXML {
    private static JAXBContext jaxbContext = null;
    private static JAXBException initException;
    static {
        try {
            // JAXBContext is thread-safe and should not be recreated for each 
            // request as it may define classes which use perm gen
            jaxbContext = JAXBContext.newInstance(ArcXML.class);
        } catch(JAXBException e) {
            initException = e;
        }
    }    
    
    @XmlAttribute
    protected String version = "1.1";
    
    @XmlElementWrapper(name="REQUEST")
    @XmlElements({
        @XmlElement(name = "GET_SERVICE_INFO", type = AxlGetServiceInfo.class),
        @XmlElement(name = "GET_FEATURES", type = AxlGetFeatures.class)
    })
    protected Set<AxlRequest> requests;
    
    @XmlElement(name="RESPONSE")
    protected AxlResponse response;
    
    public ArcXML() {
    }
    
    public ArcXML(AxlRequest request) {
        requests = Collections.singleton(request);
    }
    
    public AxlResponse getResponse() {
        return response;
    }

    public void setResponse(AxlResponse response) {
        this.response = response;
    }

    public static JAXBContext getJaxbContext() {
        if(initException != null) {
            throw new ExceptionInInitializerError(initException);
        }
        return jaxbContext;
    }
}
