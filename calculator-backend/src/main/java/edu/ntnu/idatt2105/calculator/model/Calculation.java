package edu.ntnu.idatt2105.calculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "`calculation`")
public class Calculation {

  @Id
  @Column(name = "`calculation_id`", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "`calculation`", nullable = false)
  @NonNull
  private String calculation;

  @Column(name = "`answer`", nullable = false)
  @NonNull
  private String answer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "`user`", referencedColumnName = "`username`")
  private User user;
}
