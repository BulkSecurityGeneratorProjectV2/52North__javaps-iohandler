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
package org.n52.javaps.io.data.binding.complex;

import org.apache.xmlbeans.XmlObject;
import org.n52.javaps.io.complex.ComplexData;

/**
 *
 * This class holds an XMLObject as payload. It is used by the
 * GenericXMLDataParser and - Generator.
 *
 * @author bpross-52n
 *
 */
public class GenericXMLDataBinding implements ComplexData<XmlObject> {

    private static final long serialVersionUID = -6875103125533078664L;

    private transient XmlObject payload;

    public GenericXMLDataBinding(XmlObject payload) {
        this.payload = payload;
    }

    @Override
    public XmlObject getPayload() {
        return payload;
    }

    @Override
    public Class<XmlObject> getSupportedClass() {
        return XmlObject.class;
    }

}
