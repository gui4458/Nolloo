const con = document.querySelector('.container')

const maxScroll = con.scrollHeight - con.clientHeight
window.addEventListener('scroll', function(){
    console.log(con.scrollHeight)
    console.log(window.scrollY)
    console.log(con.clientHeight)

    if(con.scrollHeight == (window.scrollY-5)){
        alert(123123)
    }
    
    
});



// 하트 누르면 추가
function wishAdd(divTag, itemCode) {
    const head = divTag.parentElement
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
            // const result1 = confirm('관심목록에 상품을 등록했습니다.\n관심목록 페이지로 이동할까요?')
            //     if (result1) {
            //         location.href = `/wish/goWishList`;
            //     }
            const strDelete = `
        
            <div class="col heart" 
            onclick="wishDelete(this,${itemCode})">
            ❤
            </div>
        `
            head.replaceChildren(head.textContent = '');
            head.insertAdjacentHTML("afterbegin", strDelete)


            // location.href = '/item/list'

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}


//하트 누르면 삭제
function wishDelete(divTag, itemCode) {
    const head = divTag.parentElement



    fetch('/wish/wishDelete', { //요청경로
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
            return response.text();
            // return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            // const result1 = confirm('관심목록에 상품을 등록했습니다.\n관심목록 페이지로 이동할까요?')
            //     if (result1) {
            //         location.href = `/wish/goWishList`;
            //     }
            const strInsert = `
        
            <div class="col heart" 
            onclick="wishAdd(this,${itemCode})">
            🤍
            </div>
        `
            head.replaceChildren(head.textContent = '');
            head.insertAdjacentHTML("afterbegin", strInsert)

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

function gologin() {
    alert('로그인을 해주세요.')
}
