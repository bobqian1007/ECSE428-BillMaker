package com.ecse428.billmaker.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class SupervisorUser extends User{
   private Set<SupervisionRequest> supervisionRequests;
   
   @OneToMany(mappedBy="supervisorUser" )
   public Set<SupervisionRequest> getSupervisionRequests() {
      return this.supervisionRequests;
   }
   
   public void setSupervisionRequests(Set<SupervisionRequest> supervisionRequestss) {
      this.supervisionRequests = supervisionRequestss;
   }
   
   }
