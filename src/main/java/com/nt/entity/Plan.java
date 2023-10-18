package com.nt.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
private String pName;
private String pStatus;
private String pGender;
@DateTimeFormat(pattern = "yyyy-MM-dd")
private LocalDate pStartDate;
@DateTimeFormat(pattern = "yyyy-MM-dd")
private LocalDate pEnddate;


}
