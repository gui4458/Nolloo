function wishAdd(itemCode){

    const result1 = confirm('관심목록에 상품을 등록하시겠습니까?')
    if (result1 == true) {
        result2 = confirm('관심목록에 상품을 등록 했습니다.\n관심목록 페이지로 이동할까요?')
        if (result2 == true) {
            location.href = `/wish/insertWish?itemCode=${itemCode}`;
        }
    }
}

function wishDelete(itemCode){
    
    location.href = `/wish/wishDelete?itemCode=${itemCode}`;
}