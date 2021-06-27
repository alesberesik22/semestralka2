package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "IotNode_AllIoTNodes",
                query = "from IotNode"),
        @org.hibernate.annotations.NamedQuery(
                name = "IoTNode_findByHouseHold",
                query = "from IotNode where houseHold = :houseHold"),
        @org.hibernate.annotations.NamedQuery(name = "IotNode_findById",
                query = "from IotNode where id = :id"),

})
@Entity
public class IotNode {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    // Swagger nebude zobrazovať atribút
    private Long id;
    @NotEmpty
    @ApiModelProperty(example = "IoTNode") // Príklad pre swagger doku.
    private String Name;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "household_id", nullable = false)
    //@JsonIgnore()
    private HouseHold houseHold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public HouseHold getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(HouseHold houseHold) {
        this.houseHold = houseHold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IotNode iotNode = (IotNode) o;

        return id.equals(iotNode.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
