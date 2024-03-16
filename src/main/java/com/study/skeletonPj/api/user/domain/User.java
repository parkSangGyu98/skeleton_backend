package com.study.skeletonPj.api.user.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.study.skeletonPj.common.util.ModelMapperUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("User")
public class User {
	@Getter
	@Setter
	public static class Veo implements UserDetails {
		String userId;
		String userPw;
		String userName;
		Integer userAge;
		Double userMoney;
		String userIntro;
		LocalDateTime createDate;
		LocalDateTime updateDate;
		
		@Override // 권한 반환
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return List.of(new SimpleGrantedAuthority("user"));
		}

		// 사용자의 id 반환(고유한 값)
		@Override
		public String getUsername() {
			return getUserId();
		}

		// 사용자의 패스워드 반환
		@Override
		public String getPassword() {
			return getUserPw();
		}

		// 계정 만료 여부 반환
		@Override
		public boolean isAccountNonExpired() {
			return true; // true : 만료 X
		}

		// 계정 잠금 여부 반환
		@Override
		public boolean isAccountNonLocked() {
			return true; // true : 잠금 X
		}

		// 패스워드 만료 여부 반환
		@Override
		public boolean isCredentialsNonExpired() {
			return true; // true : 만료 X
		}

		// 계정 사용 가능 여부 반환
		@Override
		public boolean isEnabled() {
			return true; // true : 사용 가능
		}
	}
	
	@Getter
	@Setter
	public static class Vo {
		String userId;
		String userName;
		Integer userAge;
		Double userMoney;
		String userIntro;
		LocalDateTime createDate;
		LocalDateTime updateDate;
		
		public static Vo of(Veo veo) {
			Vo vo = ModelMapperUtils.getModelMapper().map(veo, Vo.class);
			return vo;
		}
		
		public static List<Vo> of(List<Veo> veoList) {
			List<Vo> voList = new ArrayList<Vo>();
			for(Veo veo : veoList) {
				voList.add(of(veo));
			}
			return voList;
		}
	}
	
}
