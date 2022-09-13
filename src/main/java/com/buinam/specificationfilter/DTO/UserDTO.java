package com.buinam.specificationfilter.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    Long id;
    String name;
    Integer age;
    Boolean active;
}
