package com.choonchernlim.util.reader;

import au.com.bytecode.opencsv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A simple CSV reader that implements Iterable. It handles comma and double quotes, but it does not handle
 * line breaks.
 */
public class CSVReader implements Iterable<String[]> {
    private final InputStream inputStream;

    public CSVReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Iterator<String[]> iterator() {
        return new Iterator<String[]>() {
            private final CSVParser csvParser = new CSVParser();
            private final Scanner scanner = new Scanner(inputStream);

            @Override
            public boolean hasNext() {
                return scanner.hasNextLine();
            }

            @Override
            public String[] next() {
                try {
                    return csvParser.parseLine(scanner.nextLine());
                }
                catch (IOException e) {
                    throw new RuntimeException("Unable to parse CSV string", e);
                }
            }

            @Override
            public void remove() {
                scanner.remove();
            }
        };
    }
}
