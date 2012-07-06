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

import javax.xml.bind.annotation.*;

/**
 *
 * @author Matthijs Laan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AxlSpatialFilter {
    public static final String RELATION_AREA_INTERSECTION = "area_intersection";
    public static final String RELATION_ENVELOPE_INTERSECTION = "envelope_intersection";
    
    @XmlAttribute 
    private String relation = RELATION_AREA_INTERSECTION;
    
    @XmlElements({
        @XmlElement(name = "ENVELOPE", type = AxlEnvelope.class),
        @XmlElement(name = "POLYGON", type = AxlPolygon.class),
        @XmlElement(name = "MULTIPOINT", type = AxlMultiPoint.class),
        @XmlElement(name = "POLYLINE", type = AxlPolyline.class)
    })    
    private AxlGeometry geometryOrEnvelope;
    
    @XmlElement(name = "BUFFER")
    private AxlBuffer buffer;

    public AxlGeometry getGeometryOrEnvelope() {
        return geometryOrEnvelope;
    }

    public void setGeometryOrEnvelope(AxlGeometry geometryOrEnvelope) {
        this.geometryOrEnvelope = geometryOrEnvelope;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public AxlBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(AxlBuffer buffer) {
        this.buffer = buffer;
    }
}
