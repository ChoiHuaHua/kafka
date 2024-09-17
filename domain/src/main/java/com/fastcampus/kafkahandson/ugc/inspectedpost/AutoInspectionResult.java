package com.fastcampus.kafkahandson.ugc.inspectedpost;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutoInspectionResult {

    private String status; //GOOD, BAD
    private String[] tags;
}
