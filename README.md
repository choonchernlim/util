# Utility API

## CSVReader

A simple CSV reader that implements `Iterable`. It handles comma and double quotes, but it does not handle line breaks.

Per https://stackoverflow.com/questions/25189342/spock-reading-test-data-from-csv-file , 
it allows us to write elegant Spock specification to read data from CSV file without loading all data into memory first.

```Groovy
@Unroll
def "#field1 + 1 should equal to #field2"() {
    expect:
    Integer.valueOf(field1 as String) + 1 == Integer.valueOf(field2 as String)

    where:
    [field1, field2] << new CSVReader(getClass().getClassLoader().getResourceAsStream("test-data.csv"))
}
```

It also allows us to write a clean for-loop to read data from CSV file.

```Java
for (String[] fields : new CSVReader(getClass().getClassLoader().getResourceAsStream("test-data.csv"))) {
    System.out.println(fields[0] + " " + fields[1]);
}
```

