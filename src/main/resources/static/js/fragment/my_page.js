
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

function reviseChk() {

    const memberPw_tag = document.querySelector('#update_memberPw');


    if (memberPw_tag.value == '') {
        //현재 input 태그에 에러메세지가 있는지 검사
        const error_span = memberPw_tag.closest('tr').querySelector('.error');

        //만약 기존의 에러메세지가 있다면 지워줌
        if (error_span != null) {
            error_span.remove();
        }

        const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">비밀번호는 필수입력입니다.</span>';
        memberPw_tag.insertAdjacentHTML('afterend', error_str);
    }


    const memberPwChk=document.querySelector('#update_memberPwChk')

    if(memberPwChk.value==''){
        const error_span=memberPwChk.closest('tr').querySelector('.error')

        if (error_span != null) {
            error_span.remove();
        }

        const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">비밀번호 확인은 필수입력입니다.</span>';
        memberPwChk.insertAdjacentHTML('afterend', error_str);

    }

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

    const member_address= document.querySelector('#roadAddress');

    if(member_address.value==''){
        const error_span=member_address.closest('tr').querySelector('.error');

        if (error_span != null) {
            error_span.remove();
        }

        const error_str = '<span class="error" style="color:red; font-style:italic; font-size:0.8rem;">주소는 필수입력입니다.</span>';
        member_address.insertAdjacentHTML('afterend', error_str);

    }

    //위의 조건이 다 맞다면 submit
    document.querySelector('#update-member-form').submit();

    
    

    
}