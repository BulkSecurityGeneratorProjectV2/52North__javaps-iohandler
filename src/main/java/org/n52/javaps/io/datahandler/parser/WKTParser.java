/*
 * Copyright 2016-2022 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.javaps.io.datahandler.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.n52.javaps.annotation.Properties;
import org.n52.javaps.description.TypedProcessInputDescription;
import org.n52.javaps.io.AbstractPropertiesInputOutputHandler;
import org.n52.javaps.io.Data;
import org.n52.javaps.io.DecodingException;
import org.n52.javaps.io.InputHandler;
import org.n52.javaps.io.data.binding.complex.JTSGeometryBinding;
import org.n52.shetland.ogc.wps.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class parses String representations out of JTS Geometries.
 *
 * @author Benjamin Pross
 *
 */
@Properties(
        defaultPropertyFileName = "wkthandler.default.json",
        propertyFileName = "wktparser.json")
public class WKTParser extends AbstractPropertiesInputOutputHandler implements InputHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(WKTParser.class);

    public WKTParser() {
        super();
        addSupportedBinding(JTSGeometryBinding.class);
    }

    public Data<?> parse(TypedProcessInputDescription<?> description,
            InputStream input,
            Format format) throws IOException, DecodingException {
        try {
            Geometry g = new WKTReader().read(new InputStreamReader(input, StandardCharsets.UTF_8));

            return new JTSGeometryBinding(g);

        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }

}
