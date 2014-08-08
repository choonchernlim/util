package com.choonchernlim.util.reader

import spock.lang.Specification
import spock.lang.Unroll

class CSVReaderSpec extends Specification {

    @Unroll
    def "#field1 + 1 should equal to #field2"() {
        expect:
        Integer.valueOf(field1 as String) + 1 == Integer.valueOf(field2 as String)

        where:
        [field1, field2] << new CSVReader(getClass().getClassLoader().getResourceAsStream("test-data.csv"))
    }

    @Unroll
    def "Handling special character - #actualValue"() {
        expect:
        actualValue == expectedValue

        where:
        [actualValue] << new CSVReader(getClass().getClassLoader().getResourceAsStream("test-data-special-chars.csv"))
        expectedValue << [",", '"Awesome"']
    }

    def "Invoking iterator.remove()"() {
        when:
        new CSVReader(getClass().getClassLoader().getResourceAsStream("test-data.csv")).iterator().remove()

        then:
        thrown(UnsupportedOperationException.class)
    }
}
