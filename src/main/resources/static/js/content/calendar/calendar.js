document.addEventListener('DOMContentLoaded',function(){
  
  
  var calendarEl = document.getElementById('calendar');


  fetch('/calendar/getCalendarInfo', { //요청경로
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

    
  

    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: "dayGridMonth",
      firstDay: 1,
      titleFormat: function (date) {
        year = date.date.year;
        month = date.date.month + 1;
  
        return year + "년 " + month + "월";
      },
      headerToolbar: {
        left: 'prev,next',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek,dayGridDay'
      },dayCellContent: function (info) {
        var number = document.createElement("a");
        number.classList.add("fc-daygrid-day-number");
        number.innerHTML = info.dayNumberText.replace("일", '').replace("日","");
        if (info.view.type === "dayGridMonth") {
          return {
            html: number.outerHTML
          };
        }
        return {
          domNodes: []
        };
    },
      locale: "ko",
      initialDate: '2024-03-12',
      navLinks: true, // can click day/week names to navigate views
      editable: true,
      dayMaxEvents: true// allow "more" link when too many events
      
      
    });
    calendar.batchRendering(function() {
      data.forEach(e => {
        if(e.cateCode==1){
          calendar.addEvent({color : 'rgba(137, 43, 226, 0.795)' ,title: e.itemTitle, start: e.itemStartDate, end: e.itemEndDate,url:`http://localhost:8081/item/itemDetailForm?itemCode=${e.itemCode}` });
        }
        if(e.cateCode==2){
          calendar.addEvent({title: e.itemTitle, start: e.itemStartDate, end: e.itemEndDate,url:`http://localhost:8081/item/itemDetailForm?itemCode=${e.itemCode}` });
        }
        function goItemDetail() {
          // 채팅방 URL 생성
          const chatRoomUrl = `http://192.168.30.107:8081/item/itemDetailForm?itemCode=${e.itemCode}`;
      
          // 채팅방을 새 창으로 열기
          const windowFeatures = "left=1500,top=100,width=750,height=750,popup";
          window.open(chatRoomUrl, "_blank", windowFeatures);
      }
      });
      
    });

  
    calendar.render();



})
//fetch 통신 실패 시 실행 영역
.catch(err=>{
    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    console.log(err);
});

});