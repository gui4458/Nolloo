// LIMIT
let limit = 0;
// OFFSET
let offset = 0;
const cateCode = 2;
// 페이지 로드 시 초기 아이템 가져오기
    let itemHtml = ``

window.addEventListener('DOMContentLoaded', () => {
    fetchInitialItems();
});

// 초기 아이템 가져오는 함수
function fetchInitialItems() {
    limit = 9;
    offset = 0;

    fetch('/item/list', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
            limit : limit,
            offset : offset,
            cateCode: cateCode
        })
    })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data)
            displayItems(data);
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

// 스크롤 이벤트 리스너 등록
window.addEventListener('scroll', () => {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        fetchItems();
    }
});

// 추가 아이템 가져오는 함수
function fetchItems() {
    limit = 3;
    offset = 9;
    fetch('/item/list', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
            cateCode: cateCode
        })
    })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            displayItems(data); // 추가 아이템 표시
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

// 아이템 표시 함수
function displayItems(items) {

    const itemListContainer = document.getElementById('itemListContainer');
    items.forEach(item => {
        
            itemHtml = itemHtml + `
            
                <div class="item-lazy bg-white shadow-xl shadow-slate-900/5 rounded-lg">
                    <a href='@{/item/itemDetailForm(itemCode=${item.itemCode})}'>
                    <div class="flex flex-row p-3 lg:flex-col">
                        <div class="image-container w-24 h-24 lg:w-full lg:h-56 bg-cover bg-center rounded-full lg:rounded-lg" style="background-image: url('/upload/item/${item.imgList[0].attachedFileName}');">
                        </div>
            
                        <div class="ml-5 lg:ml-0 lg:mt-3">
                            <figcaption class="font-medium">
                                <div class="text-dark-600">
                                    <span class="inline-flex items-center rounded-md bg-pink-50 px-2 py-1 text-xs font-medium text-pink-700 ring-1 ring-inset ring-pink-700/10">${item.cateName}</span><strong class="inline-flex text-xs py-1 pl-1">${item.itemPlace}</strong>
                                </div>
                                <div class="text-dark-600 font-bold mt-1">
                                ${item.itemTitle}                  
                                </div>
                                <div class="text-slate-600 text-sm mt-1">
                                    Description(Subtitle)
                                </div>
                                <div class="text-green-500 text-sm mt-1">
                                    ${item.itemStartDate}~${item.itemEndDate}
                                </div>
                            </figcaption>
                        </div>

                    </div>
                    </a>
                    <div class="w-8 h-8 ml-3">
                        <block sec:authorize="isAuthenticated()">
                            <div class="" onclick="wishDelete(this,${item.itemCode})"
                            >
                            ❤
                            </div>

                            <div class="" onclick="wishAdd(this,${item.itemCode})"
                            >
                            🤍
                            </div>
                        </th:block>
                        <th:block sec:authorize="isAnonymous()">
                            <div class="" onclick="gologin()">
                            🤍
                            </div>
                        </th:block>
                    </div>
                </div>
             `



    });
    itemListContainer.innerHTML += itemHtml;
}

// 하트 추가 및 삭제 함수
function wishAdd(divTag, itemCode) {
    // 추가 로직
}

function wishDelete(divTag, itemCode) {
    // 삭제 로직
}

// 로그인 함수
function gologin() {
    alert('로그인을 해주세요.');
}