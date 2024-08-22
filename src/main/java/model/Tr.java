package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tr {
    private String text;
    private String pos;
    private Text[]syn;
    private Text[]mean;
    private Ex[]ex;

}
