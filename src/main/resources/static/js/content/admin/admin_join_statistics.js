fetch('/admin/adminJoinStatistics1', { //요청경로
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

    // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
    return response.json(); //나머지 경우에 사용
})
//fetch 통신 후 실행 영역
.then((item) => {//data -> controller에서 리턴되는 데이터!
    
    let monthList = []
    let cntList=[]
    item.month.forEach(m => {
        monthList.push(m.month);
        cntList.push(m.cnt);
    });

    let itemName=[]
    let reserve=[]
    item.items.forEach(i =>{
        itemName.push(i.itemVO.cateName);
        reserve.push(i.reserveCode);
        console.log(i);
    });
    console.log(item);
//Doughnut chart
    new Chart(document.querySelector('#doughnut-chart'), {
        type: 'doughnut',
        data: {
            labels: itemName,
            datasets: [
                {
                    label: "Population (millions)",
                    backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
                    data: reserve
                }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Predicted world population (millions) in 2050'
            }
        }
    });
    //bar chart
    new Chart(document.querySelector('#bar-chart-grouped'), {
    
        type: 'bar',
        data: {
            labels: monthList.reverse() ,
            datasets: [
                {
                    label: "월별 총 등록 상품수",
                    backgroundColor: "#3e95cd",
                    data: cntList.reverse()
                }, {
                    label: "Europe",
                    backgroundColor: "#8e5ea2",
                    data: [408, 547, 675, 734]
                }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Population growth (millions)'
            }
        }
        
    });
   
})
//fetch 통신 실패 시 실행 영역
.catch(err=>{
    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    console.log(err);
});
