/*
 * Copyright 2016-2019 52°North Initiative for Geospatial Open Source
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
package org.n52.javaps.io;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.xmlbeans.XmlObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.n52.javaps.description.TypedComplexInputDescription;
import org.n52.javaps.description.impl.TypedProcessDescriptionFactory;
import org.n52.javaps.io.data.binding.complex.GenericXMLDataBinding;
import org.n52.javaps.io.datahandler.parser.GenericXMLDataParser;
import org.n52.shetland.ogc.wps.Format;

public class ComplexInputHandlerTest {

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    private GenericXMLDataParser handler;

    private TypedProcessDescriptionFactory descriptionFactory;

    @Before
    public void setup() {
        this.handler = new GenericXMLDataParser();
        this.descriptionFactory = new TypedProcessDescriptionFactory();
    }

    @Test
    public void testXmlEncodingEmptyFormat() throws IOException, EncodingException {

        String value = "<test>xml</test>";

        Charset charset = StandardCharsets.UTF_8;
        ByteArrayInputStream input = new ByteArrayInputStream(value.getBytes(charset));

        Data<?> parsedData = this.handler.parse(input(), input, new Format());
        errors.checkThat(parsedData, is(notNullValue()));
        errors.checkThat(parsedData, is(notNullValue()));
        errors.checkThat(parsedData, is(instanceOf(GenericXMLDataBinding.class)));
        errors.checkThat(parsedData.getPayload(), is(instanceOf(XmlObject.class)));
        errors.checkThat(parsedData.getPayload().toString(), is(value));
    }

    private TypedComplexInputDescription input() {

        return descriptionFactory.complexInput()
                        .withIdentifier("input")
                        .withDefaultFormat(new Format("text/xml"))
                        .withType(GenericXMLDataBinding.class)
                        .build();
    }

}
