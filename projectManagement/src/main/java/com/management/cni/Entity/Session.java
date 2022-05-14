package com.management.cni.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "session")
public class Session {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  //expiration time avec fonction calculateExpiryDate 24*6
  @Column(name = "last_connection")
  private Timestamp lastConnection;

  @Column(name = "token")
  private String token;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  public Session() {
    super();
  }

  public Session(String token, User user) {
    super();
    this.id = id;
    this.lastConnection = lastConnection;
    this.token = token;
    this.user = user;
  }

  public long getId() {
    return id;
  }

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Timestamp lastConnection) {
		this.lastConnection = lastConnection;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
