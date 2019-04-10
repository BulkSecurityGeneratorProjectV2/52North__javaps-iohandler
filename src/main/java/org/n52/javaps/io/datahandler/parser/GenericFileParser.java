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
import java.util.Optional;

import org.n52.javaps.annotation.Properties;
import org.n52.javaps.description.TypedProcessInputDescription;
import org.n52.javaps.io.AbstractPropertiesInputOutputHandler;
import org.n52.javaps.io.Data;
import org.n52.javaps.io.DecodingException;
import org.n52.javaps.io.GenericFileData;
import org.n52.javaps.io.InputHandler;
import org.n52.javaps.io.data.binding.complex.GenericFileDataBinding;
import org.n52.shetland.ogc.wps.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matthias Mueller, TU Dresden
 *
 */
@Properties(
        defaultPropertyFileName = "genericfilehandler.default.json",
        propertyFileName = "genericfilparser.json")
public class GenericFileParser extends AbstractPropertiesInputOutputHandler implements InputHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(GenericFileParser.class);

    public GenericFileParser() {
        super();
        addSupportedBinding(GenericFileDataBinding.class);
    }

    @Override
    public Data<?> parse(TypedProcessInputDescription<?> description,
            InputStream input,
            Format format) throws IOException, DecodingException {

        Optional<String> mimeType = format.getMimeType();

        GenericFileData theData = new GenericFileData(input, mimeType.get());
        LOGGER.info("Found File Input " + mimeType);

        return new GenericFileDataBinding(theData);
    }

}
