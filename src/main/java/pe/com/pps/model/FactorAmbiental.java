package pe.com.pps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class FactorAmbiental extends Factor {
}
