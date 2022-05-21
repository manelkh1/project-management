package com.management.cni.service;

import com.management.cni.entity.Session;
import com.management.cni.entity.User;
import com.management.cni.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class SessionService {

  @Autowired
  private SessionRepository sessionRepository;

  @Transactional
  public Session findUser(User user) {
    return sessionRepository.findByUser(user);
  }

  @Transactional
  public Session findByToken(String token) {
    return sessionRepository.findBytoken(token);
  }

  @Transactional
  public void saveSession(User user, String token) {
    Session session = new Session(token, user);
    session.setLastConnection(calculateExpiryDate(24 * 6));
    sessionRepository.save(session);
  }

  //Calculate expiry date
  private Timestamp calculateExpiryDate(int expiryTime) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MINUTE, expiryTime);
    return new Timestamp(cal.getTime().getTime());
  }

}
