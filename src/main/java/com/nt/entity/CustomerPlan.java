package com.nt.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CustomerPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer cId;
	@NonNull
private String cName;
	@NonNull
private String cmail;
	@NonNull
private Long cPhNo;
	@NonNull
private String cGender;
	@NonNull
private Long cSsn;
	@NonNull
private String pName;
	@NonNull
private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NonNull
private LocalDate planStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
@NonNull
private LocalDate planEndDate;
	


}
