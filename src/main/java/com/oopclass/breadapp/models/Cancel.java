package com.oopclass.breadapp.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Entity
@Table(name = "cancels")
public class Cancel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	private String first_name;
	
	private String last_name;
	
	private String product_name;
	
	private LocalDate doc;
        
        private String cancel_order;
        

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
        public String getProductName() {
            return product_name;
        }
	
        public void setProductName(String product_name) {
		this.product_name = product_name;
        }
        public LocalDate getDoc(){
            return doc;
        }
         public void setDoc(LocalDate doc) {
		this.doc = doc;
        }        
         public String getCancel() {
            return cancel_order;           
        }
         public void setCancel(String cancel_order) {
         this.cancel_order = cancel_order;
        }
        
	@Override
	public String toString() {
		return "Cancel [id=" + id + ", firstName=" + first_name + ", lastName=" + last_name + ", productName=" + product_name + ", doc=" + doc +",cancelAnswer="+cancel_order+"]";
	}
}
