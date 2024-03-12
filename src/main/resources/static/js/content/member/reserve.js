
// 예약내역 상세조회
function getReserveDetail(itemCode){
    fetch('/reserve/reserveDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
        itemCode : itemCode
        })
    })
    .then((response) => {
        //return response.text();
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        //조회한 데이터를 모달에 적용
        console.log(data);

        const modal_tbody= document.querySelector('#modal_tbody');
        modal_tbody.innerHTML=' ';
        let str='';

        
            str+=`
            <tr>
                <td>${data.itemTitle}</td>
                <td>${data.itemContent}</td>
                <td>${data.itemPrice}</td>
                <td>${data.itemStartDate}</td>
                <td>${data.itemPeople}</td>
                <td>${data.itemPlace}</td>
            </tr>
            `;
        modal_tbody.insertAdjacentHTML("afterbegin",str)






        
        //모달을 띄움
        const reserve_modal =  new bootstrap.Modal('#reserveModal');
        reserve_modal.show();

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function checkAll(){
    const checkAll = document.querySelector('#checkAll')
    const chks= document.querySelectorAll('.chk')

    if (checkAll.checked){
        for(const chk of chks){
            chk.checked=true;
        }
    }
    else{
        for(const chk of chks){
            chk.checked=false;
        }
    }

}
function deleteReserve(){
    //체크박스들
    const chks = document.querySelectorAll('.chk:checked');
    
    //선택한 체크박스를 담을 배열 통
    const reserveCodeList = [];
    
    for(const chk of chks){
        reserveCodeList.push(chk.value);
    }

    location.href=`/reserve/reserveDelete?reserveCodeList=${reserveCodeList}`;
}

