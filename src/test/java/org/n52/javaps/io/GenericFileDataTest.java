package org.n52.javaps.io;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class GenericFileDataTest {

    @Test
    public void testGenericFileData() {
        String fileExtension = GenericFileDataConstants.mimeTypeFileTypeLUT().get("application/zip");
        assertNotNull(fileExtension);
    }

}
