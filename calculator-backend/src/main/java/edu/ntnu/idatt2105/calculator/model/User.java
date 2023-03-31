package edu.ntnu.idatt2105.calculator.model;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User implements UserDetails {

  @Id
  @Column(name = "`username`", length = 32, nullable = false)
  @NonNull
  private String username;

  @Column(name = "`email`", unique = true, length = 64, nullable = false)
  @NonNull
  private String email;

  @Column(name = "`first_name`", length = 64, nullable = false)
  @NonNull
  private String firstName;

  @Column(name = "`last_name`", length = 64, nullable = false)
  @NonNull
  private String lastName;

  @Column(name = "`password`", nullable = false)
  @NonNull
  private String password;

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
  private Collection<Calculation> calculations;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
