const selectBox = document.querySelector('.cate-select');

selectBox.addEventListener('change', function () {
    let changeTable = document.querySelector('.change-td');

    let cateCode = selectBox.value;
    changeTable.replaceChildren(changeTable.textContent = '');
    fetch('/admin/cateSelect', { //요청경로
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

            let str = '';
            data.forEach((e, i) => {
                str += `

                <tr class="[&>td]:p-2 font-semibold border-b-2  border-t-red-500">
                    
                        <td>${e.itemCode}</td>
                        
                        <td>
                        ${e.itemTitle}
                        </td>

                        <td>${e.itemPrice == '0' ? '-' : '￦ '+e.itemPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원'}</td>
                        <td>
                            ${e.cateCode == 2 ? '-' : e.itemPeople +'/'+ e.peopleCnt}
                        </td>
                        <td>${e.itemPlace}</td>
                        <td>${e.itemStartDate}~${e.itemEndDate}</td>
                        <td>${e.readCnt}</td>

                    </tr>
            `

            });
            changeTable.insertAdjacentHTML('afterbegin', str)

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
});





