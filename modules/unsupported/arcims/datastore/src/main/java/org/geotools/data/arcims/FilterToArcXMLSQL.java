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

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import java.io.IOException;
import java.io.Writer;
import org.geotools.arcxml.ArcXMLUtils;
import org.geotools.arcxml.AxlBuffer;
import org.geotools.arcxml.AxlSpatialFilter;
import org.geotools.arcxml.AxlSpatialQuery;
import org.geotools.data.jdbc.FilterToSQL;
import org.geotools.filter.FilterCapabilities;
import org.geotools.filter.IsNullImpl;
import org.opengis.filter.*;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Literal;
import org.opengis.filter.expression.PropertyName;
import org.opengis.filter.spatial.*;

/**
 * The "SQL" in ArcXML is not specified further other than it being SQL of some
 * sort. Any spatial query in the filter is saved in separate ArcXML elements in
 * the AxlSpatialQuery object.
 * <p>
 * Throws exceptions when the filter obviously is not supported by ArcIMS.
 *
 * @author Matthijs Laan, B3Partners
 */
public class FilterToArcXMLSQL extends FilterToSQL {
    
    protected AxlSpatialQuery axlQuery;
    
    boolean spatialOperatorAllowed = true;
    
    public FilterToArcXMLSQL(AxlSpatialQuery query) {
        this(null, query);
    }
    
    public FilterToArcXMLSQL(Writer w, AxlSpatialQuery axlQuery) {
        super(w);
        this.axlQuery = axlQuery;
        setInline(true);
    }
    
    @Override
    protected FilterCapabilities createFilterCapabilities() {
        FilterCapabilities caps = new FilterCapabilities();

        caps.addAll(FilterCapabilities.LOGICAL_OPENGIS);
        caps.addAll(FilterCapabilities.SIMPLE_COMPARISONS_OPENGIS);

        caps.addType(IsNullImpl.class);
        
        caps.addType(PropertyIsBetween.class);
        caps.addType(PropertyIsLike.class);
        
        caps.addType(Id.class);
        
        caps.addType(BBOX.class);
        caps.addType(Intersects.class);
        caps.addType(DWithin.class);
        
        caps.addType(FilterCapabilities.SIMPLE_ARITHMETIC);

        return caps;
    }
        
    @Override
    protected Object visit(BinaryLogicOperator filter, Object extraData) {
        String op = (String)extraData;
        
        boolean saveSpatialOpAllowed = spatialOperatorAllowed;
        
        // A spatial operator can only be used if it is combined with attribute
        // queries in a boolean AND
        
        if("OR".equals(op)) {
            spatialOperatorAllowed = false;
        } 
        Object r = super.visit(filter, extraData);
        
        spatialOperatorAllowed = saveSpatialOpAllowed;
        return r;
    }
    
    @Override
    protected Object visitBinarySpatialOperator(BinarySpatialOperator filter,
            PropertyName property, Literal geometry, boolean swapped,
            Object extraData) {
        if(axlQuery.getSpatialFilter() != null) {
            // Filter could be pre-processed with ArcSdeSimplifyingFilterVisitor
            // So that INTERSECTS(the_geom, g1) OR INTERSECTS(the_geom, g2) can be merged
            // into INTERSECTS(the_geom, UNION(g1,g2))           
            throw new RuntimeException("Only a single spatial operator is supported");
        }
        
        if(!spatialOperatorAllowed) {
            throw new RuntimeException("Spatial operator not allowed in this position in filter");
        }

        if(featureType != null && property.getPropertyName() != null) {
            if(!featureType.getGeometryDescriptor().getLocalName().equals(property.getPropertyName())) {
                throw new RuntimeException("Spatial operator only supported on default geometry property");
            }
        }
        
        Geometry geom = (Geometry)geometry.getValue();
        
        AxlSpatialFilter axlFilter = new AxlSpatialFilter();
        axlQuery.setSpatialFilter(axlFilter);
        
        if(filter instanceof BBOX) {
            axlFilter.setGeometryOrEnvelope(ArcXMLUtils.convertToAxlEnvelope((Polygon)geom));
            axlFilter.setRelation(AxlSpatialFilter.RELATION_ENVELOPE_INTERSECTION);
        } else if(filter instanceof Intersects) {
            
            axlFilter.setGeometryOrEnvelope(ArcXMLUtils.convertToAxlGeometry(geom));
            axlFilter.setRelation(AxlSpatialFilter.RELATION_AREA_INTERSECTION);

        } else if(filter instanceof DWithin) {
            
            axlFilter.setGeometryOrEnvelope(ArcXMLUtils.convertToAxlGeometry(geom));
            axlFilter.setRelation(AxlSpatialFilter.RELATION_AREA_INTERSECTION);
            
            axlFilter.setBuffer(new AxlBuffer(((DWithin)filter).getDistance()));
        }
        try {
            out.write("1=1");
        } catch (IOException ex) {
            throw new RuntimeException(IO_ERROR, ex);
        }
        
        return null;
    }
    
    @Override
    protected Object visitBinarySpatialOperator(BinarySpatialOperator filter, Expression e1, 
        Expression e2, Object extraData) {
        throw new RuntimeException(
            "ArcXML spatial operators only supported for default geometry and a literal operand");
    }    
}
