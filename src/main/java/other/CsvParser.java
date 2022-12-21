package other;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CsvParser {
    public static void main(String[] args) throws IOException {
        String fileName = "test.csv";
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
    }

    @JsonPropertyOrder({ "number", "name", "count", "sum" })
    class Element {
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



