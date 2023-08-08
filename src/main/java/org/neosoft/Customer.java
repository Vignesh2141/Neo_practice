package org.neosoft;

import java.util.List;

@Data
@Collection
@NoArgsCuonstructor
public class Customer {
    @Id
    public String id;
    public String name;

    public List<Address> addresses;

    public String type;

}
