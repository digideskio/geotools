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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author matthijsln
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AxlLayerInfo {
    public static final String TYPE_FEATURECLASS    = "featureclass";
    public static final String TYPE_IMAGE           = "image";
    public static final String TYPE_ACETATE         = "acetate";
    
    @XmlAttribute
    private String id;
    
    @XmlAttribute
    private String name;
    
    @XmlAttribute
    private String type;
    
    @XmlAttribute
    private String maxscale;
    
    @XmlAttribute
    private String minscale;
            
    @XmlAttribute
    private Boolean visible;
    
    @XmlElement(name="FCLASS")
    private AxlFClass fclass;

    public AxlLayerInfo() {
    }
    
    public AxlLayerInfo(String id) {
        this.id = id;
    }

    public AxlFClass getFclass() {
        return fclass;
    }

    public void setFclass(AxlFClass fclass) {
        this.fclass = fclass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxscale() {
        return maxscale;
    }

    public void setMaxscale(String maxscale) {
        this.maxscale = maxscale;
    }

    public String getMinscale() {
        return minscale;
    }

    public void setMinscale(String minscale) {
        this.minscale = minscale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }    
}
