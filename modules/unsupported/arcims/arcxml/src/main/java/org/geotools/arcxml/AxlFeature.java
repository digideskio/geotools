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
import javax.xml.bind.annotation.*;

/**
 *
 * @author Matthijs Laan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AxlFeature {
    
    @XmlElementWrapper(name="FIELDS")
    @XmlElement(name="FIELD")
    List<AxlField> fields;
    
    @XmlElements({
        @XmlElement(name = "POLYGON", type = AxlPolygon.class),
        @XmlElement(name = "MULTIPOINT", type = AxlMultiPoint.class),
        @XmlElement(name = "POLYLINE", type = AxlPolyline.class)
    })    
    private AxlGeometry geometry;

    public List<AxlField> getFields() {
        return fields;
    }

    public void setFields(List<AxlField> fields) {
        this.fields = fields;
    }

    public AxlGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(AxlGeometry geometry) {
        this.geometry = geometry;
    }
}
