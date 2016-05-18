package pe.com.pps.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity(name = "FactorEstimacionAmbiental")
@DiscriminatorValue(value = "1")
public class FactorEstimacionAmbiental extends FactorEstimacion {
}
