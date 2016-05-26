package pe.com.pps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "4")
public class TareaDuracion  extends TareaGestion {
}
