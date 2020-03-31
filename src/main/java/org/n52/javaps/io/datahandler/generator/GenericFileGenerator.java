/*
 * Copyright 2016-2020 52°North Initiative for Geospatial Open Source
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

import java.io.IOException;
import java.io.InputStream;

import org.n52.javaps.annotation.Properties;
import org.n52.javaps.description.TypedProcessOutputDescription;
import org.n52.javaps.io.AbstractPropertiesInputOutputHandler;
import org.n52.javaps.io.Data;
import org.n52.javaps.io.EncodingException;
import org.n52.javaps.io.OutputHandler;
import org.n52.javaps.io.data.binding.complex.GenericFileDataBinding;
import org.n52.shetland.ogc.wps.Format;

/**
 * @author Matthias Mueller, TU Dresden
 *
 */
@Properties(
        defaultPropertyFileName = "genericfilehandler.default.json",
        propertyFileName = "genericfilegenerator.json")
public class GenericFileGenerator extends AbstractPropertiesInputOutputHandler implements OutputHandler {

    public GenericFileGenerator() {
        super();
        addSupportedBinding(GenericFileDataBinding.class);
    }

    @Override
    public InputStream generate(TypedProcessOutputDescription<?> description,
            Data<?> data,
            Format format) throws IOException, EncodingException {
        InputStream theStream = ((GenericFileDataBinding) data).getPayload().getDataStream();
        return theStream;
    }

}
