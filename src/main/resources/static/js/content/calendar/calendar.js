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
      headerToolbar: {
        left: 'prevYear,prev,next,nextYear today',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek,dayGridDay'
      },
      initialDate: '2024-03-12',
      navLinks: true, // can click day/week names to navigate views
      editable: true,
      dayMaxEvents: true// allow "more" link when too many events
      
      
      
    });
    calendar.batchRendering(function() {
      data.forEach(e => {
        calendar.addEvent({ title: e.itemTitle, start: e.itemStartDate });
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