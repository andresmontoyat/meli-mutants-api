package analizer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dna {

    public static final String A = "A";
    public static final String C = "C";
    public static final String T = "T";
    public static final String G = "G";

    private String[] sequence;

}
