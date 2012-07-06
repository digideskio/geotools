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

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author matthijsln
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AxlFClass {
    public static final String TYPE_POINT = "point";
    public static final String TYPE_POLYGON = "polygon";
    public static final String TYPE_LINE = "line";
    
    @XmlAttribute
    private String type;
    
    @XmlElement(name="ENVELOPE")
    private AxlEnvelope envelope;
    
    @XmlElement(name="FIELD")
    private List<AxlFieldInfo> fields;

    public AxlEnvelope getEnvelope() {
        return envelope;
    }

    public void setEnvelope(AxlEnvelope envelope) {
        this.envelope = envelope;
    }

    public List<AxlFieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<AxlFieldInfo> fields) {
        this.fields = fields;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
