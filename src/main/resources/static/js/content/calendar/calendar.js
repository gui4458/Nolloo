$(document).ready(function() {
		
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
        },
        defaultDate: '2016-12-12',
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: [
            {
                title: 'All Day Event',
                start: '2016-12-01'
            },
            {
                title: 'Long Event',
                start: '2016-12-07',
                end: '2016-12-10'
            },
            {
                id: 999,
                title: 'Repeating Event',
                start: '2016-12-09T16:00:00'
            },
            {
                id: 999,
                title: 'Repeating Event',
                start: '2016-12-16T16:00:00'
            },
            {
                title: 'Conference',
                start: '2016-12-11',
                end: '2016-12-13'
            },
            {
                title: 'Meeting',
                start: '2016-12-12T10:30:00',
                end: '2016-12-12T12:30:00'
            },
            {
                title: 'Lunch',
                start: '2016-12-12T12:00:00'
            },
            {
                title: 'Meeting',
                start: '2016-12-12T14:30:00'
            },
            {
                title: 'Happy Hour',
                start: '2016-12-12T17:30:00'
            },
            {
                title: 'Dinner',
                start: '2016-12-12T20:00:00'
            },
            {
                title: 'Birthday Party',
                start: '2016-12-13T07:00:00'
            },
            {
                title: 'Click for Google',
                url: 'https://google.com/',
                start: '2016-12-28'
            }
        ]
    });
    
});
// 날짜에 해당하는 내용의 String Format 으로 리턴해줍니다.
// Usage : new Date().toDateFormat(); // '2022-10-19'
// return String
Date.prototype.toDateFormat = function(){
    return this.toISOString().substring(0, 10);
  }
  
  // 날짜 기준으로 10월 19일인 오늘 기준으로 3일을 더할 수 있습니다. 
  // Usage : new Date().addDays(3); // '2022-10-22'
  // return Date
  Date.prototype.addDays = function(days) {
    let date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
  }
  
  // 위의 toDateFormat()에서 월까지만 나오게 됩니다. 
  // Usage : new Date().toDateFormatUntilMonth();  // '2022-10'
  // return String
  Date.prototype.toDateFormatUntilMonth = function(){
    return this.toISOString().substring(0, 7);
  }
  
  // 한글로 변환해서 월까지 뿌려줍니다. 
  // Usage : new Date().toDateFormat().toKoreanDateFormatter(); // '2022년 10월'
  // return String
  String.prototype.toKoreanDateFormatter = function() {
    return `${this.substring(0, 4)}년 ${this.substring(5, 7)}월`;
  }
  
  // YYYY-MM-dd 형식에서 연월일단위로 데이터를 뿌려줍니다.
  // Usage : '2022-10-19'.toKoreanDateFormatterYYYYMMDD(); // '2022년 10월 19일'
  // return String
  String.prototype.toKoreanDateFormatterYYYYMMDD = function() {
    return `${this.substring(0, 4)}년 ${this.substring(5, 7)}월 ${this.substring(8, 10)}일`;
  }
  
  // 기간값을 받았을 때 {start: '2022-10-19', end: '2022-10-25'}의 형태로 반환해줍니다.
  // Usage: toDateFormatPeriod('Oct 19-25, 2022') 
  // return JSON { start: 시작일자, end: 종료일자 }
  function toDateFormatPeriod(text) {
    let year = text.split(', ')[1];
    let split = text.split(' ');
    let firstDate = new Date(split[0] + ' ' + split[1] + ' ' + year).addDays(1);
    let secondDate = firstDate.addDays(6);
  
    return {
      "start" : firstDate.toDateFormat(),
      "end" : secondDate.toDateFormat(),
    }
  }
  
  // 문자열과 영문 월 축약형인지 아닌지의 플래그를 받아서 연월일 값으로 반환해줍니다. 
  // Usage : toDateFormatMMDDYYYY('Oct 19, 2022', true);
  // return String
  function toDateFormatMMDDYYYYY(text, short) {
    let split = text.split(' ');
    let month = short ? getMonthFromShortEng(split[0]) : getMonthFromEng(split[0]);
    let dayOfMonth = split[1].replaceAll(',', '');
    let year = split[2];
    let day = ("0" + dayOfMonth).slice(-2);
    return `${year}-${month}-${day}`;
  }
  
  // 연도와 영문 월을 받아서 1일 값을 가진 연월일 값으로 반환해줍니다. 
  // Usage : toDateFormatter('2022', 'October'); // '2022-10-01'
  // return String
  function toDateFormatter(year, enMonth) {
    let month = getMonthFromEng(enMonth);
    return year + '-' + month + '-01';
  }
  
  // 영문 월 이름을 받아 숫자 월로 반환해줍니다.
  // Usage: getMonthFromShortEng('October'); // '10'
  // return String
  function getMonthFromEng(enMonth) {
    let month = '01';
    if(enMonth == 'January') {
      month = '01';
    } else if(enMonth == 'Febrary') {
      month = '02';
    } else if(enMonth == 'March') {
      month = '03';
    } else if(enMonth == 'April') {
      month = '04';
    } else if(enMonth == 'May') {
      month = '05';
    } else if(enMonth == 'June') {
      month = '06';
    } else if(enMonth == 'July') {
      month = '07';
    } else if(enMonth == 'August') {
      month = '08';
    } else if(enMonth == 'September') {
      month = '09';
    } else if(enMonth == 'October') {
      month = '10';
    } else if(enMonth == 'November') {
      month = '11';
    } else {
      month = '12';
    }
    return month;
  }
  
  // 영문 짧은 월 이름을 받아 숫자 월로 반환해줍니다.
  // Usage: getMonthFromShortEng('Oct'); // '10'
  // return String
  function getMonthFromShortEng(enMonth) {
    let month = '01';
    if(enMonth == 'Jan') {
      month = '01';
    } else if(enMonth == 'Feb') {
      month = '02';
    } else if(enMonth == 'Mar') {
      month = '03';
    } else if(enMonth == 'Apr') {
      month = '04';
    } else if(enMonth == 'May') {
      month = '05';
    } else if(enMonth == 'Jun') {
      month = '06';
    } else if(enMonth == 'Jul') {
      month = '07';
    } else if(enMonth == 'Aug') {
      month = '08';
    } else if(enMonth == 'Sep') {
      month = '09';
    } else if(enMonth == 'Oct') {
      month = '10';
    } else if(enMonth == 'Nov') {
      month = '11';
    } else {
      month = '12';
    }
    return month;
  
  }
  $('#calendar').fullCalendar({
    height: 700,
    header: {
        //center: 'prev,title,next', 
        left: null, 
        center: 'title',
        right: null
    },
    defaultDate: 'YYYYMMDD', 
    editable: true,
    eventLimit: true,
    buttonText: {
        today: "오늘",
        month: "월별",
        week: "주별",
        day: "일별",
    }
   
});