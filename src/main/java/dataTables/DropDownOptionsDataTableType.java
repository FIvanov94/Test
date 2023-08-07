package dataTables;

import enums.DropDownOptions;
import io.cucumber.java.DataTableType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DropDownOptionsDataTableType {

    DropDownOptions dropDownOption;

    @DataTableType
    public DropDownOptionsDataTableType transformDropDownOptions(Map<String, String> map) {
        return DropDownOptionsDataTableType.builder()
                .dropDownOption(DropDownOptions.getDropDownOptionsByName(map.get("options")))
                .build();
    }
}
