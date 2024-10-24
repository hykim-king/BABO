package com.pcwk.ehr.cmn;

import java.util.List;

public interface WorkDiv<T> {

	void writeFile(String path); // 파일 쓰기
	int readFile(String path); // 파일 읽기
	int doSave(T param); // 계정 등록
	void doUpdate(); // 컬렉션 값이 바뀔 시(수정)에 파일에 업데이트
	int doDelete(T param);
	T doSelectOne(T param);
	List<T> doRetrieve(DTO param);
	
}
