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
package org.n52.javaps.io.datahandler.generator;

import java.io.InputStream;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.n52.javaps.annotation.Properties;
import org.n52.javaps.description.TypedProcessOutputDescription;
import org.n52.javaps.io.AbstractPropertiesInputOutputHandler;
import org.n52.javaps.io.Data;
import org.n52.javaps.io.OutputHandler;
import org.n52.javaps.io.data.binding.complex.GenericXMLDataBinding;
import org.n52.shetland.ogc.wps.Format;

@Properties(
        defaultPropertyFileName = "genericxmlhandler.default.json",
        propertyFileName = "genericxmlgenerator.json")
public class GenericXMLDataGenerator extends AbstractPropertiesInputOutputHandler implements OutputHandler {

    public GenericXMLDataGenerator() {
        super();
        addSupportedBinding(GenericXMLDataBinding.class);
    }

    @Override
    public InputStream generate(TypedProcessOutputDescription<?> description,
            Data<?> data,
            Format format) {

        if (data instanceof GenericXMLDataBinding) {

            XmlObject xmlData = ((GenericXMLDataBinding) data).getPayload();

            XmlOptions xmlOptions = new XmlOptions();

            xmlOptions.setSaveNoXmlDecl();

            return xmlData.newInputStream(xmlOptions);

        }

        return XmlObject.Factory.newInstance().newInputStream();
    }

}
