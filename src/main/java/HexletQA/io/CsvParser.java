package HexletQA.io;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

public class CsvParser { // парсинг CSV файлов
    public static void main(String[] args) throws IOException {
        String fileName = "test1.csv";
        Reader myReader = new FileReader(fileName);
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Element.class)
                .withColumnSeparator(';').withSkipFirstDataRow(true);
        MappingIterator<Element> iterator = mapper
                .readerFor(Element.class)
                .with(schema)
                .readValues(myReader);

        List<Element> elements = iterator.readAll();

        for (Element element : elements) {
            System.out.println(element.toString());
        }

        CsvMapper mapper1 = new CsvMapper();
        CsvSchema schema1 = mapper1.schemaFor(Element.class)
                .withColumnSeparator(';')
                .withHeader();
        ObjectWriter writer1 = mapper1.writer(schema1);
        writer1.writeValue(new FileWriter("test.csv"),elements);

        List<String> list = List.of("AAA", "BBB", "CCC");
        CsvMapper mapper2 = new CsvMapper();
        CsvSchema schema2 = CsvSchema.builder()
                .addColumn("name").build()
                .withHeader().withColumnSeparator('\n');
        ObjectWriter writer2 = mapper2.writer(schema2);
        writer2.writeValue(new FileWriter("test2.csv"), list);

        Reader myReader3 = new FileReader("test2.csv");
        CsvMapper mapper3 = new CsvMapper();
        CsvSchema schema3 = mapper3.schemaFor(String.class)
                .withColumnSeparator('\n')
                .withSkipFirstDataRow(true);
        MappingIterator<String> iterator3 = mapper3
                .readerFor(String.class)
                .with(schema3)
                .with(com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.SKIP_EMPTY_LINES)
                .readValues(myReader3);

        List<String> list3 = iterator3.readAll().stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        for (String element : list3) {
            System.out.println(element);
        }
    }
    @JsonPropertyOrder({ "number", "name", "count", "sum" })
    static class Element {
        public String number;
        public String name;
        public Double count;
        public Double sum;

        @Override
        public String toString() {
            return "Element{" + "number=" + number + ", name="
                    + name + ", count=" + count + ", sum=" + sum + '}';
        }
    }
}



