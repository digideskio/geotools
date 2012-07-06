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
import org.geotools.arcxml.AxlFClass;
import org.geotools.arcxml.AxlField;
import org.geotools.arcxml.AxlFieldInfo;
import org.geotools.arcxml.AxlLayerInfo;
import org.geotools.data.FeatureReader;
import org.geotools.data.Query;
import org.geotools.data.QueryCapabilities;
import org.geotools.data.store.ContentEntry;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.sort.SortBy;

/**
 *
 * @author Matthijs Laan, B3Partners
 */
public class ArcIMSFeatureSource extends ContentFeatureSource {
    
    public ArcIMSFeatureSource(ContentEntry ce) {
        super(ce, null);
    }
    
    @Override
    public QueryCapabilities buildQueryCapabilities() {        
        return new QueryCapabilities() {
            @Override
            public boolean isJoiningSupported() {
                return false;
            }
            
            @Override
            public boolean isOffsetSupported() {
                return true;
            }
            
            @Override
            public boolean isReliableFIDSupported() {
                return true;
            }
            
            @Override
            public boolean isVersionSupported() {
                return false;
            }
            
            @Override 
            public boolean supportsSorting(SortBy[] attributes) {
                return false;
            }                    
        };       
    }
    
    @Override
    protected boolean canFilter() {
        return true;
    }        
    
    @Override
    protected boolean canLimit() {
        return true;
    }
    
    @Override
    protected boolean canOffset() {
        return true;
    }
    
    protected ArcIMSDataStore getArcIMSDataStore() {
        return (ArcIMSDataStore)getDataStore();
    }
    
    @Override
    protected ReferencedEnvelope getBoundsInternal(Query query) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override   
    protected int getCountInternal(Query query) throws IOException {
        return new ArcIMSFeatureReader(this, query).getCount();
    }

    @Override
    protected FeatureReader<SimpleFeatureType, SimpleFeature> getReaderInternal(Query query) throws IOException {
        return new ArcIMSFeatureReader(this, query);
    }

    @Override
    protected SimpleFeatureType buildFeatureType() throws IOException {       
        AxlLayerInfo al = getArcIMSDataStore().getAxlLayerInfo(entry.getTypeName());
        AxlFClass fc = al.getFclass();
        
        SimpleFeatureTypeBuilder b = new SimpleFeatureTypeBuilder();
        b.setName(al.getId());       
        
        for(AxlFieldInfo f: fc.getFields()) {
            if(f.getType() == AxlField.TYPE_SHAPE) {
                b.add(f.getName(), f.getBinding(fc), getArcIMSDataStore().getCRS());
            } else {
                b.add(f.getName(), f.getBinding(fc));
            }
        }

        return b.buildFeatureType();
    }
    
    protected String findRowIdAttribute() throws IOException {
        AxlLayerInfo al = getArcIMSDataStore().getAxlLayerInfo(entry.getTypeName());
        AxlFClass fc = al.getFclass();
        for(AxlFieldInfo f: fc.getFields()) {
            if(AxlField.TYPE_ROW_ID == f.getType()) {
                return f.getName();
            }
        }
        return null;
    }    
}
