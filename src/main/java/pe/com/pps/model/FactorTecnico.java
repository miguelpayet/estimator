package pe.com.pps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
public class FactorTecnico extends Factor {
}
