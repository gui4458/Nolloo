let cateCode = document.querySelector('#cateCode').value
// LIMIT
let limit = 0;
// OFFSET
let offset = 0;
// 페이지 로드 시 초기 아이템 가져오기
let itemHtml = ``
const loginId = document.querySelector('#loginId')


// fetchInitialItems();
window.scrollTo(-10, -10)
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
            limit: limit,
            offset: offset,
            cateCode: cateCode
        })
    })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!


            displayItems(data);
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}
// 스크롤 이벤트 함수
function eventScroll() {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        fetchItems();
    }
}


// 스크롤 이벤트 리스너 등록
window.addEventListener('scroll', eventScroll);

// 추가 아이템 가져오는 함수
function fetchItems() {
    offset += limit;
    limit = 3;

    fetch('/item/list', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
            cateCode: cateCode,
            offset: offset,
            limit: limit

        })
    })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            itemHtml = '';
            displayItems(data); // 추가 아이템 표시

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}

//<div class="image-container w-24 h-24 lg:w-full lg:h-56 bg-cover bg-center rounded-full lg:rounded-lg" style="background-image: url('/upload/item/${item.imgList[0].attachedFileName}');"></div>




// 초기 아이템 표시 함수
function displayItems(items) {
    if (offset >= items.dataCnt) {
        itemHtml += '<div class="text-center w-[70%] mb-[70px] border-2 bg-white shadow-lg mx-auto h-[50px] p-3 rounded-lg text-base col-start-1 col-span-3">새로운 글이 없습니다.</div>'

        window.removeEventListener('scroll', eventScroll);
    } else {
        const itemListContainer = document.getElementById('itemListContainer');
        items.itemList.forEach(item => {
            let wishchk = items.wishCodeList.includes(item.itemCode)
            itemHtml = itemHtml + `
                
                    <div class="item-lazy bg-white shadow-xl shadow-slate-900/5 rounded-lg group">
                        <a href='/item/itemDetailForm?itemCode=${item.itemCode}'>
                        <div class="flex flex-row p-3 lg:flex-col">
                            <div class="image-container relative w-24 h-24 lg:w-full lg:h-56 bg-cover bg-center rounded-full lg:rounded-lg overflow-hidden">`
            if (item.cateCode == 1) {
                itemHtml += `<img class="object-cover w-full h-full group-hover:scale-110 transition-all duration-[500ms]" src="/upload/itemSolo/${item.imgList[0].attachedFileName}" alt="">
                `
            }
            if (item.cateCode == 2) {
                itemHtml += `<img class="object-cover w-full h-full group-hover:scale-110 transition-all duration-[500ms]" src="/upload/item/${item.imgList[0].attachedFileName}" alt="">
                `
            }
            if (loginId != null) {
                if (wishchk) {
                    itemHtml += `
                        <span class="text-red-500 absolute right-[10px] top-[5px] text-[25px] cursor-pointer" onclick="wishDelete(this,${item.itemCode})"><i class="ri-heart-3-line"></i></sapn>

                            `
                } else {
                    itemHtml += `
                        <span class="text-red-500 absolute right-[10px] top-[5px] text-[25px] cursor-pointer" onclick="wishAdd(this,${item.itemCode})"><i class="ri-heart-3-line"></i></sapn>
                    
                    `
                }
            }else {
                itemHtml += `
                                
                        <span class="text-red-500 absolute right-[10px] top-[5px] text-[25px] cursor-pointer" onclick="gologin()><i class="ri-heart-3-line"></i></sapn>
                            
                            `
            }


            itemHtml += `
            
            
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
                        <div class="w-8 h-8 ml-3">`
            if (loginId != null) {
                if (wishchk) {
                    itemHtml += `
                                <div class="" onclick="wishDelete(this,${item.itemCode})">
                                ❤
                                </div>
                                </div>
                                </div>`
                } else {
                    itemHtml += `<div class="" onclick="wishAdd(this,${item.itemCode})">
                                🤍
                                </div>
                                
                                </div>
                                </div>`
                }



            } else {
                itemHtml += `
                                
                                <div class="" onclick="gologin()">
                                🤍
                                </div>
                            
                            </div>
                            </div>
                            `
            }

        });
    }

    itemListContainer.innerHTML += itemHtml;
}

// 하트 추가 및 삭제 함수
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

// 로그인 함수
function gologin() {
    alert('로그인을 해주세요.');
}