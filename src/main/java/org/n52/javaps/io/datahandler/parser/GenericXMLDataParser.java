/*
 * Copyright 20165-2019 52°North Initiative for Geospatial Open Source
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

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.n52.javaps.annotation.Properties;
import org.n52.javaps.description.TypedProcessInputDescription;
import org.n52.javaps.io.AbstractPropertiesInputOutputHandler;
import org.n52.javaps.io.Data;
import org.n52.javaps.io.InputHandler;
import org.n52.javaps.io.data.binding.complex.GenericXMLDataBinding;
import org.n52.shetland.ogc.wps.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Properties(
        defaultPropertyFileName = "genericxmlhandler.default.json",
        propertyFileName = "genericxmlparser.json")
public class GenericXMLDataParser extends AbstractPropertiesInputOutputHandler implements InputHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(GenericXMLDataParser.class);

    public GenericXMLDataParser() {
        super();
        addSupportedBinding(GenericXMLDataBinding.class);
    }

    @Override
    public Data<?> parse(TypedProcessInputDescription<?> description,
            InputStream input,
            Format format) {

        XmlObject xmlData = XmlObject.Factory.newInstance();

        try {
            xmlData = XmlObject.Factory.parse(input);
        } catch (XmlException | IOException e) {
            LOGGER.error("Could not parse inputstream as XMLObject.", e);
        }

        GenericXMLDataBinding xmlDataBinding = new GenericXMLDataBinding(xmlData);

        return xmlDataBinding;
    }

}
