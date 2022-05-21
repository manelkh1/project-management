package com.management.cni.service;

import com.management.cni.Entity.Member;
import com.management.cni.Entity.Member;
import com.management.cni.Entity.User;
import com.management.cni.Repository.MemberRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.MemberRequest;
import com.management.cni.security.dto.response.MemberResponse;
import com.management.cni.security.mapper.MemberMapper;
import com.management.cni.security.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class MemberService {

  @Autowired
  MemberRepository memberRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private SessionService sessionService;


  public ApiResponse getAllMembers() {
   // User user = userService.getConnectedAdmin();
    List<MemberResponse> memberResponses = new ArrayList<>();
    try {

   //   if (user != null) {
        List<Member> members = memberRepository.findAll();
        if (!members.isEmpty()) {
          for (Member member : members) {
            MemberResponse memberResponse = MemberMapper.INSTANCE.convertToMemberResponse(member);
            memberResponses.add(memberResponse);
          }
        }
        return new ApiResponse(memberResponses, null, HttpStatus.OK, LocalDateTime.now());
     /* } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }*/
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse getMemberById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user.getAdmin() != null || user.getMember() != null || user.getMember() != null) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
          MemberResponse memberResponse = MemberMapper.INSTANCE.convertToMemberResponse(member.get());
          return new ApiResponse(memberResponse, null, HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "MEMBER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse createMember(MemberRequest memberRequest) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Member member = MemberMapper.INSTANCE.convertToMember(memberRequest);
        ///convert userRequest to USER PERSISTENCE SO YOU CAN STORE IT IN DATABASE

        String password = passwordEncoder.encode(memberRequest.getUser().getPassword());
        member.getUser().setPassword(password);
        member.getUser().setStatus(false);
        String token = UUID.randomUUID().toString();//generate token randomly
        sessionService.saveSession(member.getUser(), token);//put a token and a saveduser in a session
        // emailService.sendHtmlMail(member.getUser());
        memberRepository.save(member);
        return new ApiResponse(null, "MEMBER CREATED", HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse deleteMemberById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
          memberRepository.deleteById(id);
          return new ApiResponse(null, "MEMBER DELETED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "MEMBER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse updateMember(Long id, MemberRequest memberRequest) {
    User user = userService.getConnectedMember();
    try {
      if (user != null) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
          User newUser = member.get().getUser();

          newUser.setFirstName(memberRequest.getUser().getFirstName());
          newUser.setLastName(memberRequest.getUser().getLastName());
          newUser.setEmail(memberRequest.getUser().getEmail());
          user.getMember().setUser(newUser);
          memberRepository.save(user.getMember());
          return new ApiResponse(null, "MEMBER UPDATED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "MEMBER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

}
