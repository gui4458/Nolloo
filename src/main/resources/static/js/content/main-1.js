// 페이지 로드 시 초기 아이템 가져오기
window.addEventListener('DOMContentLoaded', () => {
    fetchInitialItems();
});

// 초기 아이템 가져오는 함수
function fetchInitialItems() {
    fetch('/item/list', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(response => response.json())
    .then(data => {
        data.slice(0, 6).forEach(item => { // 처음 6개의 아이템만 선택하여 보여줌
            const itemHtml = `
                <div class="item-lazy bg-white shadow-xl shadow-slate-900/5 rounded-lg">
                    <a href="/item/itemDetailForm?itemCode=${item.itemCode}">
                        <div class="flex flex-row p-3 lg:flex-col">
                            <div class="image-container w-24 h-24 lg:w-full lg:h-56 bg-cover bg-center rounded-full lg:rounded-lg" style="background-image: url('/upload/item/${item.imgList[0].attachedFileName}');">
                            </div>
                            <div class="ml-5 lg:ml-0 lg:mt-3">
                                <figcaption class="font-medium">
                                    <div class="text-dark-600">
                                        <span class="inline-flex items-center rounded-md bg-pink-50 px-2 py-1 text-xs font-medium text-pink-700 ring-1 ring-inset ring-pink-700/10">${item.cateName}</span>
                                        <strong class="inline-flex text-xs py-1 pl-1">${item.itemPlace}</strong>
                                    </div>
                                    <div class="text-dark-600 font-bold mt-1">${item.itemTitle}</div>
                                    <div class="text-slate-600 text-sm mt-1">Description(Subtitle)</div>
                                    <div class="text-green-500 text-sm mt-1">${item.itemStartDate} ~ ${item.itemEndDate}</div>
                                </figcaption>
                            </div>
                        </div>
                    </a>
                    <div class="w-8 h-8 ml-3">
                        <!-- 하트 추가 및 삭제 로직 -->
                    </div>
                </div>`;
            itemList.innerHTML += itemHtml;
        });
    })
    .catch(error => console.error('Error fetching initial items:', error));
}

// 스크롤 이벤트 리스너 등록
window.addEventListener('scroll', () => {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        fetchItems();
    }
});

// 추가 아이템 가져오는 함수
function fetchItems() {
    fetch('/item/list', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(item => {
            const itemHtml = `
                <div class="item-lazy bg-white shadow-xl shadow-slate-900/5 rounded-lg">
                    <a href="/item/itemDetailForm?itemCode=${item.itemCode}">
                        <div class="flex flex-row p-3 lg:flex-col">
                            <div class="image-container w-24 h-24 lg:w-full lg:h-56 bg-cover bg-center rounded-full lg:rounded-lg" style="background-image: url('/upload/item/${item.imgList[0].attachedFileName}');">
                            </div>
                            <div class="ml-5 lg:ml-0 lg:mt-3">
                                <figcaption class="font-medium">
                                    <div class="text-dark-600">
                                        <span class="inline-flex items-center rounded-md bg-pink-50 px-2 py-1 text-xs font-medium text-pink-700 ring-1 ring-inset ring-pink-700/10">${item.cateName}</span>
                                        <strong class="inline-flex text-xs py-1 pl-1">${item.itemPlace}</strong>
                                    </div>
                                    <div class="text-dark-600 font-bold mt-1">${item.itemTitle}</div>
                                    <div class="text-slate-600 text-sm mt-1">Description(Subtitle)</div>
                                    <div class="text-green-500 text-sm mt-1">${item.itemStartDate} ~ ${item.itemEndDate}</div>
                                </figcaption>
                            </div>
                        </div>
                    </a>
                    <div class="w-8 h-8 ml-3">
                        <!-- 하트 추가 및 삭제 로직 -->
                    </div>
                </div>`;
            itemList.innerHTML += itemHtml;
        });
    })
    .catch(error => console.error('Error fetching items:', error));
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