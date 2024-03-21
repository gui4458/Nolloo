
function getDetail(itemCode) {

    fetch('/item/selectItemDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            itemCode: itemCode
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!


            const detail_div = document.querySelector('.detail-div')
            detail_div.innerHTML = '';

            let str = '';
            str += `

        <h6 style="font-weight: bold;">파티 상세 내역</h6>

        <form action="/item/updateItem" method="post">
        <input type="hidden" name="itemCode" value="${itemCode}">
            <table class="table table-bordered">

            <tr>
            <td>제목</td>
            <td>
                <input type="text" class="form-control" name="itemTitle" value=" ${data.itemTitle}">
            </td>
            </tr>
                <tr>
                    <td>내용</td>
                    <td>
                        <input type="text" class="form-control" name="itemContent" value=" ${data.itemContent}">
                    </td>
                </tr>
                <tr>
                    <td>위치</td>
                    <td>
                        <input type="text" class="form-control" name="itemPlace" value="${data.itemPlace}">
                    </td>
                </tr>
                <tr>
                    <td>시작 날짜</td>
                    <td>
                    <input type="text" class="form-control" name="itemStartDate" value="${data.itemStartDate}">
                    </td>
                </tr>
                <tr>
                    <td>마감 날짜</td>
                    <td>
                    <input type="text" class="form-control" name="itemEndDate" value="${data.itemEndDate}">
                    </td>
                </tr>            
                <tr>
                    <td>가격</td>
                    <td>
                    <input type="text" class="form-control" name="itemPrice" value="${data.itemPrice}">
                    </td>
                </tr>        
                <tr>
                    <td>인원수</td>
                    <td>
                    <input type="text" class="form-control" name="itemPeople" value="${data.itemPeople}">
                    </td>
                </tr>        
                <tr>
                    <td>연락처</td>
                    <td>
                    <input type="text" class="form-control" name="itemTel" value="${data.itemTel == null ? '미등록' : data.itemTel}">
                    </td>
                </tr>        
                <tr>
                    <td>대표 이미지</td>
                    <td>`;

            data.imgList.forEach(element => {
                if (element.isMain == 'Y') {
                    str += `<div class="row">
                                <div class="col">
                                    <input class="form-control" type="file" name="originfileName" id="main_img_input" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    ${element.originFileName}
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-excel" viewBox="0 0 16 16" onclick="goDeleteImg(${element.imgCode},this);">
                                        <path d="M5.18 4.616a.5.5 0 0 1 .704.064L8 7.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 8l2.233 2.68a.5.5 0 0 1-.768.64L8 8.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 8 5.116 5.32a.5.5 0 0 1 .064-.704"></path>
                                        <path d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1"></path>
                                    </svg>
                                </div>
                            </div>
                        `;
                }
            });

            str +=
                `</td>
                    </tr>        
                    <tr>
                        <td>상세 이미지</td>
                        <td>
                            <div class="row">
                                    <div class="col">
                                        <input class="form-control" type="file" name="originfileName" multiple>
                                    </div>
                                </div>`;

            data.imgList.forEach(element => {
                if (element.isMain == 'N') {
                    str += `
                            <div class="row">
                                <div class="col">
                                    ${element.originFileName}
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-excel" viewBox="0 0 16 16" onclick="goDeleteImg(${element.imgCode}, this);">
                                        <path d="M5.18 4.616a.5.5 0 0 1 .704.064L8 7.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 8l2.233 2.68a.5.5 0 0 1-.768.64L8 8.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 8 5.116 5.32a.5.5 0 0 1 .064-.704"></path>
                                        <path d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1"></path>
                                    </svg>
                                </div>
                            </div>
                        `;
                }
            });

            str += `</td>
                    </tr>        
                </table>
                <input type="submit" value="수정" class="btn btn-outline-secondary">
        </form>
            

            `;
            detail_div.insertAdjacentHTML("afterbegin", str);

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}


//x 버튼 클릭 시 이미지를 삭제하는 코드
function goDeleteImg(imgCode, selected_tag){
    //이미지 삭제하러 컨트롤러 이동
    fetch('/item/deleteImg', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            imgCode : imgCode
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            //삭제한 이미지명을 화면에서 제거
        
            

            //대표이미지 삭제 시 대표이미지 input 태그 활성화
            const fileInputTag = selected_tag.closest('tr').querySelector('input');
            const colTag = selected_tag.parentElement;

            selected_tag.parentElement.innerHTML = '';    
        
            console.log(colTag.textContent.trim());
            if(colTag.textContent.trim() == ''){

                fileInputTag.disabled = false;
            }

            

    
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

