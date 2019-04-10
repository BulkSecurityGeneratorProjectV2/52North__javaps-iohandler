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
package org.n52.javaps.io.datahandler.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.n52.javaps.description.TypedProcessInputDescription;
import org.n52.javaps.io.AbstractPropertiesInputOutputHandler;
import org.n52.javaps.io.Data;
import org.n52.javaps.io.DecodingException;
import org.n52.javaps.io.InputHandler;
import org.n52.javaps.io.data.binding.complex.PlainStringBinding;
import org.n52.shetland.ogc.wps.Format;

/**
 * @author Bastian Schaeffer; Matthias Mueller, TU Dresden
 *
 */
public class WCPSQueryParser extends AbstractPropertiesInputOutputHandler implements InputHandler {

    public WCPSQueryParser() {
        super();
        addSupportedBinding(PlainStringBinding.class);
    }

    @Override
    public Data<?> parse(TypedProcessInputDescription<?> description,
            InputStream input,
            Format format) throws IOException, DecodingException {
        BufferedReader br;
        StringWriter sw;
        try {
            br = new BufferedReader(new InputStreamReader(input, "UTF-8"));

            sw = new StringWriter();
            int k;
            while ((k = br.read()) != -1) {
                sw.write(k);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported Encoding");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PlainStringBinding result = new PlainStringBinding(sw.toString());
        return result;
    }

}
