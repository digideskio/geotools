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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author matthijsln
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AxlResponse {
    @XmlElement(name="SERVICEINFO")
    private AxlServiceInfo serviceInfo;
    
    @XmlElement(name="FEATURES")
    private AxlFeatures features;
    
    @XmlElement(name="ERROR")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public AxlServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(AxlServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public AxlFeatures getFeatures() {
        return features;
    }

    public void setFeatures(AxlFeatures features) {
        this.features = features;
    }       
}
