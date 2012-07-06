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
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Matthijs Laan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AxlFeatures {
    
    @XmlElement(name="FEATURE")
    List<AxlFeature> features;

    @XmlElement(name="FEATURECOUNT")
    AxlFeatureCount featureCount;
    
    public List<AxlFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<AxlFeature> features) {
        this.features = features;
    }

    public AxlFeatureCount getFeatureCount() {
        return featureCount;
    }

    public void setFeatureCount(AxlFeatureCount featureCount) {
        this.featureCount = featureCount;
    }
}
