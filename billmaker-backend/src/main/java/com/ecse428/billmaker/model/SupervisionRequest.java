package com.ecse428.billmaker.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class SupervisionRequest{
   private IndividualUser individualUser;
   
   @ManyToOne(optional=false)
   public IndividualUser getIndividualUser() {
      return this.individualUser;
   }
   
   public void setIndividualUser(IndividualUser individualUser) {
      this.individualUser = individualUser;
   }
   
   private SupervisorUser supervisorUser;
   
   @ManyToOne(optional=false)
   public SupervisorUser getSupervisorUser() {
      return this.supervisorUser;
   }
   
   public void setSupervisorUser(SupervisorUser supervisorUser) {
      this.supervisorUser = supervisorUser;
   }
   
   private Boolean status;

public void setStatus(Boolean value) {
    this.status = value;
}
public Boolean getStatus() {
    return this.status;
}
private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
}
