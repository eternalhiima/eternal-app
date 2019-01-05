package com.eternal.web.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SampleModel implements Serializable{

    private Boolean bool;

    private String val;

}
