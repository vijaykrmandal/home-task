package com.bottomline.exercise.autocomplete.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "names")
public class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Add this if you want auto-increment
    private Long id;

    @NotBlank(message = "Name must not be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only alphabetic characters")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    
    public Name( String name) {
		this.id = null;
		this.name = name; 
	}

    public Name( ) {
		
	}
    
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}


	@Override
    public String toString() {
        return name;
    }
}