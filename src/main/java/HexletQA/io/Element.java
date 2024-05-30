package HexletQA.io;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PRIVATE;

//@Getter
//@Setter
//@AllArgsConstructor(access = PRIVATE)
//@NoArgsConstructor
public class Element {
    @CsvBindByPosition(position = 0)
    private String number;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private Double count;
    @CsvBindByPosition(position = 3)
    private Double sum;

    @Override
    public String toString() {
        return "HexletQA.io.Element{" + "number=" + number + ", name="
                + name + ", count=" + count + ", sum=" + sum + '}';
    }

}
