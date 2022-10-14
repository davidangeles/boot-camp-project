package com.nttdata.bootcamp.msdatabase.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Client {

    private Integer code;
    private String name;
    private String lastName;
    private Integer type;
    private List<Integer> lstAccount;

}
