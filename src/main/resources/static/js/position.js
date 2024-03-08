let lat = parseFloat(itemX.value); 
let lng = parseFloat(itemY.value); 

let data = {
    lat: lat,
    lng: lng
};
console.log(data)

drawMap(data);

function drawMap(pos) {
    console.log(pos.lat);
    console.log(pos.lng);

    var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
    var mapOption = { 
        center: new kakao.maps.LatLng(pos.lat, pos.lng), // 지도의 중심좌표
        level: 4 // 지도의 확대 레벨
    };
    
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png'; // 마커이미지의 주소입니다    
    var imageSize = new kakao.maps.Size(64, 69); // 마커이미지의 크기입니다
    var imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
    
    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
    var markerPosition = new kakao.maps.LatLng(pos.lat, pos.lng); // 마커가 표시될 위치입니다
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition, 
        image: markerImage // 마커이미지 설정 
    });
    
    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);  
}
