/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
package org.eclipse.imp.x10dt.ui.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import lpg.runtime.Monitor;

import polyglot.frontend.FileSource;
import polyglot.frontend.Job;
import polyglot.frontend.Parser;
import polyglot.frontend.Source;
import polyglot.frontend.goals.Goal;
import polyglot.util.ErrorQueue;
import x10.parser.X10Lexer;
import x10.parser.X10Parser;

public class ExtensionInfo extends polyglot.ext.x10.ExtensionInfo
{
    Monitor monitor;

    ExtensionInfo(Monitor monitor) {
    	super();
        this.monitor = monitor;
    }
    
    public Job getJob(Source source) {
        Collection jobs = scheduler.jobs();
        for (Iterator i = jobs.iterator(); i.hasNext(); )
        {
            Job job = (Job) i.next();
            if (job.source() == source)
                return job;
        }
        return null;
    }
    
    //
    // Replace the Flex/Cup parser with a JikesPG parser
    //
    //    public Parser parser(Reader reader, FileSource source, ErrorQueue eq) { 
    //        Lexer lexer = new Lexer_c(reader, source.name(), eq);
    //        Grm grm = new Grm(lexer, ts, nf, eq);
    //        return new CupParser(grm, source, eq);
    //    }
    //
    
    public Parser parser(Reader reader, FileSource source, ErrorQueue eq) {
        // try {
            //
            // X10Lexer may be invoked using one of two constructors.
            // One constructor takes as argument a string representing
            // a (fully-qualified) filename; the other constructor takes
            // as arguments a (file) Reader and a string representing the
            // name of the file in question. Invoking the first
            // constructor is more efficient because a buffered File is created
            // from that string and read with one (read) operation. However,
            // we depend on Polyglot to provide us with a fully qualified
            // name for the file. In Version 1.3.0, source.name() yielded a
            // fully-qualied name. In 1.3.2, source.path() yields a fully-
            // qualified name. If this assumption still holds then the 
            // first constructor will work.
            // The advantage of using the Reader constructor is that it
            // will always work, though not as efficiently.
            //
            // X10Lexer x10_lexer = new X10Lexer(reader, source.name());
            //
            if (reader instanceof CharBufferReader)
            {
                getLexer().initialize(((CharBufferReader) reader).getBuffer(), source.path());
                getParser().initialize(ts, nf, source, eq); // Create the parser
                getLexer().lexer(getParser());
                return getParser(); // Parse the token stream to produce an AST
            }
            //
            // TODO: FIX ME! FIX ME!! FIX ME!!!
            //
            // Note that this temporary code will not work properly if the
            // input in question is in an editor buffer that has been altered.
            // When using Safari, it is important that all request for new
            // source be procesed by Safari. However, we cannot do so now without
            // changing the base Polyglot code.
            //
            else return super.parser(reader, source, eq);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //throw new IllegalStateException("Could not parse " + source.path());
    }

    /**
     * Return the <code>Goal</code> to compile the source file associated with
     * <code>job</code> to completion.
     */
    public Goal getCompileGoal(Job job) {
        return scheduler.TypeChecked(job); // CodeGenerated(job);
    }
}