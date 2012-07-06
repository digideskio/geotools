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
 * @author matthijsln
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AxlGetServiceInfo implements AxlRequest {
    
    @XmlAttribute
    private boolean envelope = true;
    
    @XmlAttribute
    private boolean extensions = false;
    
    @XmlAttribute
    private boolean fields = true;
    
    @XmlAttribute
    private boolean renderer = false;

    public boolean isEnvelope() {
        return envelope;
    }

    public void setEnvelope(boolean envelope) {
        this.envelope = envelope;
    }

    public boolean isExtensions() {
        return extensions;
    }

    public void setExtensions(boolean extensions) {
        this.extensions = extensions;
    }

    public boolean isFields() {
        return fields;
    }

    public void setFields(boolean fields) {
        this.fields = fields;
    }

    public boolean isRenderer() {
        return renderer;
    }

    public void setRenderer(boolean renderer) {
        this.renderer = renderer;
    }       
}
