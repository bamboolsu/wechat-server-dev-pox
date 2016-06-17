package org.leo.entity;

public class AccessTokenReturn {
		
	private String access_token; 
	private String refresh_token; 
	private String openid; 
	private String scope; 
	private String unionid; 
	private String expires_in;
	@Override
	public String toString() {
		return "AccessTokenReturn [access_token=" + access_token 
				+ ", expires_in=" + expires_in
				+ ", refresh_token=" + refresh_token
				+ ", openid=" + openid
				+ ", scope=" + scope
				+ ", unionid=" + unionid  + "]";
	}
	// {"access_token":"OezXcEiiBSKSxW0eoylIeBpFRIqS13vEcN2lYeoQ4mk3FsWE_68H0sgCiUBXID0ZTPcmtTFl7HssY9hAG038biEAT4RAesXU80sjb_00o_oVSZX81ORXgVMtngpjqR3bwmGA70qzRcI05bsgDYrU0w",
	//"expires_in":7200,
	//"refresh_token":"OezXcEiiBSKSxW0eoylIeBpFRIqS13vEcN2lYeoQ4mk3FsWE_68H0sgCiUBXID0ZLYHZ9BVsmjGj0ovdEb1LjY3QgUTO0NUDDCqrsQIOUFDhxJKo1Fll1qfkORl08zPGt6r6XYO9O6aPbYfLMADSFA",
	//"openid":"ocq3sshQkl8uVKgAQmQ9UWQdPY6g",
	//"scope":"snsapi_login",
	//"unionid":"o5Jt0wxJo7KS8-_SiAWxhKdQRbJE"}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
}
