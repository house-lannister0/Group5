package com.oopclass.breadapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	private String first_name;
	
	private String last_name;
	
	private String address;
	
	private String contact_number;

	
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
        public String getAddress() {
            return address;
        }
	
        public void setAddress(String address) {
		this.address = address;
        }
        public String getContactNumber(){
            return contact_number;
        }
         public void setContactNumber(String contact_number) {
		this.contact_number = contact_number;
             
         }
        
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + first_name + ", lastName=" + last_name + ", address=" + address + ", contactNumber=" + contact_number+"]";
	}

	
}
