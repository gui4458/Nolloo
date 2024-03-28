
function goWish(itemCode, loginInfo) {


    if (loginInfo == 'anonymousUser') {
        alert('로그인 해주세요.');
    }
    else if (loginInfo != 'anonymousUser') {
        fetch('/wish/insertWish', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify({
                // 데이터명 : 데이터값
                itemCode: itemCode
            })
        })
            .then((response) => {
                return response.json(); //나머지 경우에 사용
            })
            //fetch 통신 후 실행 영역
            .then((data) => {//data -> controller에서 리턴되는 데이터!
                let cnt = 0;

                data.forEach(e => {
                    if (e.itemCode == itemCode) {
                        cnt++;

                    }
                });
                if (cnt == 1) {

                    alert('관심목록에 해당 상품이 존재 합니다.')

                } else {

                    const result1 = confirm('관심목록에 상품을 등록했습니다.\n관심목록 페이지로 이동할까요?')
                    if (result1) {
                        location.href = `/wish/goWishList`;
                    }
                }
            })
            //fetch 통신 실패 시 실행 영역
            .catch(err => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
    }
}

function reserveInsert(itemCode,reserveCnt) {

    if (reserveCnt == 0) {
        fetch('/reserve/partyReserve', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify({
                // 데이터명 : 데이터값
                itemCode: itemCode
            })
        })
            .then((response) => {
                // return response.json(); //나머지 경우에 사용
            })
            //fetch 통신 후 실행 영역
            .then((data) => {//data -> controller에서 리턴되는 데이터!
                const chk = confirm('예약되었습니다.\n예약페이지로 이동 하시겠습니까?')
                if (chk) {
                    location.href = `/reserve/reserveList`;
                }
    
            })
            //fetch 통신 실패 시 실행 영역
            .catch(err => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
    }else {
        alert('이미 예약되어있는 파티입니다.')
    }
}
function reserveAlert() {
    alert('로그인 해주세요.')
}



function goChat(itemCode) {

    fetch('/inChk', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
        })
    })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            if (data.includes(itemCode)) {
                // 채팅방 URL 생성
                const chatRoomUrl = `http://192.168.30.107:8081/chat/${itemCode}`;

                // 채팅방을 새 창으로 열기
                const windowFeatures = "left=1500,top=100,width=750,height=750,popup";
                window.open(chatRoomUrl, "_blank", windowFeatures);
            }else{
                alert('참여 후 채팅 하실 수 있습니다.')
            }


        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });



}


function goLogin() {
    alert('로그인 후 이용해주세요.')
}

