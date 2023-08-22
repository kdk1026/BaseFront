package com.infoin.app.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2023. 2. 10. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
@Getter
@Setter
@ToString
public class UserVo {

	private String userId;
	private String bplcId;
	private String fctryId;
	private String userNm;
	private String userNcnm;
	private String userCd;
	private String fileLinkPath;
	private String bplcNm;
	private String fctryNm;
	private String lineNm;
	private String opId;
	private String opNm;
	private String authorId;

}
