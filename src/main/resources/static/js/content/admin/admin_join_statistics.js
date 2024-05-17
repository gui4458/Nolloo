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
        if (!response.ok) {
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return;
        }

        // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((item) => {//data -> controller에서 리턴되는 데이터!

        let monthList = [];
        let cntList = [];
        let reserveList = [];
        item.month.forEach(m => {
            monthList.push(m.month);
            cntList.push(m.cnt);
            reserveList.push(m.reserveCode);
        });


        let itemName = [];
        let reserve = [];
        item.items.forEach(i => {
            itemName.push(i.itemVO.cateName);
            reserve.push(i.reserveCode);

        });

        let itemTrueName = [];
        let itemReserve = [];
        item.itemsTrue.forEach(i => {
            itemTrueName.push(i.itemVO.cateName);
            itemReserve.push(i.reserveCode);
        });
        console.log(itemReserve);
        const tbodyTag = document.querySelector('.tbody-tag')
        let newTbodyStr = ``
        //Doughnut chart 누적참여인원
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
                },
                onClick: function (event, elements, chart) {
                    fetch('/admin/getReserveList', {
                        method: 'POST',
                        cache: 'no-cache',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                        },
                        body: new URLSearchParams({
                            // 데이터명 : 데이터값
                        })
                    })
                        .then((response) => {
                            if (!response.ok) {
                                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                                return;
                            }
                            return response.json();
                        })
                        .then((data) => {
                            let i = elements[0].index;
                            alert(`2=${chart.data.labels[i]}`);
                            const tbodyTag = document.querySelector('.tbody-tag');
                            let newTbodyStr = ``;
                            let newIndex = 0;
                            let minus = 0;
                            data.forEach((e) => {
                                if (e.itemVO.cateName == chart.data.labels[i]) {
                                    newIndex += 1;
                                }
                            });
                            console.log(newIndex)
                            data.forEach((e, index1) => {
                                
                                if (e.itemVO.cateName == chart.data.labels[i]) {
                                    
                                    newTbodyStr += `
                                    <tr  class="[&>td]:p-2 font-semibold border-b-2 text-center">
                                        <td>${newIndex - minus}</td>
                                        <td>${e.itemVO.cateName}</td>
                                        <td>${e.itemVO.memberId}</td>
                                        <td>${e.itemVO.itemTitle}</td>
                                    </tr>
                                    `
                                    minus += 1; 
                                }


                            });

                            tbodyTag.replaceChildren(tbodyTag.textContent = '');
                            tbodyTag.insertAdjacentHTML('afterbegin', newTbodyStr)
                        })
                        .catch(err => {
                            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                            console.log(err);
                        });
                }

            }
        });

        // 월별 파티 참여 인원, 월별 등록된 총 상품수
        //Mixed chart
        new Chart(document.querySelector('#mixed-chart'), {
            type: 'bar',
            data: {
                labels: monthList.reverse(),
                datasets: [{
                    label: "월별 파티 참여인수",
                    type: "line",
                    borderColor: "#8e5ea2",
                    data: reserveList.reverse(),
                    fill: false
                },
                //{
                //     label: "Africa",
                //     type: "line",
                //     borderColor: "#3e95cd",
                //     data: [133, 221, 783, 2478],
                //     fill: false
                // }, 
                {
                    label: "월별 등록된 총 상품수",
                    type: "bar",
                    backgroundColor: "#ffe4c4",
                    data: cntList.reverse(),
                },
                    // {
                    //     label: "Africa",
                    //     type: "bar",
                    //     backgroundColor: "rgba(0,0,0,0.2)",
                    //     backgroundColorHover: "#3e95cd",
                    //     data: [133, 221, 783, 2478]
                    // }
                ]
            },
            options: {
                title: {
                    display: true,
                    text: 'Population growth (millions): Europe & Africa'
                },
                legend: { display: false }
            }
        });
        //Doughnut chart 현재 참여한 인원
        new Chart(document.querySelector('#doughnut-chart-itemTrue'), {
            type: 'doughnut',
            data: {
                labels: itemTrueName,
                datasets: [
                    {
                        label: "Population (millions)",
                        backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
                        data: itemReserve
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

    })

    //fetch 통신 실패 시 실행 영역
    .catch(err => {
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
//차트 클릭하면
