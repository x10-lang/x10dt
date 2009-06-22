/*
 * Created on Oct 6, 2005
 */
package org.eclipse.imp.x10dt.core.builder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import polyglot.frontend.FileSource;

/**
 * A Polyglot Source whose input comes from an InputStream.<br>
 * Currently extends FileSource since that's all that the Polyglot Compiler class
 * will accept.
 * @author rfuhrer
 */
public class StreamSource extends FileSource {
    private InputStream fStream;
    private Reader fReader;

    public StreamSource(InputStream s, String fullPath) throws IOException {
	super(new File(fullPath), true);
	fStream= s;
    }

    public Reader open() throws IOException {
	// If only the 'reader' base-class field wasn't a FileReader, we
	// could assign that field and not have to save the reader in a
	// separate redundant field and override close()...
	if (fReader == null)
	    fReader= new InputStreamReader(fStream);
	return fReader;
    }

    public void close() throws IOException {
	if (fReader != null) {
	    fReader.close();
	    fReader= null;
	}
    }
}
