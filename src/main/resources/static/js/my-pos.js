getMapData();

// Function to calculate distance between two points using Haversine formula
function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371; // Radius of the Earth in kilometers
    const dLat = deg2rad(lat2 - lat1);
    const dLon = deg2rad(lon2 - lon1);
    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = R * c; // Distance in kilometers
    return distance;
}

// Function to convert degrees to radians
function deg2rad(deg) {
    return deg * (Math.PI / 180);
}

function getMapData(code, date, r1,r2) {
    const fetchRequest = {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    };

    const requestBody = {};
    if (code) {
        requestBody.cateCode = code;
    }
    if (date) {
        requestBody.from = date;
    }
    if (r1) {
        requestBody.region1 = r1;
    }
    if (r2) {
        requestBody.region2 = r2;
    }
    fetchRequest.body = new URLSearchParams(requestBody);

    fetch('/now', fetchRequest)
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }
            return response.json();
        })
        .then((data) => {
            drawMap(data);
            navigator.geolocation.getCurrentPosition(position => {
                const currentLat = position.coords.latitude;
                const currentLon = position.coords.longitude;

                data.forEach(item => {
                    const distance = calculateDistance(currentLat, currentLon, item.itemX, item.itemY);
                    item.distance = distance;
                });
                data.sort((a, b) => a.distance - b.distance);

                console.log(data);

                displayItems(data);
            });
        })
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}


function displayItems(items) {
    const itemListContainer = document.getElementById('item-list');
    itemListContainer.innerHTML = ''; 



    items.forEach(item => {
        const itemElement = document.createElement('div');
        let str = ''; 

        if (item.cateCode == 2) {
            str = 'item';
        }
        else {
            str = 'itemSolo'; 
        }


        itemElement.innerHTML = `
        <div class="bg-white shadow-xl shadow-slate-900/5 rounded-lg" >
            <a href='/item/itemDetailForm?itemCode=${item.itemCode}'>
                <div class="flex flex-row p-3 lg:flex-col">
                    <div class="image-container w-24 h-24 lg:w-full lg:h-56 bg-cover bg-center rounded-full lg:rounded-lg" style="background-image: url('/upload/${str}/${item.imgList[0].attachedFileName}');">
                    </div>
                    <div class="ml-5 lg:ml-0 lg:mt-3">
                        <figcaption class="font-medium">
                            <div class="text-dark-600">
                                <span class="inline-flex items-center rounded-md bg-pink-50 px-2 py-1 text-xs font-medium text-pink-700 ring-1 ring-inset ring-pink-700/10">${item.cateName}</span>
                                <strong class="inline-flex text-xs py-1 pl-1">${item.itemPlace}</strong>
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
            <div id="distance" class="w-8 h-8 ml-3"> ${item.distance.toFixed(2)}km</div>
            <div id="attend" class="w-8 h-8 ml-3">참가</div>
            <div id="wait" class="w-8 h-8 ml-3">대기</div>

        </div>`;
        
        itemListContainer.appendChild(itemElement);
    });
}


function drawMap(data) {

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 6 // 지도의 확대 레벨
    };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    for (var i = 0; i < data.length; i ++) {

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        var latlon = new kakao.maps.LatLng(data[i].itemX, data[i].itemY)
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: latlon, // 마커를 표시할 위치
            image : markerImage // 마커 이미지
        });
    }

    // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
    if (navigator.geolocation) {

        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {

            var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도

            var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">내위치</div>'; // 인포윈도우에 표시될 내용입니다

            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition, message);
        });

    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
        message = 'geolocation을 사용할수 없어요..'

        displayMarker(locPosition, message);
    }

    // 지도에 마커와 인포윈도우를 표시하는 함수입니다
    function displayMarker(locPosition, message) {

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: locPosition
        });

        var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            content : iwContent,
            removable : iwRemoveable
        });

        // 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);

        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);
    }

}

