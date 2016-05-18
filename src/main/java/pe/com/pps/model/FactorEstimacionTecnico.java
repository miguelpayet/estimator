package pe.com.pps.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity(name = "FactorEstimacionTecnico")
@DiscriminatorValue(value = "2")
public class FactorEstimacionTecnico extends FactorEstimacion {

}
