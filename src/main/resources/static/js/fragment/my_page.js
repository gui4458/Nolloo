
function searchAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수


            document.querySelector('#postCode').value = data.zonecode;
            document.querySelector("#roadAddress").value = roadAddr;
        }
    }).open();
}

//회원정보수정
function reviseChk() {

    // const memberPw_tag = document.querySelector('#update_memberPw');

    // //validation 처리 (데이터 유효성 검사)
    // if (memberPw_tag.value == '') {
    //     //현재 input 태그에 에러메세지가 있는지 검사
    //     const error_span = memberPw_tag.closest('tr').querySelector('.error');

    //     //만약 기존의 에러메세지가 있다면 지워줌
    //     if (error_span != null) {
    //         error_span.remove();
    //     }

    //     const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">비밀번호는 필수입력입니다.</span>';
    //     memberPw_tag.insertAdjacentHTML('afterend', error_str);
    // }


    // const memberPwChk=document.querySelector('#update_memberPwChk')

    // if(memberPwChk.value==''){
    //     const error_span=memberPwChk.closest('tr').querySelector('.error')

    //     if (error_span != null) {
    //         error_span.remove();
    //     }

    //     const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">비밀번호 확인은 필수입력입니다.</span>';
    //     memberPwChk.insertAdjacentHTML('afterend', error_str);

    // }

    const memberName= document.querySelector('#update_memberName')

    if(memberName.value==''){
        const error_span=memberName.closest('tr').querySelector('.error')

        if (error_span != null) {
            error_span.remove();
        }

        const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">이름은 필수입력입니다.</span>';
        memberName.insertAdjacentHTML('afterend', error_str);

    }
    // const memberBirth= document.querySelector('#update_memberBirth')

    // if(memberBirth.value==){
    //     const error_span=memberBirth.closest('tr').querySelector('.error')

    //     if (error_span != null) {
    //         error_span.remove();
    //     }

    //     const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">생년월일은 필수입력입니다.</span>';
    //     memberBirth.insertAdjacentHTML('afterend', error_str);

    // }
    const memberEmail= document.querySelector('#update_memberEmail')

    if(memberEmail.value==''){
        const error_span=memberEmail.closest('tr').querySelector('.error')

        if (error_span != null) {
            error_span.remove();
        }

        const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">이메일은 필수입력입니다.</span>';
        memberEmail.insertAdjacentHTML('afterend', error_str);

    }
    const memberTel1= document.querySelector('#update_memberTel1')

    if(memberTel1.value==''){
        const error_span=memberTel1.closest('tr').querySelector('.error')

        if (error_span != null) {
            error_span.remove();
        }

        const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">전화번호는 필수입력입니다.</span>';
        memberTel1.insertAdjacentHTML('afterend', error_str);

    }

    // const member_address= document.querySelector('#roadAddress');

    // if(member_address.value==''){
    //     const error_span=member_address.closest('tr').querySelector('.error');

    //     if (error_span != null) {
    //         error_span.remove();
    //     }

    //     const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">주소는 필수입력입니다.</span>';
    //     member_address.insertAdjacentHTML('afterend', error_str);

    // }

    //위의 조건이 다 맞다면 submit

    if (memberName.value != '' && memberPw_tag.value != '' && memberEmail.value !='') {
        document.querySelector('#update-member-form').submit(); 
        alert('수정이 완료되었습니다.');
    }


        // document.querySelector('#update-member-form').submit();
    

    
    

    
}

//회원 탈퇴 실행
function deleteMember(){

    fetch('/member/deleteMember', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('회원 탈퇴되었습니다.');
        
        location.href = '/member/logout';
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

    
    
}
